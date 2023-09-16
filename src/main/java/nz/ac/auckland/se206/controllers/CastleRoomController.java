package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

    System.out.println("Check button pressed");
  }

  @FXML
  private void checkReleased(MouseEvent event) {
    checkButtonPressed.setOpacity(0);

    // TODO: check if the answer is correct
  }

  // Methods for chest animations:

}
