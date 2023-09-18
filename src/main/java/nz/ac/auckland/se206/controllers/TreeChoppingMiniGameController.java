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
import nz.ac.auckland.se206.SceneManager.AppScene;

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
    // Bind the labels to the display values
    lblTimer.textProperty().bind(ControllerMethods.displayTime);
    lblTask.textProperty().bind(ControllerMethods.displayTask);
    lblHints.textProperty().bind(ControllerMethods.displayHints);

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

        // TODO: Make a notification to say an orb fell from the tree
        System.out.println("An orb fell from the tree!");

      } else if (chopCount == 10) {
        // Completed the mini game
        GameState.isChopped = true;
        GameState.isForrestGameCompleted = true;
        GameState.isForrestOrbCollected = true;

        // Make the planks appear in the inventory
        findPlanks();

        // TODO: Make a notification to say you cut all the trees down
        System.out.println("You completed the mini game!");

        // Update the trees image in the mini game
        miniTrees.setOpacity(0);
        choppedMiniTrees.setOpacity(1);

        // Update the trees image in the forest room to show the trees are chopped
        App.getForestRoomController().chopTrees();
      }
    } else {
      // TODO: Make a notification say that the tress have already been chopped
      System.out.println("You've already chopped those trees enough");
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
    TranslateTransition orbTransition = new TranslateTransition();
    greenOrbOnTree.setOpacity(1);
    orbTransition.setNode(greenOrbOnTree);
    orbTransition.setDuration(Duration.millis(1000));
    orbTransition.setByY(125);
    orbTransition.play();

    TranslateTransition orbOutlineTransition = new TranslateTransition();
    orbOutlineTransition.setNode(greenOrbOnTreeOutline);
    orbOutlineTransition.setDuration(Duration.millis(1000));
    orbOutlineTransition.setByY(125);
    orbOutlineTransition.play();
  }

  // Green Orb Logic
  @FXML
  private void greenOrbHover(MouseEvent event) {
    if (isOrbFallen && !isOrbCollected) greenOrbOnTreeOutline.setOpacity(1);
  }

  @FXML
  private void greenOrbUnhover(MouseEvent event) {
    if (isOrbFallen && !isOrbCollected) greenOrbOnTreeOutline.setOpacity(0);
  }

  @FXML
  private void greenOrbClick(MouseEvent event) {
    // Make the green orb appear in the inventory
    // NOTE: This should only be done once the orb is clicked by the user (implemented later)
    findGreenOrb();
    greenOrbOnTree.setOpacity(0);
    greenOrbOnTreeOutline.setOpacity(0);
    isOrbCollected = true;
  }

  private void treeHitAnimation() {
    Timeline timeline = new Timeline();

    KeyFrame keyFrameOne =
        new KeyFrame(
            Duration.millis(35),
            event -> {
              treeHitOne.setOpacity(1);
              miniTrees.setOpacity(0);
            });

    KeyFrame keyFrameTwo =
        new KeyFrame(
            Duration.millis(70),
            event -> {
              treeHitTwo.setOpacity(1);
              treeHitOne.setOpacity(0);
            });

    KeyFrame keyFrameThree =
        new KeyFrame(
            Duration.millis(105),
            event -> {
              treeHitThree.setOpacity(1);
              treeHitTwo.setOpacity(0);
            });

    KeyFrame keyFrameFour =
        new KeyFrame(
            Duration.millis(140),
            event -> {
              treeHitTwo.setOpacity(1);
              treeHitThree.setOpacity(0);
            });

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
}
