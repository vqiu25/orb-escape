package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.DragImage;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class BridgeGameController {

  @FXML private ImageView smallPlankOutline;
  @FXML private Rectangle smallRectangle;
  DragImage imageSmall = new DragImage();

  @FXML private ImageView mediumPlankOutline;
  @FXML private Rectangle mediumRectangle;
  DragImage imageMedium = new DragImage();

  @FXML private ImageView largePlankOutline;
  @FXML private Rectangle largeRectangle;
  DragImage imageLarge = new DragImage();

  @FXML
  private void onBack() {
    App.setScene(AppScene.LAVA);
  }

  @FXML
  private void smallDragged(MouseEvent event) {
    System.out.println("Small plank dragged");
    imageSmall.dragImage(smallPlankOutline, smallRectangle);
  }

  @FXML
  private void mediumDragged(MouseEvent event) {
    System.out.println("Medium plank dragged");
    imageMedium.dragImage(mediumPlankOutline, mediumRectangle);
  }

  @FXML
  private void largeDragged(MouseEvent event) {
    System.out.println("Large plank dragged");
    imageLarge.dragImage(largePlankOutline, largeRectangle);
  }

  @FXML
  private void checkAnswer() {
    System.out.println("Check answer");
  }
}
