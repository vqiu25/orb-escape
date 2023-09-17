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
  @FXML private ImageView dock;
  @FXML private ImageView dockOutline;
  @FXML private ImageView trees;
  @FXML private ImageView treesOutline;
  @FXML private ImageView treesRemoved;
  @FXML private ImageView treesRemovedOutline;
  @FXML private ImageView axe;
  @FXML private ImageView axeOutline;
  @FXML private ImageView axeRemoved;
  @FXML private ImageView axeRemovedOutline;
  @FXML private ImageView fishingRodIcon;
  @FXML private ImageView axeIcon;
  @FXML private ImageView fishIcon;
  @FXML private ImageView planksIcon;
  @FXML private ImageView blueOrb;
  @FXML private ImageView greenOrb;
  @FXML private ImageView redOrb;
  @FXML private Polygon treesMini;
  @FXML private Polygon choppedTrees;
  @FXML private Polygon axeGrab;
  @FXML private Polygon emptyLog;
  @FXML private Polygon fishingMini;
  @FXML private Polygon dockWithoutRod;

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);

    // Bind the inventory images to their image properties
    fishingRodIcon.imageProperty().bind(ControllerMethods.fishingRodIconImageProperty);
    axeIcon.imageProperty().bind(ControllerMethods.axeIconImageProperty);
    fishIcon.imageProperty().bind(ControllerMethods.fishIconImageProperty);
    planksIcon.imageProperty().bind(ControllerMethods.planksIconImageProperty);
    blueOrb.imageProperty().bind(ControllerMethods.blueOrbImageProperty);
    greenOrb.imageProperty().bind(ControllerMethods.greenOrbImageProperty);
    redOrb.imageProperty().bind(ControllerMethods.redOrbImageProperty);

    // Based on minigame selected, either show dragon scenario or broken bridge scenario:
    if (GameState.isForrestTreeChopping) {
      // Minigame 1: Trees need to be chopped - Disable and hide fishing rod components

      // Disable fishing rod polygon and enable empty dock polygon
      fishingMini.setDisable(true);
      dockWithoutRod.setDisable(false);

      // Make fishing rod image invisible and empty dock visible
      fishingRod.setOpacity(0);
      dock.setOpacity(1);
    } else {
      // Minigame 2: Fish needs to be caught - Disable and hide tree chopping components

      // Disable axe polygon and enable empty log polygon
      axeGrab.setDisable(true);
      emptyLog.setDisable(false);

      // Make axe image invisible and empty stump image visible
      axe.setOpacity(0);
      axeRemoved.setOpacity(1);
    }
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
  private void fishingClick(MouseEvent event) {
    // Add the fishing rod to inventory and take the user to the fishing mini game
    if (GameState.isForrestFishing) {
      GameState.isFishingRodTaken = true;
      fishingRod.setOpacity(0);
      fishingRodOutline.setOpacity(0);
      dock.setOpacity(1);
      dockWithoutRod.setDisable(false);
      fishingMini.setDisable(true);
      findFishingRod();
      App.setScene(AppScene.FISHING);
    }
  }

  @FXML
  private void fishingHover(MouseEvent event) {
    if (GameState.isForrestFishing) {
      fishingRodOutline.setOpacity(1);
    }
  }

  @FXML
  private void fishingUnhover(MouseEvent event) {
    if (GameState.isForrestFishing) {
      fishingRodOutline.setOpacity(0);
    }
  }

  // Dock without fishing rod
  @FXML
  private void dockClick(MouseEvent event) {
    if (GameState.isForrestFishing && GameState.isFishingRodTaken) {
      App.setScene(AppScene.FISHING);
    }
  }

  @FXML
  private void dockHover(MouseEvent event) {
    if (!GameState.isForrestFishing || GameState.isFishingRodTaken) {
      dockOutline.setOpacity(1);
    }
  }

  @FXML
  private void dockUnhover(MouseEvent event) {
    if (!GameState.isForrestFishing || GameState.isFishingRodTaken) {
      dockOutline.setOpacity(0);
    }
  }

  // Trees
  @FXML
  private void treesClick(MouseEvent event) {
    if (GameState.isForrestTreeChopping) {
      if (GameState.isAxeTaken) {
        // If they are playing the tree chopping version and they have collected the axe, go to tree
        // chopping minigame
        App.setScene(AppScene.TREES);
      } else {
        // TODO: Make a notification to tell the user they need something to cut down the trees
      }
    } else {
      // Do something else if they are in the other version of the game
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

  // Method to swap from the normal trees to the chopped trees
  public void chopTrees() {
    trees.setOpacity(0);
    treesOutline.setOpacity(0);
    treesMini.setDisable(true);
    choppedTrees.setDisable(false);
    treesRemoved.setOpacity(1);
  }

  // Trees Removed
  @FXML
  private void choppedClick(MouseEvent event) {
    if (GameState.isForrestTreeChopping && GameState.isChopped) {
      App.setScene(AppScene.TREES);
    }
  }

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
      findAxe();
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
    if (GameState.isAxeTaken || !GameState.isForrestTreeChopping) {
      axeRemovedOutline.setOpacity(1);
    }
  }

  @FXML
  private void emptyLogUnhover(MouseEvent event) {
    if (GameState.isAxeTaken || !GameState.isForrestTreeChopping) {
      axeRemovedOutline.setOpacity(0);
    }
  }
}
