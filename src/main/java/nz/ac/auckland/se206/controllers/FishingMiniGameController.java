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
import nz.ac.auckland.se206.GameState;

public class FishingMiniGameController extends ControllerMethods {

  @FXML private Label roomTimerLabel;

  @FXML private ImageView animatedFish;
  @FXML private ImageView fishBite;
  @FXML private Rectangle rodHitbox;
  @FXML private ImageView rodLine;

  private boolean isFlipped = false;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);

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
      GameState.isForrestGameCompleted = true;

      fishBite.setOpacity(1);
      animatedFish.setOpacity(0);

      // Coordinate of collision
      double collisionY = Math.max(rodBounds.getMinY(), fishBounds.getMinY());
      System.out.println(collisionY);

      // Depending on the height of the collision, the fish will disappear at a different time
      if (collisionY > 310) {
        PauseTransition delay = new PauseTransition(Duration.millis(2800));
        delay.setOnFinished(event -> fishBite.setOpacity(0));
        delay.play();
      } else {
        PauseTransition delay = new PauseTransition(Duration.millis(4000));
        delay.setOnFinished(event -> fishBite.setOpacity(0));
        delay.play();
      }
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
    TranslateTransition rodTransition = new TranslateTransition();
    rodTransition.setNode(rodLine);
    rodTransition.setDuration(Duration.millis(2500));
    rodTransition.setCycleCount(2);
    rodTransition.setByY(155);
    rodTransition.setAutoReverse(true);
    rodTransition.play();
  }

  // Animation for the rod hitbox
  private void moveFishingHitbox() {
    TranslateTransition rodTransition = new TranslateTransition();
    rodTransition.setNode(rodHitbox);
    rodTransition.setDuration(Duration.millis(2500));
    rodTransition.setCycleCount(2);
    rodTransition.setByY(155);
    rodTransition.setAutoReverse(true);
    rodTransition.play();
  }

  // Animation for fish bite
  private void moveFishBite() {
    TranslateTransition hitBoxTransition = new TranslateTransition();
    hitBoxTransition.setNode(fishBite);
    hitBoxTransition.setDuration(Duration.millis(2500));
    hitBoxTransition.setCycleCount(2);
    hitBoxTransition.setByY(155);
    hitBoxTransition.setAutoReverse(true);
    hitBoxTransition.play();
  }

  // Cast fishing rod button
  @FXML
  private void rodDrop(MouseEvent event) {
    moveFishingLine();
    moveFishingHitbox();
    moveFishBite();
  }
}
