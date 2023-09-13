package nz.ac.auckland.se206.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameMaster;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager.AppScene;
import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.GptPromptEngineering;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;
import nz.ac.auckland.se206.speech.TextToSpeech;
import org.controlsfx.control.Notifications;

public class TelevisionController extends ControllerMethods {

  @FXML private Label televisionTimerLabel;

  private GameMaster gameMaster;
  private ChatMessage chatMessage;

  public void initialize() throws ApiProxyException {
    // Bind the timer label to the display time
    televisionTimerLabel.textProperty().bind(ControllerMethods.displayTime);

    gameMaster = new GameMaster();
    gameMaster.chatCompletionRequest();

    ChatMessage msg =
        new ChatMessage(
            "user",
            GptPromptEngineering.chatWithGameMaster(
                "how dumb it is to see maths on the television"));
    chatMessage = gameMaster.runGameMaster(msg);
  }

  /**
   * Change scene from TV GUI back to room GUI:
   *
   * @param event Mouse click on back button.
   */
  @FXML
  private void goBack(MouseEvent event) {
    // Create game master notification:
    Notifications message = NotificationBuilder.createNotification(chatMessage.getContent(), 6);
    message.show();

    App.setScene(AppScene.ROOM);
  }

  /** Speaks the clue to the user: */
  @FXML
  private void speakClue(MouseEvent event) {
    Task<Void> clueSpeakTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            TextToSpeech textToSpeech = new TextToSpeech();
            textToSpeech.speak("twenty plus eight");

            return null;
          }
        };

    Thread clueSpeakThread = new Thread(clueSpeakTask);
    clueSpeakThread.start();
  }
}
