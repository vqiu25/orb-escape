package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class StartScreenController extends ControllerMethods {
  @FXML private ImageView startOneButton;
  @FXML private ImageView startTwoButton;
  @FXML private ImageView startThreeButton;
  @FXML private ImageView optionsOneButton;
  @FXML private ImageView optionsTwoButton;
  @FXML private ImageView optionsThreeButton;
  @FXML private ImageView quitOneButton;
  @FXML private ImageView quitTwoButton;
  @FXML private ImageView quitThreeButton;

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
  private void startReleased(MouseEvent event) {
    startThreeButton.setOpacity(0);
    App.setScene(AppScene.STORY);
  }

  @FXML
  private void optionsHover(MouseEvent event) {
    optionsTwoButton.setOpacity(1);
  }

  @FXML
  private void optionsUnhover(MouseEvent event) {
    optionsTwoButton.setOpacity(0);
  }

  @FXML
  private void optionsPressed(MouseEvent event) {
    optionsThreeButton.setOpacity(1);
  }

  @FXML
  private void optionsReleased(MouseEvent event) {
    optionsThreeButton.setOpacity(0);
    App.setScene(AppScene.OPTIONS);
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

  @FXML
  private void quitReleased(MouseEvent event) {
    System.exit(0);
  }
}
