package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class GameOverController extends ControllerMethods {

  /** Quits the game. */
  @FXML
  private void quitGame(MouseEvent event) {
    System.exit(0);
  }
}
