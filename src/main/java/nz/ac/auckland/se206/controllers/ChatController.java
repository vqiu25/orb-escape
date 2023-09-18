package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import java.util.Random;
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
import nz.ac.auckland.se206.SceneManager.AppScene;
import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.GptPromptEngineering;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionResult;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionResult.Choice;

/** Controller class for the chat view. */
public class ChatController extends ControllerMethods {
  @FXML private TextArea chatTextArea;
  @FXML private TextField inputText;
  @FXML private Label chatTimerLabel;
  @FXML private ImageView gameMasterClose;
  @FXML private ImageView gameMasterCloseHover;
  @FXML private ImageView blueRectangle;
  @FXML private ImageView pongAnimation;
  @FXML private ImageView hourGlassAnimation;
  @FXML private ImageView pacManAnimation;
  @FXML private ImageView barAnimation;
  @FXML private ImageView sendButtonHover;
  @FXML private ImageView sendButtonPressed;

  private ChatCompletionRequest chatCompletionRequest;

  /**
   * Initializes the chat view, loading the riddle.
   *
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  @FXML
  public void initialize() throws ApiProxyException {
    // Bind the timer label to the display time
    chatTimerLabel.textProperty().bind(ControllerMethods.displayTime);

    // Randomly select either lamp or rug as the word to guess:
    String wordToGuess;
    Random random = new Random();
    int randomInt = random.nextInt(20); // Picks a random number between 0 and 20

    if (randomInt <= 10) {
      wordToGuess = "cabinet"; // If number is less than or equal to 10, word to guess is cabinet
      GameState.isCabinet = true;
    } else {
      wordToGuess = "rug";
      GameState.isRug = true;
    }

    chatCompletionRequest =
        new ChatCompletionRequest().setN(1).setTemperature(1.3).setTopP(0.5).setMaxTokens(100);
    runGpt(new ChatMessage("user", GptPromptEngineering.getRiddleWithGivenWord(wordToGuess)));
  }

  /**
   * Appends a chat message to the chat text area.
   *
   * @param msg the chat message to append
   */
  private void appendChatMessage(ChatMessage msg) {
    chatTextArea.appendText(msg.getRole() + ": " + msg.getContent() + "\n\n");
  }

  /**
   * Runs the GPT model with a given chat message.
   *
   * @param msg the chat message to process
   * @return the response chat message
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  private ChatMessage runGpt(ChatMessage msg) throws ApiProxyException {
    chatCompletionRequest.addMessage(msg);
    try {
      ChatCompletionResult chatCompletionResult = chatCompletionRequest.execute();
      Choice result = chatCompletionResult.getChoices().iterator().next();
      chatCompletionRequest.addMessage(result.getChatMessage());
      appendChatMessage(result.getChatMessage());
      return result.getChatMessage();
    } catch (ApiProxyException e) {
      // TODO handle exception appropriately
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Sends a message to the GPT model.
   *
   * @param event the action event triggered by the send button
   * @throws ApiProxyException if there is an error communicating with the API proxy
   * @throws IOException if there is an I/O error
   */
  private void onSendMessage() throws ApiProxyException, IOException {
    String message = inputText.getText();
    // If the user has not entered a message, do nothing
    if (message.trim().isEmpty()) {
      sendButtonPressed.setOpacity(0);
      return;
    }
    blueRectangle.setOpacity(1);
    selectRandomAniamtion();

    // If the user has entered a message:
    inputText.clear();
    sendButtonPressed.setDisable(true);

    // Add the user's message to the chat text area
    ChatMessage msg = new ChatMessage("user", message);
    appendChatMessage(msg);

    // Create a new thread to run the GPT model
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

                    App.getRoomController().updateTaskLabel("Click on the riddle's answer!");
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
   * @throws IOException if there is an I/O error
   */
  @FXML
  private void returnToRoom(MouseEvent event) throws ApiProxyException, IOException {
    App.setScene(AppScene.ROOM);
    // TODO: Update so that it returns to prior room
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
  }

  // Request focus on input text
  private void requestFocus() {
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
}
