package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;

public class HelpController extends ControllerMethods {

  @FXML private Label lblTimer;
  @FXML private Label lblTask;
  @FXML private Label lblHints;

  @FXML private ImageView backTwoButton;
  @FXML private ImageView backThreeButton;
  @FXML private Label taskText;

  public void initialize() throws ApiProxyException {

    // Update tutorial text
    taskText.setText("If in doubt, refer to the task list\nat the top left!");

    // Bind the labels to the display values
    lblTimer.textProperty().bind(ControllerMethods.displayTime);
    lblTask.textProperty().bind(ControllerMethods.displayTask);
    lblHints.textProperty().bind(ControllerMethods.displayHints);
  }

  @FXML
  private void backHover(MouseEvent event) {
    backTwoButton.setOpacity(1);
  }

  @FXML
  private void backUnhover(MouseEvent event) {
    backTwoButton.setOpacity(0);
  }

  @FXML
  private void backPressed(MouseEvent event) {
    backThreeButton.setOpacity(1);
  }

  /** Switches the scene from the help GUI to start GUI and restart timer */
  @FXML
  private void backReleased(MouseEvent event) {
    backThreeButton.setOpacity(0);

    // Return to previous scene by popping stack:
    App.setScene(GameState.lastScene);
  }
}
