package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class SettingsController extends ControllerMethods {

  /**
   * Switches the scene from the pause GUI to room GUI - resuming the game.
   *
   * @param event
   */
  @FXML
  private void resumeGame(MouseEvent event) {
    App.setScene(AppScene.ROOM);
  }

  /**
   * Quits the game, closing the application.
   *
   * @param event
   */
  @FXML
  private void quitGame(MouseEvent event) {
    System.exit(0);
  }
}
