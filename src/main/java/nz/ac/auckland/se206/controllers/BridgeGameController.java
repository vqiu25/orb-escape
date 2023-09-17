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

  // Back Button:
  @FXML private ImageView backButton;
  @FXML private ImageView backButtonHovered;
  @FXML private ImageView backButtonPressed;

  // Check Button:
  @FXML private ImageView checkButton;
  @FXML private ImageView checkButtonHovered;
  @FXML private ImageView checkButtonPressed;

  // Small Planks:
  @FXML private ImageView smallPlank;
  @FXML private ImageView smallPlankOutline;
  @FXML private ImageView smallFixed;
  @FXML private Rectangle smallRectangle;
  DragImage imageSmall;

  // Medium Planks:
  @FXML private ImageView mediumPlank;
  @FXML private ImageView mediumPlankOutline;
  @FXML private ImageView mediumFixed;
  @FXML private Rectangle mediumRectangle;
  DragImage imageMedium;

  // Large Planks:
  @FXML private ImageView largePlank;
  @FXML private ImageView largePlankOutline;
  @FXML private ImageView largeFixed;
  @FXML private Rectangle largeRectangle;
  DragImage imageLarge;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);

    imageSmall = new DragImage(smallPlankOutline, smallPlank, smallFixed, smallRectangle);
    imageMedium = new DragImage(mediumPlankOutline, mediumPlank, mediumFixed, mediumRectangle);
    imageLarge = new DragImage(largePlankOutline, largePlank, largeFixed, largeRectangle);
  }

  // Back buttong logic:
  @FXML
  private void backHovered() {
    backButtonHovered.setOpacity(1);
  }

  @FXML
  private void backUnhovered() {
    backButtonHovered.setOpacity(0);
  }

  @FXML
  private void backPressed() {
    backButtonPressed.setOpacity(1);
  }

  @FXML
  private void backReleased() {
    backButtonPressed.setOpacity(0);

    // Change scene to lava room
    App.setScene(AppScene.LAVA);
  }

  // Check button logic:
  @FXML
  private void checkHovered() {
    checkButtonHovered.setOpacity(1);
  }

  @FXML
  private void checkUnhovered() {
    checkButtonHovered.setOpacity(0);
  }

  @FXML
  private void checkPressed() {
    checkButtonPressed.setOpacity(1);
  }

  @FXML
  private void checkReleased() {
    checkButtonPressed.setOpacity(0);

    // Check if planks are in the right place:
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

  // Small Plank Logic:
  @FXML
  private void smallHovered() {
    smallPlank.setOpacity(0);
  }

  @FXML
  private void smallUnhovered() {
    smallPlank.setOpacity(1);
  }

  @FXML
  private void smallDragged(MouseEvent event) {
    // Disable non outlined image
    smallPlank.setOpacity(0);

    imageSmall.drag(event);
  }

  @FXML
  void smallReleased() {
    imageSmall.released();
  }

  // Medium Plank Logic:
  @FXML
  private void mediumHovered() {
    mediumPlank.setOpacity(0);
  }

  @FXML
  private void mediumUnhovered() {
    mediumPlank.setOpacity(1);
  }

  @FXML
  private void mediumDragged(MouseEvent event) {
    // Disable non outlined image
    mediumPlank.setOpacity(0);

    imageMedium.drag(event);
  }

  @FXML
  void mediumReleased() {
    imageMedium.released();
  }

  // Large Plank Logic:
  @FXML
  private void largeHovered() {
    largePlank.setOpacity(0);
  }

  @FXML
  private void largeUnhovered() {
    largePlank.setOpacity(1);
  }

  @FXML
  private void largeDragged(MouseEvent event) {
    // Disable non outlined image
    largePlank.setOpacity(0);

    imageLarge.drag(event);
  }

  @FXML
  void largeReleased() {
    imageLarge.released();
  }
}
