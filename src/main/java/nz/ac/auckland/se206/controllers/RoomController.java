package nz.ac.auckland.se206.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameMaster;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppScene;
import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.GptPromptEngineering;
import nz.ac.auckland.se206.speech.TextToSpeech;
import org.controlsfx.control.Notifications;

/** Controller class for the room view. */
public class RoomController extends ControllerMethods {

  @FXML private Label lblTimer;
  @FXML private Label lblTask;
  @FXML private Label lblHints;

  @FXML private ImageView rightArrow;
  @FXML private ImageView rightArrowHover;
  @FXML private ImageView rightArrowPressed;
  @FXML private ImageView leftArrow;
  @FXML private ImageView leftArrowHover;
  @FXML private ImageView leftArrowPressed;
  @FXML private ImageView cabinetOutline;
  @FXML private ImageView rugOutline;
  @FXML private ImageView windowOutline;
  @FXML private ImageView mapOutline;
  @FXML private ImageView terminalOutline;
  @FXML private ImageView switchOn;
  @FXML private ImageView switchOff;
  @FXML private ImageView switchOnOutline;
  @FXML private ImageView switchOffOutline;
  @FXML private ImageView darkOverlay;
  @FXML private ImageView hiddenCode;
  @FXML private ImageView hiddenCodeOutline;
  @FXML private ImageView code;
  @FXML private ImageView codeOutline;
  @FXML private ImageView glowParticleOne;
  @FXML private ImageView glowParticleTwo;
  @FXML private ImageView glowParticleThree;
  @FXML private ImageView map;
  @FXML private ImageView portalBaseOutline;
  @FXML private ImageView portalFrameOutline;
  @FXML private ImageView portal;
  @FXML private ImageView portalOutline;

  // Game Master
  @FXML private ImageView gameMasterDefault;
  @FXML private ImageView gameMasterChat;

  // Inventory Items
  @FXML private ImageView fishingRodIcon;
  @FXML private ImageView axeIcon;
  @FXML private ImageView fishIcon;
  @FXML private ImageView planksIcon;
  @FXML private ImageView blueOrb;
  @FXML private ImageView greenOrb;
  @FXML private ImageView redOrb;

  @FXML private Polygon codedText;
  @FXML private ImageView helpOne;
  @FXML private ImageView helpTwo;
  @FXML private ImageView helpThree;
  @FXML private ImageView settingsOne;
  @FXML private ImageView settingsTwo;
  @FXML private ImageView settingsThree;

  private TextToSpeech textToSpeech;
  private GameMaster gameMaster;
  private ChatMessage chatMessage;
  private int spamCount = 0;

  /** Initializes the room view, it is called when the room loads. */
  public void initialize() {
    // Bind the labels to the display values
    lblTimer.textProperty().bind(ControllerMethods.displayTime);
    lblTask.textProperty().bind(ControllerMethods.displayTask);
    lblHints.textProperty().bind(ControllerMethods.displayHints);

    // Bind the inventory images to their image properties
    fishingRodIcon.imageProperty().bind(ControllerMethods.fishingRodIconImageProperty);
    axeIcon.imageProperty().bind(ControllerMethods.axeIconImageProperty);
    fishIcon.imageProperty().bind(ControllerMethods.fishIconImageProperty);
    planksIcon.imageProperty().bind(ControllerMethods.planksIconImageProperty);
    blueOrb.imageProperty().bind(ControllerMethods.blueOrbImageProperty);
    greenOrb.imageProperty().bind(ControllerMethods.greenOrbImageProperty);
    redOrb.imageProperty().bind(ControllerMethods.redOrbImageProperty);

    // // Initialize game master object:
    // gameMaster = new GameMaster();
    // gameMaster.chatCompletionRequest();
    // gameMaster();
  }

  /** Opens the chat window with the game master. */
  @FXML
  private void openChat(MouseEvent event) {
    App.setScene(AppScene.CHAT);
  }

  @FXML
  private void onHelpHover(MouseEvent event) {
    helpTwo.setOpacity(1);
  }

  @FXML
  private void onHelpUnhover(MouseEvent event) {
    helpTwo.setOpacity(0);
  }

  @FXML
  private void onHelpPressed(MouseEvent event) {
    helpThree.setOpacity(1);
  }

  /**
   * Opens the help window GUI.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void onHelpReleased(MouseEvent event) {
    helpThree.setOpacity(0);

    // Store scene to stack
    SceneManager.sceneStack.push(AppScene.ROOM);

    App.setScene(AppScene.HELP);
  }

  @FXML
  private void onSettingsHover(MouseEvent event) {
    settingsTwo.setOpacity(1);
  }

  @FXML
  private void onSettingsUnhover(MouseEvent event) {
    settingsTwo.setOpacity(0);
  }

  @FXML
  private void onSettingsPressed(MouseEvent event) {
    settingsThree.setOpacity(1);
  }

  /**
   * Opens the settings scene.
   *
   * @param event
   */
  @FXML
  private void onSettingsReleased(MouseEvent event) {
    settingsThree.setOpacity(0);

    // Store scene to stack
    SceneManager.sceneStack.push(AppScene.ROOM);

    App.setScene(AppScene.SETTINGS);
  }

  /**
   * Updates the task label with a given message.
   *
   * @param message The message to be displayed.
   */
  public void updateTaskLabel(String message) {
    lblTask.setText("Task: " + message);
  }

  /**
   * Displays a dialog box with the given title, header text, and message.
   *
   * @param title the title of the dialog box
   * @param headerText the header text of the dialog box
   * @param message the message content of the dialog box
   */
  private void showDialog(String title, String headerText, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * When the right arrow is no longer hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void rightButtonUnhovered(MouseEvent event) {
    rightArrowHover.setOpacity(0);
  }

  /**
   * When the right arrow is hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void rightButtonHovered(MouseEvent event) {
    rightArrowHover.setOpacity(1);
  }

  /**
   * When the right arrow is pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void rightButtonClicked(MouseEvent event) {
    rightArrowPressed.setOpacity(1);
  }

  /**
   * When the right arrow is no longer pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void rightButtonUnclick(MouseEvent event) {
    rightArrowPressed.setOpacity(0);
    App.setScene(AppScene.FOREST);
  }

  /**
   * When the left arrow is hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonUnhovered(MouseEvent event) {
    leftArrowHover.setOpacity(0);
  }

  /**
   * When the left arrow is no longer hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonHovered(MouseEvent event) {
    leftArrowHover.setOpacity(1);
  }

  /**
   * When the left arrow pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonClicked(MouseEvent event) {
    leftArrowPressed.setOpacity(1);
  }

  /**
   * When the left arrow is no longer pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonUnclick(MouseEvent event) {
    leftArrowPressed.setOpacity(0);
    App.setScene(AppScene.LAVA);
  }

  /**
   * If the cabinet riddle is selected and solved, the user can interact with the cabinet to see
   * clue.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void cabinetClick(MouseEvent event) {
    // if the riddle has NOT been solved give help
    if (!GameState.isRiddleResolved) {
      spamCount++;

      if (spamCount == 5) {
        Notifications message2 =
            NotificationBuilder.createNotification(
                "Game Master:", "Open the chat to talk to me and get your first clue!", 6);
        message2.show();
      }
      return;
    }

    // If the item has already been clicked, dont let them click again.
    if (GameState.itemClicked) {
      return;
    }

    // if cabinet riddle is selected and solved solved:
    if (GameState.isCabinet && GameState.isRiddleResolved) {
      GameState.itemClicked = true;

      showDialog(
          "You've found a clue!",
          "TV Remote Found!",
          "You found the TV remote! Turn on the TV for your next clue!");

      updateTaskLabel("[Insert Task]");

      Notifications message =
          NotificationBuilder.createNotification("Game Master:", chatMessage.getContent(), 6);
      message.show();
    }
  }

  @FXML
  private void cabinetHover(MouseEvent event) {
    cabinetOutline.setOpacity(1);
  }

  @FXML
  private void cabinetUnhover(MouseEvent event) {
    cabinetOutline.setOpacity(0);
  }

  /**
   * If the rug riddle is selected and solved, the user can interact with the rug to see clue.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void carpetClick(MouseEvent event) {
    // if the riddle has NOT been solved give help
    if (!GameState.isRiddleResolved) {
      spamCount++;

      if (spamCount == 5) {
        Notifications message2 =
            NotificationBuilder.createNotification(
                "Game Master:", "Open the chat to talk to me and get your first clue!", 6);
        message2.show();
      }
      return;
    }

    // If the item has already been clicked, dont let them click again.
    if (GameState.itemClicked) {
      return;
    }

    // if rug riddle is selected and solved
    if (GameState.isRug && GameState.isRiddleResolved) {
      GameState.itemClicked = true;

      showDialog(
          "You've found a clue!",
          "TV Remote Found!",
          "You found the TV remote! Turn on the TV for your next clue!");

      updateTaskLabel("[Insert Task]");

      Notifications message =
          NotificationBuilder.createNotification("Game Master:", chatMessage.getContent(), 6);
      message.show();
    }
  }

  @FXML
  private void carpetHover(MouseEvent event) {
    rugOutline.setOpacity(1);
  }

  @FXML
  private void carpetUnhover(MouseEvent event) {
    rugOutline.setOpacity(0);
  }

  // Window
  @FXML
  private void paneClick(MouseEvent event) {}

  @FXML
  private void paneHover(MouseEvent event) {
    windowOutline.setOpacity(1);
  }

  @FXML
  private void paneUnhover(MouseEvent event) {
    windowOutline.setOpacity(0);
  }

  // Map
  @FXML
  private void mapClick(MouseEvent event) {
    map.setOpacity(0);
    mapOutline.setOpacity(0);
    GameState.isMapOnWall = false;
    codedText.setDisable(false);
    // Update Code
    if (GameState.isLightOn) {
      hiddenCode.setOpacity(1);
    } else {
      code.setOpacity(1);
      glowParticleOne.setOpacity(1);
      glowParticleTwo.setOpacity(1);
      glowParticleThree.setOpacity(1);
    }
  }

  @FXML
  private void mapHover(MouseEvent event) {
    if (GameState.isMapOnWall) {
      mapOutline.setOpacity(1);
    }
  }

  @FXML
  private void mapUnhover(MouseEvent event) {
    if (GameState.isMapOnWall) {
      mapOutline.setOpacity(0);
    }
  }

  // Terminal
  @FXML
  private void terminalClick(MouseEvent event) {
    // If the orbs have NOT been found, prompt user to find the orbs:
    if (GameState.isRoomOrbCollected
        && GameState.isForestOrbCollected
        && GameState.isCastleOrbCollected) {
      App.setScene(AppScene.TERMINAL);
    } else {
      Notifications message =
          NotificationBuilder.createNotification(
              "Game Master: ", "Find the orbs to access the terminal!", 5);
      message.show();
    }
  }

  @FXML
  private void terminalHover(MouseEvent event) {
    terminalOutline.setOpacity(1);
  }

  @FXML
  private void terminalUnhover(MouseEvent event) {
    terminalOutline.setOpacity(0);
  }

  // Light Switch
  @FXML
  private void switchClick(MouseEvent event) {
    if (GameState.isLightOn) {
      // Update Switch Images
      switchOn.setOpacity(0);
      switchOnOutline.setOpacity(0);
      switchOff.setOpacity(1);
      switchOffOutline.setOpacity(1);
      // Update Overlay
      darkOverlay.setOpacity(1);
      // Update Code
      if (!GameState.isMapOnWall) {
        hiddenCode.setOpacity(0);
        code.setOpacity(1);
        glowParticleOne.setOpacity(1);
        glowParticleTwo.setOpacity(1);
        glowParticleThree.setOpacity(1);
        GameState.isCodeFound = true;
        updateTask();
      }
      // Update GameState
      GameState.isLightOn = false;
    } else {
      // Update Switch Images
      switchOn.setOpacity(1);
      switchOnOutline.setOpacity(1);
      switchOff.setOpacity(0);
      switchOffOutline.setOpacity(0);
      // Update Overlay
      darkOverlay.setOpacity(0);
      // Update Code
      hiddenCode.setOpacity(1);
      code.setOpacity(0);
      glowParticleOne.setOpacity(0);
      glowParticleTwo.setOpacity(0);
      glowParticleThree.setOpacity(0);
      // Update GameState
      GameState.isLightOn = true;
    }
  }

  @FXML
  private void switchHover(MouseEvent event) {
    if (GameState.isLightOn) {
      switchOnOutline.setOpacity(1);
    } else {
      switchOffOutline.setOpacity(1);
    }
  }

  @FXML
  private void switchUnhover(MouseEvent event) {
    if (GameState.isLightOn) {
      switchOnOutline.setOpacity(0);
    } else {
      switchOffOutline.setOpacity(0);
    }
  }

  // Portal
  @FXML
  private void portalClick(MouseEvent event) {
    if (GameState.isPortalOpen) {
      GameState.isRoomEscaped = true;
      App.setScene(AppScene.GAMEFINISHED);
    }
  }

  @FXML
  private void portalHover(MouseEvent event) {
    portalBaseOutline.setOpacity(1);
    if (GameState.isOrbsPlaced) {
      portalOutline.setOpacity(1);
    } else {
      portalFrameOutline.setOpacity(1);
    }
  }

  @FXML
  private void portalUnhover(MouseEvent event) {
    portalBaseOutline.setOpacity(0);
    if (GameState.isOrbsPlaced) {
      portalOutline.setOpacity(0);
    } else {
      portalFrameOutline.setOpacity(0);
    }
  }

  private void turnPortalOn() {
    GameState.isOrbsPlaced = true;
    portal.setOpacity(1);
  }

  // Code
  @FXML
  private void codeClick(MouseEvent event) {
    if (GameState.isLightOn) {

    } else {

    }
  }

  @FXML
  private void codeHover(MouseEvent event) {
    if (!GameState.isMapOnWall && !GameState.isLightOn) {
      codeOutline.setOpacity(1);
    } else {
      hiddenCodeOutline.setOpacity(1);
    }
  }

  @FXML
  private void codeUnhover(MouseEvent event) {
    if (!GameState.isMapOnWall && !GameState.isLightOn) {
      codeOutline.setOpacity(0);
    } else {
      hiddenCodeOutline.setOpacity(0);
    }
  }

  /**
   * Allows user to enter a pin to unlock the door
   *
   * @param event Mouse click event.
   */
  @FXML
  private void doorClick(MouseEvent event) {
    // If riddle has not been resolved, prompt user to solve the riddle:
    if (!GameState.isRiddleResolved) {
      showDialog(
          "Info",
          "Door Locked! Seems like we need a pin...",
          "Solve the riddle to recieve a clue!");

      // Take user to chat window to solve riddle:
      App.setScene(AppScene.CHAT);
      return;
    }

    // If riddle has been solved but the item has not been clicked:
    if (GameState.isRiddleResolved && !GameState.itemClicked) {
      showDialog(
          "Info",
          "Door Locked! Seems like we need a pin...",
          "Use your answer from the riddle to recieve the next clue!");
      return;
    }

    // If riddle has been solved and item has been clicked, prompt user to escape
    if (GameState.isRiddleResolved && GameState.itemClicked) {
      // Switch scene to the keypad:
      App.setScene(AppScene.KEYPAD);
      return;
    }
  }

  /**
   * If the riddle has been solved, the user can interact with the TV to see clue.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void televisionClick(MouseEvent event) {

    // If the riddle has not been solved, give user help
    if (!GameState.isRiddleResolved) {
      spamCount++;

      if (spamCount == 5) {
        Notifications message2 =
            NotificationBuilder.createNotification(
                "Game Master:", "Open the chat to talk to me and get your first clue!", 6);
        message2.show();
      }
      return;
    }

    // if the item has been clicked, then change GUI to show the math problem on the screen
    if (GameState.itemClicked) {
      // Switch GUI to TV screen:
      App.setScene(AppScene.TELEVISION);
      updateTaskLabel("Enter the pin to the door!");
    }
  }

  /**
   * When the clock is clicked, the current local time is spoken.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void clockClick(MouseEvent event) {
    Task<Void> clockSpeakTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            textToSpeech = new TextToSpeech();
            textToSpeech.speak(
                "The current time is: "
                    + new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()));

            return null;
          }
        };

    Thread clockSpeakThread = new Thread(clockSpeakTask);
    clockSpeakThread.start();
  }

  /**
   * This method is called when the user clicks on the bookshelf:
   *
   * @param event Mouse click event.
   */
  @FXML
  private void bookClick(MouseEvent event) {
    Notifications message =
        NotificationBuilder.createNotification(
            "Game Master:", "Who even reads books these days!", 3);
    message.show();
  }

  /**
   * This method is called when the user clicks on the bookshelf:
   *
   * @param event Mouse click event.
   */
  @FXML
  void windowClick(MouseEvent event) {
    Notifications message =
        NotificationBuilder.createNotification("Game Master:", "Ha ha ha... yeah.. good luck.", 3);
    message.show();
  }

  /**
   * This method is called when the user does not talk to the game master to answer the riddle.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void helpUser(MouseEvent event) {

    spamCount++;

    // If the user clicks any part of the screen 6 times, help will be shown:
    if (spamCount == 5) {
      Notifications message2 =
          NotificationBuilder.createNotification(
              "Game Master:", "Open the chat to talk to me and get your first clue!", 6);
      message2.show();
    }
  }

  /**
   * This method is called when the user clicks on the rug or cabinet. It will call the game master
   * (powered by GPT) to generate a response.
   */
  private void gameMaster() {
    Task<Void> task =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            // Get game master response:
            ChatMessage msg =
                new ChatMessage(
                    "user",
                    GptPromptEngineering.chatWithGameMaster(
                        "about finally finding a television remote"));
            chatMessage = gameMaster.runGameMaster(msg);

            return null;
          }
        };

    Thread thread = new Thread(task);
    thread.start();
  }

  // Bottom Right Game Master Button
  @FXML
  private void gameMasterOnHover(MouseEvent event) {
    gameMasterDefault.setOpacity(0);
    gameMasterChat.setOpacity(1);
  }

  @FXML
  private void gameMasterOnUnhover(MouseEvent event) {
    gameMasterDefault.setOpacity(1);
    gameMasterChat.setOpacity(0);
  }

  @FXML
  private void gameMasterOnClick(MouseEvent event) {
    // Logic for which background GPT should have
    if (GameState.isMapOnWall) {
      setMainMapOpacity();
    } else if (!GameState.isMapOnWall) {
      setMainMapRemovedOpacity();
    }

    if (!GameState.isLightOn) {
      setMainDarkOpacity();
    }
    App.setScene(AppScene.CHAT);
  }
}
