package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.DragImage;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager.AppScene;
import org.controlsfx.control.Notifications;

public class BridgeGameController extends ControllerMethods {

  @FXML private Label roomTimerLabel;

  @FXML private ImageView smallPlankOutline;
  @FXML private Rectangle smallRectangle;
  DragImage imageSmall = new DragImage();

  @FXML private ImageView mediumPlankOutline;
  @FXML private Rectangle mediumRectangle;
  DragImage imageMedium = new DragImage();

  @FXML private ImageView largePlankOutline;
  @FXML private Rectangle largeRectangle;
  DragImage imageLarge = new DragImage();

  // Inventory Items
  @FXML private ImageView fishingRodIcon;
  @FXML private ImageView axeIcon;
  @FXML private ImageView fishIcon;
  @FXML private ImageView planksIcon;
  @FXML private ImageView blueOrb;
  @FXML private ImageView greenOrb;
  @FXML private ImageView redOrb;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);

    // Bind the inventory images to their image properties
    fishingRodIcon.imageProperty().bind(ControllerMethods.fishingRodIconImageProperty);
    axeIcon.imageProperty().bind(ControllerMethods.axeIconImageProperty);
    fishIcon.imageProperty().bind(ControllerMethods.fishIconImageProperty);
    planksIcon.imageProperty().bind(ControllerMethods.planksIconImageProperty);
    blueOrb.imageProperty().bind(ControllerMethods.blueOrbImageProperty);
    greenOrb.imageProperty().bind(ControllerMethods.greenOrbImageProperty);
    redOrb.imageProperty().bind(ControllerMethods.redOrbImageProperty);
  }

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
      removePlanks();

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
