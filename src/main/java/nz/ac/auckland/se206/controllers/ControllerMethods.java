package nz.ac.auckland.se206.controllers;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class ControllerMethods {
  // String properties for the timer, task and hints
  protected static StringProperty displayTime = new SimpleStringProperty(GameState.timerString);
  protected static StringProperty displayTask = new SimpleStringProperty("Task: ");
  protected static StringProperty displayHints = new SimpleStringProperty(GameState.hintString);

  // Object properties for all the images in the inventory
  protected static ObjectProperty<javafx.scene.image.Image> fishingRodIconImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> axeIconImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> fishIconImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> planksIconImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> blueOrbImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> greenOrbImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> redOrbImageProperty =
      new SimpleObjectProperty<>(null);

  // Instance variables to be accessible to all controllers
  protected int count;
  protected Timer timer = new Timer(true);

  /** Starts the timer. */
  protected void startTimer() {
    count = GameState.timerCount;
    timer.scheduleAtFixedRate(
        new TimerTask() {
          @Override
          public void run() {
            if (GameState.isRoomEscaped) {
              timer.cancel();
            } else {
              count--;
              if (count == 0) {
                timer.cancel();
                gameOver();
              }
              updateTimer();
            }
          }
        },
        0,
        1000);
  }

  /** Updates the timer and checks if game over. */
  private void updateTimer() {
    String extra = (count % 60) < 10 ? "0" : "";
    String time = (count / 60) + ":" + extra + (count % 60);
    Platform.runLater(
        () -> {
          displayTime.setValue("Time Left: " + time);
        });
  }

  /** Changes scene to game over scene. */
  protected void gameOver() {
    App.setScene(AppScene.GAMEOVER);
  }

  /** Updates the task label based on current game state */
  protected void updateTask() {
    if (!GameState.isRiddleFound) {
      displayTask.setValue("Task: Search for a riddle");
    } else if (!GameState.isRiddleResolved) {
      displayTask.setValue("Task: Try to solve the riddle");
    } else if (GameState.isRug && !GameState.isRoomOrbCollected) {
      displayTask.setValue("Task: Have a look under the rug");
    } else if (GameState.isCabinet && !GameState.isRoomOrbCollected) {
      displayTask.setValue("Task: Check the cabinet");
    } else if ((GameState.isForestFishing && !GameState.isFishingRodTaken)
        || (GameState.isForestTreeChopping && !GameState.isAxeTaken)) {
      displayTask.setValue("Task: Search for other items");
    } else if ((GameState.isFishingRodTaken && !GameState.isForestGameCompleted)
        || (GameState.isAxeTaken && !GameState.isForestGameCompleted)) {
      displayTask.setValue("Task: Try using the item you found");
    } else if (GameState.isForestTreeChopping
        && GameState.isForestGameCompleted
        && !GameState.isForestOrbCollected) {
      displayTask.setValue("Task: Don't forget the green orb");
    } else if (GameState.isLavaDragon && !GameState.isLavaGameCompleted) {
      displayTask.setValue("Task: Distract the dragon with food");
    } else if (GameState.isLavaBridge && !GameState.isLavaGameCompleted) {
      displayTask.setValue("Task: Use the planks you made");
    } else if (GameState.isLavaGameCompleted && !GameState.isChestFound) {
      displayTask.setValue("Task: What's inside the castle?");
    } else if (GameState.isLavaGameCompleted && !GameState.isCodeFound) {
      displayTask.setValue("Task: Search for a code");
    } else if (GameState.isLavaGameCompleted && !GameState.isChestUnlocked) {
      displayTask.setValue("Task: Try opening the chest");
    } else if (!GameState.isCastleOrbCollected) {
      displayTask.setValue("Task: Don't forget the red orb");
    } else if (GameState.isRoomOrbCollected
        && GameState.isForestOrbCollected
        && GameState.isCastleOrbCollected
        && !GameState.isOrbsPlaced) {
      displayTask.setValue("Task: Place the orbs in the terminal");
    } else if (GameState.isPortalOpen && !GameState.isRoomEscaped) {
      displayTask.setValue("Task: Enter the portal!");
    }
  }

  /** Updates the hints remaining */
  protected void updateHintsRemaining() {
    if (GameState.isEasySelected) {
      displayHints.setValue("Hints: Infinite");
    } else if (GameState.isMediumSelected) {
      displayHints.setValue("Hints: " + GameState.hintCount);
    } else if (GameState.isHardSelected) {
      displayHints.setValue("Hints: None");
    }
  }

  /** Method for adding the fishing rod to inventory */
  protected void findFishingRod() {
    Image fishingRodImage = new Image(getClass().getResourceAsStream("/images/fishingRodIcon.png"));
    fishingRodIconImageProperty.set(fishingRodImage);
  }

  /** Method for adding the axe to inventory */
  protected void findAxe() {
    Image axeImage = new Image(getClass().getResourceAsStream("/images/axeIcon.png"));
    axeIconImageProperty.set(axeImage);
  }

  /** Method for adding the fish to inventory */
  protected void findFish() {
    Image fishImage = new Image(getClass().getResourceAsStream("/images/fishIcon.png"));
    fishIconImageProperty.set(fishImage);
  }

  /** Method for adding the fish to inventory */
  protected void findPlanks() {
    Image planksImage = new Image(getClass().getResourceAsStream("/images/planksIcon.png"));
    planksIconImageProperty.set(planksImage);
  }

  /** Method for adding the blue orb to inventory */
  protected void findBlueOrb() {
    Image blueOrbImage = new Image(getClass().getResourceAsStream("/images/blueOrb.png"));
    blueOrbImageProperty.set(blueOrbImage);
  }

  /** Method for adding the green orb to inventory */
  protected void findGreenOrb() {
    Image greenOrbImage = new Image(getClass().getResourceAsStream("/images/greenOrb.png"));
    greenOrbImageProperty.set(greenOrbImage);
  }

  /** Method for adding the red orb to inventory */
  protected void findRedOrb() {
    Image redOrbImage = new Image(getClass().getResourceAsStream("/images/redOrb.png"));
    redOrbImageProperty.set(redOrbImage);
  }

  /** Method for removing the fish from the inventory */
  protected void removeFish() {
    fishIconImageProperty.set(null);
  }

  /** Method for removing the planks from the inventory */
  protected void removePlanks() {
    planksIconImageProperty.set(null);
  }
}
