package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class BridgeGameController {

  @FXML private ImageView smallPlankOutline;
  @FXML private Rectangle smallRectangle;

  @FXML private ImageView mediumPlankOutline;
  @FXML private Rectangle mediumRectangle;

  @FXML private ImageView largePlankOutline;
  @FXML private Rectangle largeRectangle;

  @FXML
  private void onBack() {
    App.setScene(AppScene.LAVA);
  }

  @FXML
  private void smallDragged(MouseEvent event) {
    System.out.println("Small plank dragged");
  }

  @FXML
  private void mediumDragged(MouseEvent event) {
    System.out.println("Medium plank dragged");
  }

  @FXML
  private void largeDragged(MouseEvent event) {
    System.out.println("Large plank dragged");
  }

  @FXML
  private void checkAnswer() {
    System.out.println("Check answer");
  }
}
