package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameFinishedController extends ControllerMethods {
  @FXML private Label lblTitle;
  @FXML private Label lblSubtitle;

  @FXML private ImageView restartTwoButton;
  @FXML private ImageView restartThreeButton;
  @FXML private ImageView quitTwoButton;
  @FXML private ImageView quitThreeButton;

  // Restart images:
  @FXML private ImageView restartingBackground;
  @FXML private ImageView restartingAnimation;

  public void initialize() {
    // Bind the labels to the message values
    lblTitle.textProperty().bind(ControllerMethods.titleMessage);
    lblSubtitle.textProperty().bind(ControllerMethods.subtitleMessage);
  }

  @FXML
  private void restartHover(MouseEvent event) {
    restartTwoButton.setOpacity(1);
  }

  @FXML
  private void restartUnhover(MouseEvent event) {
    restartTwoButton.setOpacity(0);
  }

  @FXML
  private void restartPressed(MouseEvent event) {
    restartThreeButton.setOpacity(1);
  }

  @FXML
  private void restartReleased(MouseEvent event) throws IOException {
    // Enable restarting images:
    restartingBackground.setDisable(false);
    restartingAnimation.setDisable(false);
    restartingBackground.setOpacity(1);
    restartingAnimation.setOpacity(1);

    restartThreeButton.setOpacity(0);
    System.out.println("Restarting game...");

    // Restart game:
    restartGame();
  }

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

  /** Quits the game. */
  @FXML
  private void quitReleased(MouseEvent event) {
    quitThreeButton.setOpacity(0);
    System.exit(0);
  }

  public void disableRestartingImages() {
    restartingBackground.setDisable(true);
    restartingAnimation.setDisable(true);
    restartingBackground.setOpacity(0);
    restartingAnimation.setOpacity(0);
  }
}
