package nz.ac.auckland.se206.controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.GptPromptEngineering;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionResult;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionResult.Choice;

import java.io.IOException;
import java.util.Random;

/**
 * Controller class for the chat view.
 */
public class ChatController extends ControllerMethods {
  @FXML
  private Label lblTimer;
  @FXML
  private Label lblTask;
  @FXML
  private Label lblHints;

  @FXML
  private TextArea chatTextArea;
  @FXML
  private TextField inputText;
  @FXML
  private ImageView gameMasterClose;
  @FXML
  private ImageView gameMasterCloseHover;
  @FXML
  private ImageView blueRectangle;
  @FXML
  private ImageView pongAnimation;
  @FXML
  private ImageView hourGlassAnimation;
  @FXML
  private ImageView pacManAnimation;
  @FXML
  private ImageView barAnimation;
  @FXML
  private ImageView sendButtonHover;
  @FXML
  private ImageView sendButtonPressed;

  // Book items:
  @FXML
  private ImageView riddleBook;
  @FXML
  private TextArea riddleTextArea;
  @FXML
  private TextArea riddleTextChatArea;

  // Background
  @FXML
  private ImageView background;

  // Inventory Items
  @FXML
  private ImageView fishingRodIcon;
  @FXML
  private ImageView axeIcon;
  @FXML
  private ImageView fishIcon;
  @FXML
  private ImageView planksIcon;
  @FXML
  private ImageView blueOrb;
  @FXML
  private ImageView greenOrb;
  @FXML
  private ImageView redOrb;

  // Game states:
  private boolean isRiddleInitialized = false;

  private ChatCompletionRequest riddleChatCompletionRequest;
  private ChatCompletionRequest chatCompletionRequest;

  /**
   * Initializes the chat view, loading the riddle.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {
    // Bind the labels to the display values and styles
    lblTimer.textFillProperty().bind(ControllerMethods.timerTextFill);
    lblTimer.textProperty().bind(ControllerMethods.displayTime);
    lblTask.textProperty().bind(ControllerMethods.displayTask);
    lblHints.textProperty().bind(ControllerMethods.displayHints);

    // Initialise the inventory items
    fishingRodIcon = getFishingRodIcon();
    axeIcon = getAxeIcon();
    fishIcon = getFishIcon();
    planksIcon = getPlanksIcon();
    blueOrb = getBlueOrb();
    greenOrb = getGreenOrb();
    redOrb = getRedOrb();

    // Bind the inventory images to their image properties
    bindInventory();

    // Bind the background to its image properties
    background.imageProperty().bind(ControllerMethods.backgroundImageProperty);

    // Randomly select either cabinet or rug as the word to guess:
    String wordToGuess;
    Random random = new Random();
    int randomInt = random.nextInt(10);

    if (randomInt > 4) {
      wordToGuess = "cabinet";
      GameState.isCabinet = true;
    } else {
      wordToGuess = "rug";
      GameState.isRug = true;
    }

    riddleChatCompletionRequest =
            new ChatCompletionRequest().setN(1).setTemperature(0.2).setTopP(0.4).setMaxTokens(45);
    runGpt(new ChatMessage("assistant", GptPromptEngineering.getRiddleWithGivenWord(wordToGuess)));

    chatCompletionRequest =
            new ChatCompletionRequest().setN(1).setTemperature(0.2).setTopP(0.4).setMaxTokens(80);
    runGpt(new ChatMessage("assistant", GptPromptEngineering.getGameMaster()));
  }

  /**
   * Appends a chat message to the chat text area.
   *
   * @param msg      the chat message to append
   * @param textArea the text area to append the message to
   */
  private void appendChatMessage(ChatMessage msg, TextArea textArea) {
    String prefix;

    if (textArea == riddleTextArea) {
      // If setting the riddle, don't add a prefix
      prefix = "";
    } else if (msg.getRole().equals("user")) {
      // If the message is from the user, set prefix to "You said: "
      prefix = "You said: ";
    } else {
      // If the message is from the game master, set prefix to "CLOUD: "
      prefix = "CLOUD: ";

      // If the message is from CLOUD and is a hint, append custom statement to text area
      if (msg.getContent().startsWith("Hint")) {
        // Remove the "Hint: " prefix and append custom statement
        String modifiedString = msg.getContent().replace("Hint: ", "Sure! Here is a hint for you.\n");

        // Append the message to the text area
        textArea.appendText(prefix + modifiedString + "\n\n");

        return;
      }
    }
    // Append the message to the text area
    textArea.appendText(prefix + msg.getContent() + "\n\n");
  }

  /**
   * Runs the GPT model with a given chat message.
   *
   * @param msg the chat message to process
   * @return the response chat message
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  private ChatMessage runGpt(ChatMessage msg) throws ApiProxyException {

    // ONLY called when game initializes to set up the riddle book:
    if (!isRiddleInitialized) {
      riddleChatCompletionRequest.addMessage(msg);
      isRiddleInitialized = true;

      return gptHelper(riddleChatCompletionRequest, riddleTextArea);
    }

    // Called when appending the message to riddle chat area:
    if (GameState.isRiddleBookOpen) {
      riddleChatCompletionRequest.addMessage(msg);
      return gptHelper(riddleChatCompletionRequest, riddleTextChatArea);
    } else {
      // Otherwise, append the message to the chat text area:
      chatCompletionRequest.addMessage(msg);
      return gptHelper(chatCompletionRequest, chatTextArea);
    }
  }

  // Function that take a request and executes it, appending the output to a certain text area:
  public ChatMessage gptHelper(ChatCompletionRequest request, TextArea textArea) {
    try {
      // Execute the request and get the result
      ChatCompletionResult chatCompletionResult = request.execute();

      // Get the first choice from the result
      Choice result = chatCompletionResult.getChoices().iterator().next();

      // Add the result to the request
      request.addMessage(result.getChatMessage());

      // Append the result to the text area
      appendChatMessage(result.getChatMessage(), textArea);

      return result.getChatMessage();
    } catch (ApiProxyException e) {
      e.printStackTrace();

      return null;
    }
  }

  /**
   * Sends the user's message inputted into the text to the GPT model.
   *
   * @param event the action event triggered by the send button
   * @throws ApiProxyException if there is an error communicating with the API proxy
   * @throws IOException       if there is an I/O error
   */
  private void onSendMessage() throws ApiProxyException, IOException {
    // Get the user's message
    String message = inputText.getText();

    // If the user has not entered a message, do nothing
    if (message.trim().isEmpty()) {
      sendButtonPressed.setOpacity(0);
      return;
    }

    // Add the user's message to the chat text area
    ChatMessage userMessage = new ChatMessage("user", message);

    // If riddle book is open, append to riddle text area:
    if (GameState.isRiddleBookOpen) {
      appendChatMessage(userMessage, riddleTextChatArea);
    } else {
      appendChatMessage(userMessage, chatTextArea);
    }

    // Animate GPT loading screen:
    blueRectangle.setOpacity(1);
    selectRandomAniamtion();

    // If the user has entered a message:
    inputText.clear();
    sendButtonPressed.setDisable(true);

    // Prepend their message with a prompt depending on how many hints they have
    ChatMessage msg;

    // If the user has hints available, prepend the message with a hint prompt:
    if (GameState.hintCount > 0) {

      String hint = "";

      // Get game context for hints:
      if (!GameState.isRiddleFound) {
        hint = "looking for a book.";
      } else if (!GameState.isRiddleResolved && GameState.isRug) {
        hint = "solving a riddle where the answer is rug. do not reveal the answer.";
      } else if (!GameState.isRiddleResolved && GameState.isCabinet) {
        hint = "solving a riddle where the answer is cabinet. do not reveal the answer.";
      } else if (!GameState.isRoomOrbCollected && GameState.isRug) {
        hint = "the orb being found under the rug";
      } else if (!GameState.isRoomOrbCollected && GameState.isCabinet) {
        hint = "the orb being found in the cabinet";
      } else if (GameState.isForestTreeChopping && !GameState.isForestGameCompleted) {
        hint = "exploring the tree";
      } else if (GameState.isForestFishing && !GameState.isForestGameCompleted) {
        hint = "exploring the fishing dock";
      } else if (GameState.isLavaBridge && !GameState.isLavaGameCompleted) {
        hint = "fixing the bridge with some wood";
      } else if (GameState.isLavaDragon && !GameState.isLavaGameCompleted) {
        hint = "distracting the dragon with fish";
      } else if (!GameState.isCodeFound) {
        hint = "searching behind a map";
      } else if (!GameState.isOrbsPlaced) {
        hint = "activating the portal to escape";
      }

      msg = new ChatMessage("user", GptPromptEngineering.hintAvailablePrompt(message, hint));
    } else {
      // Otherwise, prepend the message with a no hints prompt:
      msg = new ChatMessage("user", GptPromptEngineering.noHintsAvailablePrompt(message));
    }

    // Create a new thread to run the GPT model based on what the user inputted:
    Task<Void> gameMasterTask =
            new Task<Void>() {
              @Override
              protected Void call() throws Exception {

                ChatMessage lastMsg = runGpt(msg);

                Platform.runLater(
                        () -> {
                          // Check if the user has guessed the riddle:
                          if (lastMsg.getRole().equals("assistant")
                                  && lastMsg.getContent().startsWith("Correct")) {
                            GameState.isRiddleResolved = true;
                            updateTask();
                          }

                          // Check if the user has asked for a hint:
                          if (lastMsg.getRole().equals("assistant")
                                  && lastMsg.getContent().startsWith("Hint")
                                  && GameState.hintCount > 0) {
                            // Update hint
                            GameState.hintCount--;
                            updateHintsRemaining();
                          }
                        });

                return null;
              }
            };

    // Unbind:
    gameMasterTask.setOnSucceeded(
            e -> {
              sendButtonPressed.setDisable(false);
              hideAllAnimations();
              sendButtonPressed.setOpacity(0);
            });

    gameMasterTask.setOnFailed(
            e -> {
              sendButtonPressed.setDisable(false);
              hideAllAnimations();
              sendButtonPressed.setOpacity(0);
            });

    // Start the thread
    Thread gameMasterThread = new Thread(gameMasterTask);
    gameMasterThread.start();
  }

  // Game Master Animations
  @FXML
  private void gameMasterCloseOnHover(MouseEvent event) {
    gameMasterCloseHover.setOpacity(1);
    requestFocus();
  }

  @FXML
  private void gameMasterCloseOnUnhover(MouseEvent event) {
    gameMasterCloseHover.setOpacity(0);
  }

  /**
   * Navigates back to the previous view.
   *
   * @param event the action event triggered by the go back button
   * @throws ApiProxyException if there is an error communicating with the API proxy
   * @throws IOException       if there is an I/O error
   */
  @FXML
  private void returnToRoom(MouseEvent event) throws ApiProxyException, IOException {

    // Disable the riddle book:
    disableRiddleBookOpacity();

    // If the riddle book has been opened, close it:
    if (GameState.isRiddleBookOpen) {
      GameState.isRiddleBookOpen = false;
    }

    // Return to previous scene by popping stack:
    App.setScene(GameState.lastScene);
  }

  // Send Button
  @FXML
  private void sendButtonOnHover(MouseEvent event) {
    sendButtonHover.setOpacity(1);
  }

  @FXML
  private void sendButtonOnUnhover(MouseEvent event) {
    sendButtonHover.setOpacity(0);
  }

  @FXML
  private void sendButtonPressed(MouseEvent event) {
    sendButtonPressed.setOpacity(1);
  }

  @FXML
  private void sendButtonReleased(MouseEvent event) throws ApiProxyException, IOException {
    onSendMessage();
  }

  @FXML
  private void keyPressed(KeyEvent event) {
    // if user presses enter, run onSendMessage()
    if (event.getCode().toString().equals("ENTER")) {
      try {
        onSendMessage();
      } catch (ApiProxyException | IOException e) {
        e.printStackTrace();
      }
    }

    // if the user presses escape, return to previous scene
    checkIfEscapePressed(event);
  }

  @FXML
  private void escapeKeyPressed(KeyEvent event) {
    checkIfEscapePressed(event);
  }

  private void checkIfEscapePressed(KeyEvent event) {
    // if the user presses escape, return to previous scene
    if (event.getCode().toString().equals("ESCAPE")) {
      try {
        returnToRoom(null);
      } catch (ApiProxyException | IOException e) {
        e.printStackTrace();
      }
    }
  }

  // Request focus on input text
  public void requestFocus() {
    Platform.runLater(() -> inputText.requestFocus());
  }

  // Select an animation for the loading screen
  private void selectRandomAniamtion() {
    Random random = new Random();
    int randomInt = random.nextInt(4); // Picks a random number between 0 and 4

    if (randomInt == 0) {
      pongAnimation.setOpacity(1);
    } else if (randomInt == 1) {
      hourGlassAnimation.setOpacity(1);
    } else if (randomInt == 2) {
      pacManAnimation.setOpacity(1);
    } else {
      barAnimation.setOpacity(1);
    }
  }

  private void hideAllAnimations() {
    blueRectangle.setOpacity(0);
    pongAnimation.setOpacity(0);
    hourGlassAnimation.setOpacity(0);
    pacManAnimation.setOpacity(0);
    barAnimation.setOpacity(0);
  }

  public void setRiddleBookOpacity() {
    riddleTextChatArea.setDisable(false);
    riddleBook.setDisable(false);
    riddleTextArea.setDisable(false);
    riddleBook.setOpacity(0.9);
    riddleTextArea.setOpacity(0.9);
    riddleTextChatArea.setOpacity(0.9);
    chatTextArea.setOpacity(0);
  }

  public void disableRiddleBookOpacity() {
    riddleTextChatArea.setDisable(true);
    riddleBook.setDisable(true);
    riddleTextArea.setDisable(true);
    riddleBook.setOpacity(0);
    riddleTextArea.setOpacity(0);
    riddleTextChatArea.setOpacity(0);
    chatTextArea.setOpacity(1);
  }
}
