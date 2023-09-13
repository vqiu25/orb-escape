package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;

public class HelpController extends ControllerMethods {

  @FXML private Label helpTimerLabel;

  public void initialize() throws ApiProxyException {
    // Bind the timer label to the display time
    helpTimerLabel.textProperty().bind(ControllerMethods.displayTime);
  }

  /** Switches the scene from the help GUI to start GUI and restart timer */
  @FXML
  private void onResumeGame(MouseEvent event) {
    App.setScene(AppScene.ROOM);
  }
}
