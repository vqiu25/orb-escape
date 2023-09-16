package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.DragImage;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager.AppScene;
import org.controlsfx.control.Notifications;

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
    imageSmall.dragImage(smallPlankOutline, smallRectangle);
  }

  @FXML
  private void mediumDragged(MouseEvent event) {
    imageMedium.dragImage(mediumPlankOutline, mediumRectangle);
  }

  @FXML
  private void largeDragged(MouseEvent event) {
    imageLarge.dragImage(largePlankOutline, largeRectangle);
  }

  @FXML
  private void checkAnswer() {
    if (imageSmall.isCorrectPosition()
        && imageMedium.isCorrectPosition()
        && imageLarge.isCorrectPosition()) {

      // Update game state
      GameState.isLavaGameCompleted = true;

      // Get lava room controller and change bridge state
      LavaRoomController lavaRoomController = App.getLavaRoomController();
      lavaRoomController.setFixedBridge();

      // Notify user that the bridge has been fixed:
      Notifications message =
          NotificationBuilder.createNotification(
              "Game Master:", "Well done! You have fixed the bridge!", 5);
      message.show();
    } else {
      Notifications message =
          NotificationBuilder.createNotification("Game Master:", "Try again!", 5);
      message.show();
    }
  }
}
