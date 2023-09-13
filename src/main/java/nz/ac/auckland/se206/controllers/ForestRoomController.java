package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class ForestRoomController extends ControllerMethods {
  @FXML private ImageView leftArrow;
  @FXML private ImageView leftArrowHover;
  @FXML private ImageView leftArrowPressed;
  @FXML private Label roomTimerLabel;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);
  }

  /**
   * When the left arrow is hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonUnhovered(MouseEvent event) {
    leftArrowHover.setOpacity(0);
  }

  /**
   * When the left arrow is no longer hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonHovered(MouseEvent event) {
    leftArrowHover.setOpacity(1);
  }

  /**
   * When the left arrow pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonClicked(MouseEvent event) {
    leftArrowPressed.setOpacity(1);
  }

  /**
   * When the left arrow is no longer pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonUnclick(MouseEvent event) {
    leftArrowPressed.setOpacity(0);
    App.setScene(AppScene.ROOM);
  }
}
