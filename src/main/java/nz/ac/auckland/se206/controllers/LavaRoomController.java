package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class LavaRoomController extends ControllerMethods {
  @FXML private ImageView rightArrow;
  @FXML private ImageView rightArrowHover;
  @FXML private ImageView rightArrowPressed;
  @FXML private Label roomTimerLabel;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);
  }

  @FXML
  private void openChat() {
    App.setScene(AppScene.CHAT);
  }

  @FXML
  private void openHelp() {
    App.setScene(AppScene.HELP);
  }

  @FXML
  private void openSettings() {
    App.setScene(AppScene.SETTINGS);
  }

  /**
   * When the right arrow is no longer hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void rightButtonUnhovered(MouseEvent event) {
    rightArrowHover.setOpacity(0);
  }

  /**
   * When the right arrow is hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void rightButtonHovered(MouseEvent event) {
    rightArrowHover.setOpacity(1);
  }

  /**
   * When the right arrow is pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void rightButtonClicked(MouseEvent event) {
    rightArrowPressed.setOpacity(1);
  }

  /**
   * When the right arrow is no longer pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void rightButtonUnclick(MouseEvent event) {
    rightArrowPressed.setOpacity(0);
    App.setScene(AppScene.ROOM);
  }
}
