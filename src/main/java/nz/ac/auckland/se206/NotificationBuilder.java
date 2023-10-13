package nz.ac.auckland.se206;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/** This class handles the logic required to create a notification. */
public class NotificationBuilder {

  /**
   * This method creates a notification with the passed in title, message and duration at the top
   * center of the screen.
   *
   * @param title The title of the notification.
   * @param message The message of the notification.
   * @param duration The duration of the notification.
   * @return The notification that was created.
   */
  public static Notifications createNotification(String title, String message, int duration) {

    // Adds a robot image to the notifications
    ImageView robotIcon =
        new ImageView(Notifications.class.getResource("/images/robotIconTwo.png").toExternalForm());

    robotIcon.setPreserveRatio(true);
    robotIcon.setFitHeight(40);

    // Create notification with the passed in title, message and duration at the top center of the
    // screen
    Notifications notification =
        Notifications.create()
            .text(message)
            .threshold(
                3, Notifications.create().title("Notifications Collapsed. Wait for a cooldown!"))
            .position(Pos.TOP_CENTER)
            .hideAfter(Duration.seconds(duration))
            .graphic(robotIcon)
            .owner(App.getStage());

    return notification;
  }
}
