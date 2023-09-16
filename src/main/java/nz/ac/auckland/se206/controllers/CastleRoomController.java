package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.NotificationBuilder;
import org.controlsfx.control.Notifications;

public class CastleRoomController {

  // Back buttons
  @FXML private ImageView backButton;
  @FXML private ImageView backButtonHovered;
  @FXML private ImageView backButtonPressed;

  // Check buttons
  @FXML private ImageView checkButton;
  @FXML private ImageView checkButtonHovered;
  @FXML private ImageView checkButtonPressed;

  // Chest images
  @FXML private ImageView chestLocked;
  @FXML private ImageView chestOpenedOrb;
  @FXML private ImageView chestOpenedOrbOutline;
  @FXML private ImageView chestEmpty;

  // Lock 1:
  @FXML private ImageView lockOneIncrement;
  @FXML private ImageView lockOneIncrementHovered;
  @FXML private ImageView lockOneIncrementPressed;

  @FXML private ImageView lockOneDecrement;
  @FXML private ImageView lockOneDecrementHovered;
  @FXML private ImageView lockOneDecrementPressed;

  @FXML private Label lockOneNumber;
  private int lockOneValue = 0;

  // Lock 2:
  @FXML private ImageView lockTwoIncrement;
  @FXML private ImageView lockTwoIncrementHovered;
  @FXML private ImageView lockTwoIncrementPressed;

  @FXML private ImageView lockTwoDecrement;
  @FXML private ImageView lockTwoDecrementHovered;
  @FXML private ImageView lockTwoDecrementPressed;

  @FXML private Label lockTwoNumber;
  private int lockTwoValue = 0;

  // Lock 3:
  @FXML private ImageView lockThreeIncrement;
  @FXML private ImageView lockThreeIncrementHovered;
  @FXML private ImageView lockThreeIncrementPressed;

  @FXML private ImageView lockThreeDecrement;
  @FXML private ImageView lockThreeDecrementHovered;
  @FXML private ImageView lockThreeDecrementPressed;

  @FXML private Label lockThreeNumber;
  private int lockThreeValue = 0;

  // Methods for back button animations:
  @FXML
  private void backHover(MouseEvent event) {
    backButtonHovered.setOpacity(1);
  }

  @FXML
  private void backUnhover(MouseEvent event) {
    backButtonHovered.setOpacity(0);
  }

  @FXML
  private void backPressed(MouseEvent event) {
    backButtonPressed.setOpacity(1);
  }

  @FXML
  private void backReleased(MouseEvent event) {
    backButtonPressed.setOpacity(0);

    System.out.println("Back button pressed");

    // TODO REMOVE THIS Switch back to lava room scene
    // App.setScene(AppScene.LAVA);
  }

  // Methods for check button animations:
  @FXML
  private void checkHover(MouseEvent event) {
    checkButtonHovered.setOpacity(1);
  }

  @FXML
  private void checkUnhover(MouseEvent event) {
    checkButtonHovered.setOpacity(0);
  }

  @FXML
  private void checkPressed(MouseEvent event) {
    checkButtonPressed.setOpacity(1);

    if (lockOneValue == 2 && lockTwoValue == 0 && lockThreeValue == 6) {
      // Notify the user that the answer is correct:
      Notifications message =
          NotificationBuilder.createNotification(
              "Game Master:", "Ooooooh, I wonder whats inside!", 5);
      message.show();

      // Show the chest with the orb inside:
      chestOpenedOrb.setOpacity(1);

    } else {
      // Notify the user that the answer is incorrect:
      Notifications message =
          NotificationBuilder.createNotification("Game Master:", "Try again!", 5);
      message.show();
    }
  }

  @FXML
  private void checkReleased(MouseEvent event) {
    checkButtonPressed.setOpacity(0);

    // TODO: check if the answer is correct
  }

  // Methods for pad lock animations:
  // Lock 1:
  @FXML
  private void lockOneIncrementHover(MouseEvent event) {
    lockOneIncrementHovered.setOpacity(1);
  }

  @FXML
  private void lockOneIncrementUnhover(MouseEvent event) {
    lockOneIncrementHovered.setOpacity(0);
  }

  @FXML
  private void lockOneIncrementPressed(MouseEvent event) {
    lockOneIncrementPressed.setOpacity(1);
  }

  @FXML
  private void lockOneIncrementReleased(MouseEvent event) {
    lockOneIncrementPressed.setOpacity(0);

    if (lockOneValue == 9) {
      return;
    }

    lockOneNumber.setText(String.valueOf(++lockOneValue));
  }

  @FXML
  private void lockOneDecrementHover(MouseEvent event) {
    lockOneDecrementHovered.setOpacity(1);
  }

  @FXML
  private void lockOneDecrementUnhover(MouseEvent event) {
    lockOneDecrementHovered.setOpacity(0);
  }

  @FXML
  private void lockOneDecrementPressed(MouseEvent event) {
    lockOneDecrementPressed.setOpacity(1);
  }

  @FXML
  private void lockOneDecrementReleased(MouseEvent event) {
    lockOneDecrementPressed.setOpacity(0);

    if (lockOneValue == 0) {
      return;
    }

    lockOneNumber.setText(String.valueOf(--lockOneValue));
  }

  // Lock 2:
  @FXML
  private void lockTwoIncrementHover(MouseEvent event) {
    lockTwoIncrementHovered.setOpacity(1);
  }

  @FXML
  private void lockTwoIncrementUnhover(MouseEvent event) {
    lockTwoIncrementHovered.setOpacity(0);
  }

  @FXML
  private void lockTwoIncrementPressed(MouseEvent event) {
    lockTwoIncrementPressed.setOpacity(1);
  }

  @FXML
  private void lockTwoIncrementReleased(MouseEvent event) {
    lockTwoIncrementPressed.setOpacity(0);

    if (lockTwoValue == 9) {
      return;
    }

    lockTwoNumber.setText(String.valueOf(++lockTwoValue));
  }

  @FXML
  private void lockTwoDecrementHover(MouseEvent event) {
    lockTwoDecrementHovered.setOpacity(1);
  }

  @FXML
  private void lockTwoDecrementUnhover(MouseEvent event) {
    lockTwoDecrementHovered.setOpacity(0);
  }

  @FXML
  private void lockTwoDecrementPressed(MouseEvent event) {
    lockTwoDecrementPressed.setOpacity(1);
  }

  @FXML
  private void lockTwoDecrementReleased(MouseEvent event) {
    lockTwoDecrementPressed.setOpacity(0);

    if (lockTwoValue == 0) {
      return;
    }

    lockTwoNumber.setText(String.valueOf(--lockTwoValue));
  }

  // Lock 3:
  @FXML
  private void lockThreeIncrementHover(MouseEvent event) {
    lockThreeIncrementHovered.setOpacity(1);
  }

  @FXML
  private void lockThreeIncrementUnhover(MouseEvent event) {
    lockThreeIncrementHovered.setOpacity(0);
  }

  @FXML
  private void lockThreeIncrementPressed(MouseEvent event) {
    lockThreeIncrementPressed.setOpacity(1);
  }

  @FXML
  private void lockThreeIncrementReleased(MouseEvent event) {
    lockThreeIncrementPressed.setOpacity(0);

    if (lockThreeValue == 9) {
      return;
    }

    lockThreeNumber.setText(String.valueOf(++lockThreeValue));
  }

  @FXML
  private void lockThreeDecrementHover(MouseEvent event) {
    lockThreeDecrementHovered.setOpacity(1);
  }

  @FXML
  private void lockThreeDecrementUnhover(MouseEvent event) {
    lockThreeDecrementHovered.setOpacity(0);
  }

  @FXML
  private void lockThreeDecrementPressed(MouseEvent event) {
    lockThreeDecrementPressed.setOpacity(1);
  }

  @FXML
  private void lockThreeDecrementReleased(MouseEvent event) {
    lockThreeDecrementPressed.setOpacity(0);

    if (lockThreeValue == 0) {
      return;
    }

    lockThreeNumber.setText(String.valueOf(--lockThreeValue));
  }

  // Methods for chest animations:
  @FXML
  private void orbHovered(MouseEvent event) {
    chestOpenedOrbOutline.setOpacity(1);
  }

  @FXML
  private void orbUnhovered(MouseEvent event) {
    chestOpenedOrbOutline.setOpacity(0);
  }

  @FXML
  private void orbPressed(MouseEvent event) {
    chestEmpty.setOpacity(1);

    // TODO: put the orb into inventory
  }
}
