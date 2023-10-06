package nz.ac.auckland.se206.controllers;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager.AppScene;
import org.controlsfx.control.Notifications;

public class TreeChoppingMiniGameController extends ControllerMethods {

  @FXML private Label lblTimer;
  @FXML private Label lblTask;
  @FXML private Label lblHints;
  @FXML private Label chopCountLabel;

  @FXML private ImageView miniTrees;
  @FXML private ImageView miniTreesHit;
  @FXML private ImageView choppedMiniTrees;

  @FXML private ImageView blueButtonOne;
  @FXML private ImageView blueButtonTwo;
  @FXML private ImageView blueButtonThree;
  @FXML private ImageView backButtonOne;
  @FXML private ImageView backButtonTwo;
  @FXML private ImageView backButtonThree;
  @FXML private ImageView greenOrbOnTree;
  @FXML private ImageView greenOrbOnTreeOutline;
  @FXML private ImageView treeHitOne;
  @FXML private ImageView treeHitTwo;
  @FXML private ImageView treeHitThree;

  // Game Master
  @FXML private ImageView gameMasterDefault;
  @FXML private ImageView gameMasterChat;

  // Inventory Items
  @FXML private ImageView fishingRodIcon;
  @FXML private ImageView axeIcon;
  @FXML private ImageView fishIcon;
  @FXML private ImageView planksIcon;
  @FXML private ImageView blueOrb;
  @FXML private ImageView greenOrb;
  @FXML private ImageView redOrb;

  private int chopCount = 0;
  private int orbDropAfter = new Random().nextInt(8) + 1;
  private boolean isOrbFallen = false;
  private boolean isOrbCollected = false;

  public void initialize() {
    // Bind the labels to the display values and styles
    lblTimer.textFillProperty().bind(ControllerMethods.timerTextFill);
    lblTimer.textProperty().bind(ControllerMethods.displayTime);
    lblTask.textProperty().bind(ControllerMethods.displayTask);
    lblHints.textProperty().bind(ControllerMethods.displayHints);

    // Initialise the inventory items
    fishingRodIcon = getFishingRodIcon();
    axeIcon = getAxeIcon();
    fishIcon = getFishIcon();
    planksIcon = getPlanksIcon();
    blueOrb = getBlueOrb();
    greenOrb = getGreenOrb();
    redOrb = getRedOrb();

    // Bind the inventory images to their image properties
    bindInventory();

    // Bind the trees image to its image property
    miniTrees.imageProperty().bind(ControllerMethods.miniTreesImageProperty);
    treeHitOne.imageProperty().bind(ControllerMethods.treeHitImageProperty);
  }

  @FXML
  private void blueHover(MouseEvent event) {
    blueButtonTwo.setOpacity(1);
  }

  @FXML
  private void blueUnhover(MouseEvent event) {
    blueButtonTwo.setOpacity(0);
  }

  @FXML
  private void bluePressed(MouseEvent event) {
    blueButtonThree.setOpacity(1);
  }

  @FXML
  private void blueReleased(MouseEvent event) {

    if (chopCount < 10) {
      treeHitAnimation();
      chopCount++;
      chopCountLabel.setText(chopCount + "/10");

      if (chopCount == orbDropAfter) {
        orbDrop();

        // Notify the user that an orb fell from the tree:
        Notifications message =
            NotificationBuilder.createNotification(
                "Game Master:", "Look! An orb fell from the tree!", 5);
        message.show();

      } else if (chopCount == 10) {
        // Completed the mini game
        GameState.isChopped = true;
        GameState.isForestGameCompleted = true;
        updateTask();

        // Make the planks appear in the inventory
        findPlanks();

        // Notify the user that they hae chopped all the trees down:
        Notifications message =
            NotificationBuilder.createNotification("Game Master:", "Mini game completed!", 5);
        message.show();

        // Update the trees image in the mini game
        miniTrees.setOpacity(0);
        choppedMiniTrees.setOpacity(1);

        // Update the trees image in the forest room to show the trees are chopped
        App.getForestRoomController().chopTrees();
      }
    } else {
      // Notify the user that they have chopped enough trees:
      Notifications message =
          NotificationBuilder.createNotification(
              "Game master: ", "You've already chopped enough trees!", 5);
      message.show();
    }
    blueButtonThree.setOpacity(0);
  }

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
    App.setScene(AppScene.FOREST);
  }

  private void orbDrop() {
    isOrbFallen = true;

    // Create orb drop animation object
    TranslateTransition orbTransition = new TranslateTransition();

    // Enable orb image to be visable
    greenOrbOnTree.setOpacity(1);

    // Set orb drop animation properties
    orbTransition.setNode(greenOrbOnTree);
    orbTransition.setDuration(Duration.millis(1000));
    orbTransition.setByY(125);
    orbTransition.play();

    // Create orb outline drop animation object
    TranslateTransition orbOutlineTransition = new TranslateTransition();

    // Set orb outline drop animation properties
    orbOutlineTransition.setNode(greenOrbOnTreeOutline);
    orbOutlineTransition.setDuration(Duration.millis(1000));
    orbOutlineTransition.setByY(125);
    orbOutlineTransition.play();
  }

  // Green Orb Logic
  @FXML
  private void greenOrbHover(MouseEvent event) {
    if (isOrbFallen && !isOrbCollected) {
      greenOrbOnTreeOutline.setOpacity(1);
    }
  }

  @FXML
  private void greenOrbUnhover(MouseEvent event) {
    if (isOrbFallen && !isOrbCollected) {
      greenOrbOnTreeOutline.setOpacity(0);
    }
  }

  @FXML
  private void greenOrbClick(MouseEvent event) {
    // Make the green orb appear in the inventory
    // NOTE: This should only be done once the orb is clicked by the user (implemented later)
    findGreenOrb();
    greenOrbOnTree.setOpacity(0);
    greenOrbOnTreeOutline.setOpacity(0);
    isOrbCollected = true;
    GameState.isForestOrbCollected = true;
    updateTask();
  }

  private void treeHitAnimation() {

    // Create a timeline object - controls animations over a series of frames
    Timeline timeline = new Timeline();

    // Frame 1 - Set the node to be animated
    KeyFrame keyFrameOne =
        new KeyFrame(
            Duration.millis(35),
            event -> {
              treeHitOne.setOpacity(1);
              miniTrees.setOpacity(0);
            });

    // Frame 2 - Set the node to be animated
    KeyFrame keyFrameTwo =
        new KeyFrame(
            Duration.millis(70),
            event -> {
              treeHitTwo.setOpacity(1);
              treeHitOne.setOpacity(0);
            });

    // Frame 3 - Set the node to be animated
    KeyFrame keyFrameThree =
        new KeyFrame(
            Duration.millis(105),
            event -> {
              treeHitThree.setOpacity(1);
              treeHitTwo.setOpacity(0);
            });

    // Frame 4 - Set the node to be animated
    KeyFrame keyFrameFour =
        new KeyFrame(
            Duration.millis(140),
            event -> {
              treeHitTwo.setOpacity(1);
              treeHitThree.setOpacity(0);
            });

    // Frame 5 - Set the node to be animated
    KeyFrame keyFrameFive =
        new KeyFrame(
            Duration.millis(175),
            event -> {
              treeHitTwo.setOpacity(0);
              if (chopCount != 10) {
                miniTrees.setOpacity(1);
              } else {
                miniTrees.setOpacity(0);
              }
            });

    timeline
        .getKeyFrames()
        .addAll(keyFrameOne, keyFrameTwo, keyFrameThree, keyFrameFour, keyFrameFive);
    timeline.play();
  }

  // Bottom Right Game Master Button
  @FXML
  private void gameMasterOnHover(MouseEvent event) {
    gameMasterDefault.setOpacity(0);
    gameMasterChat.setOpacity(1);
  }

  @FXML
  private void gameMasterOnUnhover(MouseEvent event) {
    gameMasterDefault.setOpacity(1);
    gameMasterChat.setOpacity(0);
  }

  @FXML
  private void gameMasterOnClick(MouseEvent event) {
    if (GameState.isChopped) {
      setForestMiniTreesRemovedOpacity();
    } else {
      setForestMiniOpacity();
    }
    // Store the current scene in the scene stack:
    GameState.lastScene = AppScene.TREES;

    // Change the scene to the chat scene
    App.setScene(AppScene.CHAT);
  }
}
