package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameOverController extends ControllerMethods {

  @FXML ImageView restartTwoButton;
  @FXML ImageView restartThreeButton;
  @FXML ImageView quitTwoButton;
  @FXML ImageView quitThreeButton;

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
  private void restartReleased(MouseEvent event) {
    restartThreeButton.setOpacity(0);
    System.out.println("Restarting game...");
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
}
