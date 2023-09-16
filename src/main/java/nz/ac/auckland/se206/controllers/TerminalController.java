package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class TerminalController extends ControllerMethods {
  @FXML private Label roomTimerLabel;
  @FXML private ImageView backButtonOne;
  @FXML private ImageView backButtonTwo;
  @FXML private ImageView backButtonThree;
  @FXML private ImageView redOneButton;
  @FXML private ImageView redTwoButton;
  @FXML private ImageView redThreeButton;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);
  }

  // Red Button
  @FXML
  private void redButtonHover(MouseEvent event) {
    redTwoButton.setOpacity(1);
  }

  @FXML
  private void redButtonUnhover(MouseEvent event) {
    redTwoButton.setOpacity(0);
  }

  @FXML
  private void redButtonPressed(MouseEvent event) {
    redThreeButton.setOpacity(1);
  }

  @FXML
  private void redButtonReleased(MouseEvent event) {
    redThreeButton.setOpacity(0);
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

  @FXML
  private void backReleased(MouseEvent event) {
    backButtonThree.setOpacity(0);
    App.setScene(AppScene.ROOM);
  }
}
