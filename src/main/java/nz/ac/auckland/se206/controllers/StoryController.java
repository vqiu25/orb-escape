package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class StoryController extends ControllerMethods {

  // Buttons are now next instead of back
  @FXML private ImageView backOneButtn;
  @FXML private ImageView backTwoButton;
  @FXML private ImageView backThreeButton;

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

  @FXML
  private void backReleased(MouseEvent event) {
    backThreeButton.setOpacity(0);
    App.setScene(AppScene.TUTORIAL);
  }
}
