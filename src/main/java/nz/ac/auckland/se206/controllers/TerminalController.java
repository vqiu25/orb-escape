package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.DragImage;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class TerminalController extends ControllerMethods {

  @FXML private Label roomTimerLabel;

  // Back Button:
  @FXML private ImageView backButtonOne;
  @FXML private ImageView backButtonTwo;
  @FXML private ImageView backButtonThree;

  // Check Button:
  @FXML private ImageView redOneButton;
  @FXML private ImageView redTwoButton;
  @FXML private ImageView redThreeButton;

  // Blue Orb:
  @FXML private ImageView blueOrb;
  @FXML private ImageView blueOrbOutline;
  @FXML private ImageView blueOrbFixed;
  @FXML private Rectangle blueOrbPlaced;
  DragImage blueOrbImage;

  // Green Orb:
  @FXML private ImageView greenOrb;
  @FXML private ImageView greenOrbOutline;
  @FXML private ImageView greenOrbFixed;
  @FXML private Rectangle greenOrbPlaced;
  DragImage greenOrbImage;

  // Red Orb:
  @FXML private ImageView redOrb;
  @FXML private ImageView redOrbOutline;
  @FXML private ImageView redOrbFixed;
  @FXML private Rectangle redOrbPlaced;
  DragImage redOrbImage;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);

    // Initialise the drag and drop helper:
    blueOrbImage = new DragImage(blueOrbOutline, blueOrb, blueOrbFixed, blueOrbPlaced);
    greenOrbImage = new DragImage(greenOrbOutline, greenOrb, greenOrbFixed, greenOrbPlaced);
    redOrbImage = new DragImage(redOrbOutline, redOrb, redOrbFixed, redOrbPlaced);
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

    // If all the orbs have been placed correctly, allow user to escape (update isOrbsPlaced), else
    // tell user to try again
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

  // Blue Orb Logic:
  @FXML
  private void blueHovered() {
    blueOrb.setOpacity(0);
  }

  @FXML
  private void blueUnhovered() {
    blueOrb.setOpacity(1);
  }

  @FXML
  private void blueDragged(MouseEvent event) {
    blueOrb.setOpacity(0);

    blueOrbImage.drag(event);
  }

  @FXML
  private void blueReleased() {
    blueOrbImage.released();
  }

  // Green Orb Logic:
  @FXML
  private void greenHovered() {
    greenOrb.setOpacity(0);
  }

  @FXML
  private void greenUnhovered() {
    greenOrb.setOpacity(1);
  }

  @FXML
  private void greenDragged(MouseEvent event) {
    greenOrb.setOpacity(0);

    greenOrbImage.drag(event);
  }

  @FXML
  private void greenReleased() {
    greenOrbImage.released();
  }

  // Red Orb Logic:
  @FXML
  private void redHovered() {
    redOrb.setOpacity(0);
  }

  @FXML
  private void redUnhovered() {
    redOrb.setOpacity(1);
  }

  @FXML
  private void redDragged(MouseEvent event) {
    redOrb.setOpacity(0);

    redOrbImage.drag(event);
  }

  @FXML
  private void redReleased() {
    redOrbImage.released();
  }
}
