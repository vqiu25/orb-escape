package nz.ac.auckland.se206.controllers;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager.AppScene;
import org.controlsfx.control.Notifications;

public class FishingMiniGameController extends ControllerMethods {

  @FXML private Label lblTimer;
  @FXML private Label lblTask;
  @FXML private Label lblHints;

  @FXML private ImageView animatedFish;
  @FXML private ImageView fishBite;
  @FXML private Rectangle rodHitbox;
  @FXML private ImageView rodLine;
  @FXML private ImageView blueButtonOne;
  @FXML private ImageView blueButtonTwo;
  @FXML private ImageView blueButtonThree;
  @FXML private ImageView backButtonOne;
  @FXML private ImageView backButtonTwo;
  @FXML private ImageView backButtonThree;

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

  @FXML private Rectangle rodRectangle;

  private boolean isFlipped = false;
  private boolean isRunning = false;
  private boolean isFishDelay = false;

  public void initialize() {
    // Bind the labels to the display values
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

    // X axis animation for the fish
    TranslateTransition transition = new TranslateTransition();
    transition.setNode(animatedFish);
    transition.setDuration(Duration.millis(4300));
    transition.setCycleCount(TranslateTransition.INDEFINITE);
    transition.setByX(-340);
    transition.setAutoReverse(true);
    transition.play();

    // Flip animation for the fish
    Duration flipDuration = Duration.millis(4300);
    Timeline timeline = new Timeline(new KeyFrame(flipDuration, event -> toggleImageFlip()));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    // Collision detection timer
    AnimationTimer collisionTimer =
        new AnimationTimer() {
          @Override
          public void handle(long timestamp) {
            checkCollision(rodHitbox, animatedFish);
          }
        };

    // Start the collision timer
    collisionTimer.start();
  }

  // Collision detection between the rod hitbox and the fish
  private void checkCollision(Rectangle rod, ImageView fish) {
    // Coordinates of objects
    Bounds rodBounds = rod.getBoundsInParent();
    Bounds fishBounds = fish.getBoundsInParent();
    if (rod.getBoundsInParent().intersects(fish.getBoundsInParent()) && !GameState.isFishCaught) {
      // Collision detected
      GameState.isFishCaught = true;
      GameState.isForestGameCompleted = true;
      GameState.isForestOrbCollected = true;

      fishBite.setOpacity(1);
      animatedFish.setOpacity(0);

      // Coordinate of collision
      double collisionY = Math.max(rodBounds.getMinY(), fishBounds.getMinY());

      // Depending on the height of the collision, the fish will disappear at a different time
      if (collisionY > 310) {
        PauseTransition delay = new PauseTransition(Duration.millis(2800));
        delay.setOnFinished(
            event -> {
              // Make the fish disappear and appear in the inventory with the green orb
              fishBite.setOpacity(0);
              findFish();
              findGreenOrb();
              updateTask();
            });
        delay.play();
      } else {
        PauseTransition delay = new PauseTransition(Duration.millis(4000));
        delay.setOnFinished(
            event -> {
              // Make the fish disappear and appear in the inventory with the green orb
              fishBite.setOpacity(0);
              findFish();
              findGreenOrb();
              updateTask();
            });
        delay.play();
      }

      isFishDelay = true;
    }
  }

  // Animation of the fish mirroring itself upon the end of half a cycle
  private void toggleImageFlip() {
    if (isFlipped) {
      // Reset scaling (unflip)
      animatedFish.setScaleX(1);
    } else {
      // Set scaling (flip)
      animatedFish.setScaleX(-1);
    }
    isFlipped = !isFlipped;
  }

  // Animation for the fishing line
  private void moveFishingLine() {
    // Create a new transition object
    TranslateTransition rodTransition = new TranslateTransition();

    // Set the node to be animated
    rodTransition.setNode(rodLine);

    // Configure the animation
    rodTransition.setDuration(Duration.millis(2500));
    rodTransition.setCycleCount(2);
    rodTransition.setByY(155);

    // Reverse the animation
    rodTransition.setAutoReverse(true);
    rodTransition.setOnFinished(
        event -> {
          isRunning = false;
        });
    rodTransition.play();
  }

  // Animation for the rod hitbox
  private void moveFishingHitbox() {
    // Create a new transition object
    TranslateTransition rodTransition = new TranslateTransition();

    // Set the node to be animated
    rodTransition.setNode(rodHitbox);

    // Animation configuration
    rodTransition.setDuration(Duration.millis(2500));
    rodTransition.setCycleCount(2);
    rodTransition.setByY(155);
    rodTransition.setAutoReverse(true);
    rodTransition.play();
  }

  // Animation for fish bite
  private void moveFishBite() {
    // Create a new transition object
    TranslateTransition hitBoxTransition = new TranslateTransition();

    // Set the node to be animated
    hitBoxTransition.setNode(fishBite);

    // Animation configuration
    hitBoxTransition.setDuration(Duration.millis(2500));
    hitBoxTransition.setCycleCount(2);
    hitBoxTransition.setByY(155);
    hitBoxTransition.setAutoReverse(true);
    hitBoxTransition.play();
  }

  // Cast fishing rod button
  private void rodDrop() {
    isRunning = true;
    moveFishingLine();
    moveFishingHitbox();
    moveFishBite();
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
    if (!isRunning && GameState.isFishCaught && isFishDelay) {
      rodDrop();
    } else if (!isRunning && !GameState.isFishCaught) {
      rodDrop();
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
    if (isRunning) {
      // If the user is currently fishing, inform user they cannot go back
      showDisabledButtonNotification();
    } else if (!isRunning && GameState.isFishCaught && isFishDelay) {
      // Return to forest scene
      App.setScene(AppScene.FOREST);
    } else if (!isRunning && !GameState.isFishCaught) {
      App.setScene(AppScene.FOREST);
    }
    backButtonThree.setOpacity(0);
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
    if (!isRunning && GameState.isFishCaught && isFishDelay
        || !isRunning && !GameState.isFishCaught) {
      setFishingMiniOpacity();

      // store current scene in scene stack
      GameState.lastScene = AppScene.FISHING;

      // Change the scene
      App.setScene(AppScene.CHAT);
    } else if (isRunning) {
      // If the user is currently fishing, inform user they cannot go back
      showDisabledButtonNotification();
    }
  }

  private void showDisabledButtonNotification() {
    // Prompt for user when they are fishing and attempt to click on game master/return
    Notifications message =
        NotificationBuilder.createNotification("CLOUD: ", "Currently fishing! Please wait!", 5);
    message.show();
  }
}
