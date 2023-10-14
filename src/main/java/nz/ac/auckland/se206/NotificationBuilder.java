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
  public static Notifications createNotification(String message) {

    // Create notification with the passed in title, message and duration at the top center of the
    // screen
    Notifications notification =
        Notifications.create()
            .text(message)
            .threshold(
                3, Notifications.create().title("Notifications Collapsed. Wait for a cooldown!"))
            .position(Pos.TOP_CENTER)
            .hideAfter(Duration.seconds(3))
            .graphic(createImageView("loraxPadded"))
            .owner(App.getStage());

    return notification;
  }

  /**
   * Creates an ImageView from the specified filename.
   *
   * @param filename The filename of the image.
   * @return An ImageView object configured with the specified image.
   */
  private static ImageView createImageView(String filename) {
    ImageView imageView =
        new ImageView(
            Notifications.class.getResource("/images/" + filename + ".png").toExternalForm());
    imageView.setPreserveRatio(true);
    imageView.setFitHeight(40);
    return imageView;
  }
}
