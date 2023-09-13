package nz.ac.auckland.se206.controllers;

import eu.hansolo.enzo.notification.Notification;
import eu.hansolo.enzo.notification.Notification.Notifier;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class StartScreenController {
  /** Switches the scene from the start GUI to tutorial GUI. */
  @FXML
  private void onLaunchGame() {

    // Syntax for new notification library: look through this to see possible methods
    // https://github.com/HanSolo/Enzo/blob/master/src/main/java/eu.hansolo.enzo/notification/Notification.java
    Notification info =
        new Notification(
            "title",
            "info-message, this is me testing how long the text can be before it screws up bruh");

    Notifier.setPopupLocation(App.getStage(), Pos.TOP_CENTER);
    Notifier.setOffsetY(50);
    Notifier.setWidth(400);
    Notifier.setHeight(100);

    Notifier.INSTANCE.notify(info);

    // Change scene to show back story:
    App.setScene(AppScene.STORY);
  }

  /** Quits the game. */
  @FXML
  private void onLeaveGame() {
    System.exit(0);
  }
}
