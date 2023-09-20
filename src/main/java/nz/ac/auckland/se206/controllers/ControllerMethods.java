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

  // Object properties for all images in the background of chat
  protected static ObjectProperty<javafx.scene.image.Image> forestAxeImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> forestRodImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> forestTreesRemovedImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> mainMapImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> mainMapRemovedImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> mainDarkImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> lavaDragonImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> lavaNoDragonImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> forestMiniImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> forestMiniTreesRemovedImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> fishingMiniImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> chestMiniImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> orbMiniImageProperty =
      new SimpleObjectProperty<>(null);
  protected static ObjectProperty<javafx.scene.image.Image> bridgeMiniImageProperty =
      new SimpleObjectProperty<>(null);

  // Instance variables to be accessible to all controllers
  protected int count;
  protected Timer timer = new Timer(true);

  /** Starts the count down timer for game. */
  protected void startTimer() {
    // Retrieve the duration of the game
    count = GameState.timerCount;

    timer.scheduleAtFixedRate(
        new TimerTask() {
          @Override
          public void run() {

            // If the user has escaped from the room, cancel timer
            if (GameState.isRoomEscaped) {
              timer.cancel();
            } else {
              // Decrement the count and update the timer.
              count--;

              // If the timer reaches 0, cancel the timer and switch scene to game over.
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
      // If the riddle has not been found by clicking on the book
      displayTask.setValue("Task: Search for a riddle");

    } else if (!GameState.isRiddleResolved) {
      // If the riddle has not been solved
      displayTask.setValue("Task: Try to solve the riddle");

    } else if (GameState.isRug && !GameState.isRoomOrbCollected) {
      // If the room orb has not been collected under the rug
      displayTask.setValue("Task: Have a look under the rug");

    } else if (GameState.isCabinet && !GameState.isRoomOrbCollected) {
      // If the room orb has not been collected in the cabinet
      displayTask.setValue("Task: Check the cabinet");

    } else if ((GameState.isForestFishing && !GameState.isFishingRodTaken)
        || (GameState.isForestTreeChopping && !GameState.isAxeTaken)) {
      // If the fishing rod or axe has not been taken
      displayTask.setValue("Task: Search for other items");

    } else if ((GameState.isFishingRodTaken && !GameState.isForestGameCompleted)
        || (GameState.isAxeTaken && !GameState.isForestGameCompleted)) {
      // If the fishing rod or axe has been taken but the forrest game has not been completed
      displayTask.setValue("Task: Try using the item you found");

    } else if (GameState.isForestTreeChopping
        && GameState.isForestGameCompleted
        && !GameState.isForestOrbCollected) {
      // If the forest orb has not been collected
      displayTask.setValue("Task: Don't forget the green orb");

    } else if (GameState.isLavaDragon && !GameState.isLavaGameCompleted) {
      // If the lava game has not been completed - dragon has not been distracted
      displayTask.setValue("Task: Distract the dragon with food");

    } else if (GameState.isLavaBridge && !GameState.isLavaGameCompleted) {
      // If the lava game has not been completed - bridge has not been built
      displayTask.setValue("Task: Use the planks you made");

    } else if (GameState.isLavaGameCompleted && !GameState.isChestFound) {
      // If the chest has not been found
      displayTask.setValue("Task: What's inside the castle?");

    } else if (GameState.isLavaGameCompleted && !GameState.isCodeFound) {
      // If the code has not been found
      displayTask.setValue("Task: Search for a code");

    } else if (GameState.isLavaGameCompleted && !GameState.isChestUnlocked) {
      // If the chest has not been unlocked
      displayTask.setValue("Task: Try opening the chest");

    } else if (!GameState.isCastleOrbCollected) {
      // If the castle orb has not been collected
      displayTask.setValue("Task: Don't forget the red orb");

    } else if (GameState.isRoomOrbCollected
        && GameState.isForestOrbCollected
        && GameState.isCastleOrbCollected
        && !GameState.isOrbsPlaced) {
      // If the orbs have not been placed in the terminal
      displayTask.setValue("Task: Place the orbs in the terminal");

    } else if (GameState.isPortalOpen && !GameState.isRoomEscaped) {
      // If the portal has not been entered
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

  /** Method for removing the orbs from the inventory */
  protected void removeOrbs() {
    blueOrbImageProperty.set(null);
    greenOrbImageProperty.set(null);
    redOrbImageProperty.set(null);
  }

  /** Methods for settimg opacities of backgrouds behind chat overlay */
  protected void setForestAxeOpacity() {
    removeBackgrounds();
    forestAxeImageProperty.set(new Image(getClass().getResourceAsStream("/images/forestAxe.png")));
  }

  protected void setForestRodOpacity() {
    removeBackgrounds();
    forestRodImageProperty.set(new Image(getClass().getResourceAsStream("/images/forestRod.png")));
  }

  protected void setForestTreesRemovedOpacity() {
    removeBackgrounds();
    forestTreesRemovedImageProperty.set(
        new Image(getClass().getResourceAsStream("/images/forestTreesRemoved.png")));
  }

  protected void setMainMapOpacity() {
    removeBackgrounds();
    mainMapImageProperty.set(new Image(getClass().getResourceAsStream("/images/mainMap.png")));
  }

  protected void setMainMapRemovedOpacity() {
    removeBackgrounds();
    mainMapRemovedImageProperty.set(
        new Image(getClass().getResourceAsStream("/images/mainMapRemoved.png")));
  }

  protected void setMainDarkOpacity() {
    removeBackgrounds();
    mainDarkImageProperty.set(new Image(getClass().getResourceAsStream("/images/mainDark.png")));
  }

  protected void setLavaDragonOpacity() {
    removeBackgrounds();
    lavaDragonImageProperty.set(
        new Image(getClass().getResourceAsStream("/images/lavaDragon.png")));
  }

  protected void setLavaNoDragonOpacity() {
    removeBackgrounds();
    lavaNoDragonImageProperty.set(
        new Image(getClass().getResourceAsStream("/images/lavaNoDragon.png")));
  }

  protected void setForestMiniOpacity() {
    removeBackgrounds();
    forestMiniImageProperty.set(
        new Image(getClass().getResourceAsStream("/images/forestMini.png")));
  }

  protected void setForestMiniTreesRemovedOpacity() {
    removeBackgrounds();
    forestMiniTreesRemovedImageProperty.set(
        new Image(getClass().getResourceAsStream("/images/forestMiniTreesRemoved.png")));
  }

  protected void setFishingMiniOpacity() {
    removeBackgrounds();
    fishingMiniImageProperty.set(
        new Image(getClass().getResourceAsStream("/images/fishingMini.png")));
  }

  protected void setChestMiniOpacity() {
    removeBackgrounds();
    chestMiniImageProperty.set(new Image(getClass().getResourceAsStream("/images/chestMini.png")));
  }

  protected void setOrbMiniOpacity() {
    removeBackgrounds();
    orbMiniImageProperty.set(new Image(getClass().getResourceAsStream("/images/orbMini.png")));
  }

  protected void setBridgeMiniOpacity() {
    removeBackgrounds();
    bridgeMiniImageProperty.set(
        new Image(getClass().getResourceAsStream("/images/bridgeMini.png")));
  }

  /** Method for removing all backgrounds for chat overlay by setting images to null */
  protected void removeBackgrounds() {
    forestAxeImageProperty.set(null);
    forestRodImageProperty.set(null);
    forestTreesRemovedImageProperty.set(null);
    mainMapImageProperty.set(null);
    mainMapRemovedImageProperty.set(null);
    mainDarkImageProperty.set(null);
    lavaDragonImageProperty.set(null);
    lavaNoDragonImageProperty.set(null);
    forestMiniImageProperty.set(null);
    forestMiniTreesRemovedImageProperty.set(null);
    fishingMiniImageProperty.set(null);
    chestMiniImageProperty.set(null);
    orbMiniImageProperty.set(null);
    bridgeMiniImageProperty.set(null);
  }
}
