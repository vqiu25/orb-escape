package nz.ac.auckland.se206;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class NotificationBuilder {

  // Creates a notification with the given title, message and duration
  public static Notifications createNotification(String title, String message, int duration) {

    // Create notification with the passed in title, message and duration at the top center of the
    // screen
    Notifications notification =
        Notifications.create()
            .title(title)
            .text(message)
            .position(Pos.TOP_CENTER)
            .hideAfter(Duration.seconds(duration))
            .owner(App.getStage());

    return notification;
  }
}
