package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class ForestRoomController extends ControllerMethods {
  @FXML private ImageView leftArrow;
  @FXML private ImageView leftArrowHover;
  @FXML private ImageView leftArrowPressed;
  @FXML private Label roomTimerLabel;
  @FXML private ImageView rock;
  @FXML private ImageView rockOutline;
  @FXML private ImageView fishingRod;
  @FXML private ImageView fishingRodOutline;
  @FXML private ImageView trees;
  @FXML private ImageView treesOutline;
  @FXML private ImageView treesRemoved;
  @FXML private ImageView treesRemovedOutline;
  @FXML private ImageView axe;
  @FXML private ImageView axeOutline;
  @FXML private ImageView axeRemoved;
  @FXML private ImageView axeRemovedOutline;
  @FXML private Polygon treesMini;
  @FXML private Polygon choppedTrees;
  @FXML private Polygon axeGrab;
  @FXML private Polygon emptyLog;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);
  }

  /**
   * When the left arrow is hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonUnhovered(MouseEvent event) {
    leftArrowHover.setOpacity(0);
  }

  /**
   * When the left arrow is no longer hovered over.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonHovered(MouseEvent event) {
    leftArrowHover.setOpacity(1);
  }

  /**
   * When the left arrow pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonClicked(MouseEvent event) {
    leftArrowPressed.setOpacity(1);
  }

  /**
   * When the left arrow is no longer pressed.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void leftButtonUnclick(MouseEvent event) {
    leftArrowPressed.setOpacity(0);
    App.setScene(AppScene.ROOM);
  }

  // Rock
  @FXML
  private void rockClick(MouseEvent event) {}

  @FXML
  private void rockHover(MouseEvent event) {
    rockOutline.setOpacity(1);
  }

  @FXML
  private void rockUnhover(MouseEvent event) {
    rockOutline.setOpacity(0);
  }

  // Fishing Rod
  @FXML
  private void fishingClick(MouseEvent event) {}

  @FXML
  private void fishingHover(MouseEvent event) {
    fishingRodOutline.setOpacity(1);
  }

  @FXML
  private void fishingUnhover(MouseEvent event) {
    fishingRodOutline.setOpacity(0);
  }

  // Trees
  @FXML
  private void treesClick(MouseEvent event) {
    if (!GameState.isChopped) {
      // Prompt user to chop trees, the following goes under an alert or notification, and likely
      // after when the user chops the entire tree
      trees.setOpacity(0);
      treesOutline.setOpacity(0);
      treesMini.setDisable(true);
      choppedTrees.setDisable(false);
      treesRemoved.setOpacity(1);
      GameState.isChopped = true;
    }
  }

  @FXML
  private void treesHover(MouseEvent event) {
    if (!GameState.isChopped) {
      treesOutline.setOpacity(1);
    }
  }

  @FXML
  private void treesUnhover(MouseEvent event) {
    if (!GameState.isChopped) {
      treesOutline.setOpacity(0);
    }
  }

  // Trees Removed
  @FXML
  private void choppedClick(MouseEvent event) {}

  @FXML
  private void choppedHover(MouseEvent event) {
    if (GameState.isChopped) {
      treesRemovedOutline.setOpacity(1);
    }
  }

  @FXML
  private void choppedUnhover(MouseEvent event) {
    if (GameState.isChopped) {
      treesRemovedOutline.setOpacity(0);
    }
  }

  // Axe
  @FXML
  private void axeClick(MouseEvent event) {
    if (!GameState.isAxeTaken) {
      axe.setOpacity(0);
      axeOutline.setOpacity(0);
      axeRemoved.setOpacity(1);
      GameState.isAxeTaken = true;
      axeGrab.setDisable(true);
      emptyLog.setDisable(false);
    }
  }

  @FXML
  private void axeHover(MouseEvent event) {
    if (!GameState.isAxeTaken) {
      axeOutline.setOpacity(1);
    }
  }

  @FXML
  private void axeUnhover(MouseEvent event) {
    if (!GameState.isAxeTaken) {
      axeOutline.setOpacity(0);
    }
  }

  // Axe Removed
  @FXML
  private void emptyLogClick(MouseEvent event) {}

  @FXML
  private void emptyLogHover(MouseEvent event) {
    if (GameState.isAxeTaken) {
      axeRemovedOutline.setOpacity(1);
    }
  }

  @FXML
  private void emptyLogUnhover(MouseEvent event) {
    if (GameState.isAxeTaken) {
      axeRemovedOutline.setOpacity(0);
    }
  }
}
