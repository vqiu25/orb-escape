package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class LavaRoomController extends ControllerMethods {

  @FXML private Label roomTimerLabel;

  @FXML private ImageView rightArrow;
  @FXML private ImageView rightArrowHover;
  @FXML private ImageView rightArrowPressed;

  // Door state
  @FXML private ImageView doorOutline;

  // Dragon state
  @FXML private ImageView dragon;
  @FXML private ImageView dragonOutline;

  // Normal bridge state
  @FXML private ImageView bridge;
  @FXML private ImageView bridgeOutline;

  // Broken bridge state
  @FXML private ImageView brokenBridge;
  @FXML private ImageView brokenBridgeOutline;

  // Fixed broken bridge state
  @FXML private ImageView fixedBridge;
  @FXML private ImageView fixedBridgeOutline;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);

    // Based on minigame selected, either show dragon scenario or broken bridge scenario:
    if (GameState.isLavaBridge && GameState.isForrestTreeChopping) {
      // Minigame 1: Bridge is Broken - Disable dragon components and fixed bridge components

      // Disable bridge components
      bridge.setOpacity(0);
      bridge.setDisable(true);
      bridgeOutline.setDisable(true);

      // Disable dragon components
      dragon.setOpacity(0);
      dragon.setDisable(true);
      dragonOutline.setDisable(true);

      // Disable fixed bridge components - these need to be re-enabled when the bridge is fixed
      // (mini-game completed)
      fixedBridge.setDisable(true);
      fixedBridgeOutline.setDisable(true);

      // Show broken bridge
      brokenBridge.setOpacity(1);

    } else {
      // Minigame 2: Dragon is blocking the bridge - Disable broken bridge components

      brokenBridge.setDisable(true);
      brokenBridgeOutline.setDisable(true);

      fixedBridge.setDisable(true);
      fixedBridgeOutline.setDisable(true);
    }
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

  // Methods for minigame 1: fix bridge
  @FXML
  private void brokenBridgeHovered(MouseEvent event) {
    brokenBridgeOutline.setOpacity(1);
  }

  @FXML
  private void brokenBridgeUnhovered(MouseEvent event) {
    brokenBridgeOutline.setOpacity(0);
  }

  @FXML
  private void brokenBridgeClicked(MouseEvent event) {

    System.out.println("Clicked broken bridge");

    if (GameState.isLavaGameCompleted) {
      // if lava game room is COMPLETEd (bridge has been fixed), prompt user to cross the
      // bridge/enter the castle
    } else {
      // if lava game room is NOT COMPLETED (bridge has not been fixed), prompt user to fix the
      // bridge and open the bridge mini game
    }

    // in the minigame, we will need to check if the bridge has been fixed, if it has, we need to
    // disable the broken bridge components/images/outlines and then renable the FIXED BROKEN BRIDGE
    // image/outline (this allows us to access fixedBridgeClicked method)
  }

  @FXML
  private void fixedBridgeHovered(MouseEvent event) {
    fixedBridgeOutline.setOpacity(1);
  }

  @FXML
  private void fixedBridgeUnhovered(MouseEvent event) {
    fixedBridgeOutline.setOpacity(0);
  }

  @FXML
  private void fixedBridgeClicked(MouseEvent event) {
    System.out.println("Clicked fixed bridge");

    // tell user they can now enter the castle
  }

  // Methods for mini-game 2: tame dragon
  @FXML
  private void dragonHovered(MouseEvent event) {
    dragonOutline.setOpacity(1);
  }

  @FXML
  private void dragonUnhovered(MouseEvent event) {
    dragonOutline.setOpacity(0);
  }

  @FXML
  private void dragonClicked(MouseEvent event) {
    System.out.println("Dragon clicked");

    if (GameState.isForrestGameCompleted) {
      // if user has collected the fish, prompt the user to feed the dragon

      // dragon drops orbs and dragon is removed from scene

      // set lava game state to completed

      // disable dragon and dragonoutline

    } else {
      // if the user has not collected the fish, prompt the user to collect the fish
    }
  }

  @FXML
  private void bridgeHovered(MouseEvent event) {
    bridgeOutline.setOpacity(1);
  }

  @FXML
  private void bridgeUnhovered(MouseEvent event) {
    bridgeOutline.setOpacity(0);
  }

  @FXML
  private void bridgeClicked(MouseEvent event) {
    System.out.println("Bridge clicked");

    if (GameState.isLavaGameCompleted) {
      // if lava game room is COMPLETEd (dragon tamed), prompt user to cross the
      // bridge/enter the castle
    } else {
      // if lava game room is NOT COMPLETED (dragon has not been tamed), prompt user to catch fish
    }
  }

  // Door methods:
  @FXML
  private void doorHovered(MouseEvent event) {
    doorOutline.setOpacity(1);
  }

  @FXML
  private void doorUnhovered(MouseEvent event) {
    doorOutline.setOpacity(0);
  }

  @FXML
  private void doorClicked(MouseEvent event) {
    System.out.println("Door clicked");

    if (GameState.isLavaGameCompleted) {
      // if lava game room is COMPLETED, allow user to enter the castle
    } else {
      // if lava game room is NOT COMPLETED, check if it is minigame 1 or 2, then prompt them to
      // finish that game
    }
  }
}
