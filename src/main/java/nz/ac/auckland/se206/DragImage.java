package nz.ac.auckland.se206;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class DragImage {

  boolean correctPosition = false;
  boolean plankMoved = false;

  double originalPlankX;
  double originalPlankY;

  double rectangleX;
  double rectangleY;

  double width;
  double height;

  public void dragImage(ImageView image, Rectangle rectangle) {
    rectangleX = rectangle.getLayoutX();
    rectangleY = rectangle.getLayoutY();

    width = rectangle.getWidth() / 2;
    height = rectangle.getHeight() / 2;

    if (!plankMoved) {
      originalPlankX = image.getLayoutX();
      originalPlankY = image.getLayoutY();
      plankMoved = true;
    }

    image.setOnMouseDragged(
        mouseEvent -> {
          image.setLayoutX(mouseEvent.getSceneX());
          image.setLayoutY(mouseEvent.getSceneY());
        });

    image.setOnMouseReleased(
        mouseEvent -> {
          // snap image to rectangle
          if (image.getLayoutX() > rectangleX - 20
              && image.getLayoutX() < rectangleX + width
              && image.getLayoutY() > rectangleY - 20
              && image.getLayoutY() < rectangleY + height) {
            image.setLayoutX(rectangleX);
            image.setLayoutY(rectangleY);

            correctPosition = true;
          } else {
            image.setLayoutX(originalPlankX);
            image.setLayoutY(originalPlankY);
          }
        });
  }
}
