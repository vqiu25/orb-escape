package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;
import org.controlsfx.control.Notifications;

public class SettingsController extends ControllerMethods {

  @FXML private Label lblTimer;
  @FXML private Label lblTask;
  @FXML private Label lblHints;

  @FXML private ImageView backButtonTwo;
  @FXML private ImageView backButtonThree;
  @FXML private ImageView restartTwoButton;
  @FXML private ImageView restartThreeButton;
  @FXML private ImageView quitTwoButton;
  @FXML private ImageView quitThreeButton;
  @FXML private Rectangle backButton;
  @FXML private Rectangle restartButton;

  public void initialize() throws ApiProxyException {
    // Bind the labels to the display values
    lblTimer.textProperty().bind(ControllerMethods.displayTime);
    lblTask.textProperty().bind(ControllerMethods.displayTask);
    lblHints.textProperty().bind(ControllerMethods.displayHints);
  }

  // Back Button
  @FXML
  private void backHover(MouseEvent event) {
    backButtonTwo.setOpacity(1);
  }

  @FXML
  private void backUnhover(MouseEvent event) {
    backButtonTwo.setOpacity(0);
  }

  @FXML
  private void backPressed(MouseEvent event) {
    backButtonThree.setOpacity(1);
  }

  /**
   * Switches the scene from the pause GUI to room GUI - resuming the game.
   *
   * @param event
   */
  @FXML
  private void backReleased(MouseEvent event) {
    backButtonThree.setOpacity(0);
    // Return to previous scene by popping stack:
    App.setScene(SceneManager.sceneStack.pop());
  }

  private void showRestartNotification() {
    Notifications notification =
        NotificationBuilder.createNotification("Game Master: ", "Game restarting!", 10);
    notification.show();
  }

  @FXML
  private void restartHover(MouseEvent event) {
    restartTwoButton.setOpacity(1);
  }

  @FXML
  private void restartUnhover(MouseEvent event) {
    restartTwoButton.setOpacity(0);
  }

  // Restart Button
  @FXML
  private void restartPressed(MouseEvent event) {
    restartThreeButton.setOpacity(1);
  }

  @FXML
  private void restartReleased(MouseEvent event) throws IOException {
    // TODO: SHOW IMAGE OVERLAY THAT BLOCKS ALL OTHER BUTTONS

    showRestartNotification();

    restartThreeButton.setOpacity(0);
    System.out.println("Restarting game...");

    // Restart game:
    restartGame();
  }

  // Quit Button
  @FXML
  private void quitHover(MouseEvent event) {
    quitTwoButton.setOpacity(1);
  }

  @FXML
  private void quitUnhover(MouseEvent event) {
    quitTwoButton.setOpacity(0);
  }

  @FXML
  private void quitPressed(MouseEvent event) {
    quitThreeButton.setOpacity(1);
  }

  /**
   * Quits the game, closing the application.
   *
   * @param event
   */
  @FXML
  private void quitReleased(MouseEvent event) {
    quitThreeButton.setOpacity(0);
    System.exit(0);
  }
}
