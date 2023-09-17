package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager.AppScene;
import org.controlsfx.control.Notifications;

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

  /** Opens chat with game master. */
  @FXML
  private void openChat() {
    App.setScene(AppScene.CHAT);
  }

  /** Opens help menu. */
  @FXML
  private void openHelp() {
    App.setScene(AppScene.HELP);
  }

  /** Opens settings menu. */
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
  /**
   * When the broken bridge is hovered over, an outline is shown.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void brokenBridgeHovered(MouseEvent event) {
    brokenBridgeOutline.setOpacity(1);
  }

  /**
   * When the broken bridge is unhovered, the outline is removed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void brokenBridgeUnhovered(MouseEvent event) {
    brokenBridgeOutline.setOpacity(0);
  }

  /**
   * When the broken bridge is clicked, if the user has completed the forrest mini game, they are
   * prompted to fix the bridge, else, they are prompted to get wood.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void brokenBridgeClicked(MouseEvent event) {

    System.out.println("Clicked broken bridge");

    if (GameState.isForrestGameCompleted) {
      App.setScene(AppScene.BRIDGE_GAME);
    } else {
      // Forrest game NOT COMPLETED, prompt user to get wood.
      Notifications message =
          NotificationBuilder.createNotification(
              "Game Master:", "The bridge broken. Try fixing it with some wood!", 5);
      message.show();
    }
  }

  /**
   * When the fixed bridge is hovered over, an outline is shown.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void fixedBridgeHovered(MouseEvent event) {
    fixedBridgeOutline.setOpacity(1);
  }

  /**
   * When the fixed bridge is unhovered, the outline is removed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void fixedBridgeUnhovered(MouseEvent event) {
    fixedBridgeOutline.setOpacity(0);
  }

  /**
   * When the fixed bridge is clicked, the user is told that they are able to enter the castle.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void fixedBridgeClicked(MouseEvent event) {
    System.out.println("Clicked fixed bridge");

    Notifications message =
        NotificationBuilder.createNotification(
            "Game Master:", "Bridge fixed! You may now enter the castle.", 5);
    message.show();
  }

  public void setFixedBridge() {
    fixedBridge.setDisable(false);
    fixedBridge.setOpacity(1);
    fixedBridgeOutline.setDisable(false);
  }

  // Methods for mini-game 2: Tame dragon
  /**
   * When the dragon is hovered over, an outline is shown.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void dragonHovered(MouseEvent event) {
    dragonOutline.setOpacity(1);
  }

  /**
   * When the dragon is unhovered, the outline is removed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void dragonUnhovered(MouseEvent event) {
    dragonOutline.setOpacity(0);
  }

  /**
   * When the dragon is clicked, if the user has completed the forrest mini game, they are prompted
   * to feed the dragon, else they are told they need to feed the dragon so that it allows them to
   * cross.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void dragonClicked(MouseEvent event) {
    System.out.println("Dragon clicked");

    if (GameState.isForrestGameCompleted) {
      // if user has collected the fish, prompt the user to feed the dragon
      Notifications message =
          NotificationBuilder.createNotification(
              "Dragon:", "Mmmm, yummy! In return have this orb I found.", 5);
      message.show();

      // TODO: set orb drop game state to true and put into inventory

      // set lava game state to completed
      GameState.isLavaGameCompleted = true;
      removeFish();

      // disable dragon and dragonoutline
      dragon.setDisable(true);
      dragonOutline.setDisable(true);

      dragon.setOpacity(0);

    } else {
      // if the user has not collected the fish, prompt the user to collect the fish
      Notifications message =
          NotificationBuilder.createNotification("Dragon:", "ROARRR! YOU SHALL NOT PASS!", 5);
      message.show();
    }
  }

  /**
   * When the bridge is hovered over, an outline is shown.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void bridgeHovered(MouseEvent event) {
    bridgeOutline.setOpacity(1);
  }

  /**
   * When the bridge is unhovered, the outline is removed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void bridgeUnhovered(MouseEvent event) {
    bridgeOutline.setOpacity(0);
  }

  /**
   * When the bridge is clicked, if the user has fed the dragon (lava game completed), they are told
   * that they are able to enter the castle, else they are told they need to feed the dragon.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void bridgeClicked(MouseEvent event) {
    System.out.println("Bridge clicked");

    if (GameState.isLavaGameCompleted) {
      // if lava game room is COMPLETEd (dragon tamed), prompt user to enter the castle
      Notifications message =
          NotificationBuilder.createNotification(
              "Game Master:", "You may now enter the castle!", 5);
      message.show();
    } else {
      // if lava game room is NOT COMPLETED (dragon has not been tamed), prompt user to catch fish
      Notifications message =
          NotificationBuilder.createNotification(
              "Game Master:", "A hungry dragon is blocking your path. Try feeding it fish!", 5);
      message.show();
    }
  }

  // Door methods:
  /**
   * When the door is hovered over, an outline is shown.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void doorHovered(MouseEvent event) {
    doorOutline.setOpacity(1);
  }

  /**
   * When the door is unhovered, the outline is removed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void doorUnhovered(MouseEvent event) {
    doorOutline.setOpacity(0);
  }

  /**
   * When the door is clicked, if the user has completed the lava mini game they enter the castle,
   * else they are told that they are unable to cross.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void doorClicked(MouseEvent event) {
    System.out.println("Door clicked");

    if (GameState.isLavaGameCompleted) {
      // if lava game room is COMPLETED, allow user to enter the castle
      // TODO: switch scene to castle - create new FXML and controller
      App.setScene(AppScene.CASTLE);
    } else {
      // lava game room is NOT COMPLETED
      if (GameState.isLavaBridge) {
        // if lava game room is minigame 1, prompt user to fix the bridge
        Notifications message =
            NotificationBuilder.createNotification(
                "Game Master:", "Door inaccessible... The bridge is broken!", 5);
        message.show();

      } else {
        // if lava game room is minigame 2, prompt user to feed the dragon
        Notifications message =
            NotificationBuilder.createNotification(
                "Game Master:", "A hungry dragon is blocking your path. Try feeding it fish!", 5);
        message.show();
      }
    }
  }
}
