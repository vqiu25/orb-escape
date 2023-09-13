package nz.ac.auckland.se206.controllers;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class ControllerMethods {
  // String property for the timer
  protected static StringProperty displayTime = new SimpleStringProperty("2:00");

  // Instance variables to be accessible to all controllers
  protected int count = 120;
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
}
