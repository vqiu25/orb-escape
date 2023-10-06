package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameMaster;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager.AppScene;
import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.GptPromptEngineering;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;
import org.controlsfx.control.Notifications;

public class TutorialController extends ControllerMethods {

  private ChatMessage chatMessage;
  private GameMaster gameMaster;
  @FXML private ImageView startOneButton;
  @FXML private ImageView startTwoButton;
  @FXML private ImageView startThreeButton;
  @FXML private Label taskText;

  public void initialize() throws ApiProxyException {

    // Update tutorial text
    taskText.setText("If in doubt, refer to the task list\nat the top left!");

    // Initialize game master object:
    gameMaster = new GameMaster();
    gameMaster.chatCompletionRequest();

    // Pass message to AI and fetch response:
    ChatMessage msg =
        new ChatMessage(
            "user",
            GptPromptEngineering.chatWithGameMaster(
                "being stuck in this room and you need help escaping"));
    chatMessage = gameMaster.runGameMaster(msg);
  }

  /**
   * Switches the scene from the tutorial GUI to start GUI and starts timer.
   *
   * @throws IOException
   */
  private void onLaunchGame() throws IOException {

    // Start timer, update task and update hints remaining
    startTimer();
    updateTask();
    updateHintsRemaining();

    // Fetch message from AI and show:
    Notifications message =
        NotificationBuilder.createNotification("Game Master:", chatMessage.getContent(), 7);
    message.show();
    App.setScene(AppScene.ROOM);
  }

  @FXML
  private void startHover(MouseEvent event) {
    startTwoButton.setOpacity(1);
  }

  @FXML
  private void startUnhover(MouseEvent event) {
    startTwoButton.setOpacity(0);
  }

  @FXML
  private void startPressed(MouseEvent event) {
    startThreeButton.setOpacity(1);
  }

  @FXML
  private void startReleased(MouseEvent event) throws IOException {
    startThreeButton.setOpacity(0);
    onLaunchGame();
  }
}
