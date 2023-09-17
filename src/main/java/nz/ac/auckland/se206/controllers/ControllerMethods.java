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
  // String property for the timer
  protected static StringProperty displayTime = new SimpleStringProperty(GameState.timerString);

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
  protected int count = GameState.timerCount;
  protected Timer timer = new Timer(true);

  /** Starts the timer. */
  protected void startTimer() {
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
          displayTime.setValue(time);
        });
  }

  /** Changes scene to game over scene. */
  protected void gameOver() {
    App.setScene(AppScene.GAMEOVER);
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
  public void findFish() {
    Image fishImage = new Image(getClass().getResourceAsStream("/images/fishIcon.png"));
    fishIconImageProperty.set(fishImage);
  }

  /** Method for adding the fish to inventory */
  public void findPlanks() {
    Image planksImage = new Image(getClass().getResourceAsStream("/images/planksIcon.png"));
    planksIconImageProperty.set(planksImage);
  }

  /** Method for adding the blue orb to inventory */
  public void findBlueOrb() {
    Image blueOrbImage = new Image(getClass().getResourceAsStream("/images/blueOrb.png"));
    blueOrbImageProperty.set(blueOrbImage);
  }

  /** Method for adding the green orb to inventory */
  public void findGreenOrb() {
    Image greenOrbImage = new Image(getClass().getResourceAsStream("/images/greenOrb.png"));
    greenOrbImageProperty.set(greenOrbImage);
  }

  /** Method for adding the red orb to inventory */
  public void findRedOrb() {
    Image redOrbImage = new Image(getClass().getResourceAsStream("/images/redOrb.png"));
    redOrbImageProperty.set(redOrbImage);
  }
}
