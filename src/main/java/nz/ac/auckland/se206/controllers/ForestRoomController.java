package nz.ac.auckland.se206.controllers;

import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.NotificationBuilder;
import nz.ac.auckland.se206.SceneManager.AppScene;
import org.controlsfx.control.Notifications;

public class ForestRoomController extends ControllerMethods {
  @FXML private ImageView leftArrow;
  @FXML private ImageView leftArrowHover;
  @FXML private ImageView leftArrowPressed;
  @FXML private Label lblTimer;
  @FXML private Label lblTask;
  @FXML private Label lblHints;
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

  // Game Master
  @FXML private ImageView gameMasterDefault;
  @FXML private ImageView gameMasterChat;

  // Inventory Items
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
  @FXML private ImageView helpOne;
  @FXML private ImageView helpTwo;
  @FXML private ImageView helpThree;
  @FXML private ImageView settingsOne;
  @FXML private ImageView settingsTwo;
  @FXML private ImageView settingsThree;

  public void initialize() {
    // Bind the labels to the display values and styles
    lblTimer.textFillProperty().bind(ControllerMethods.timerTextFill);
    lblTimer.textProperty().bind(ControllerMethods.displayTime);
    lblTask.textProperty().bind(ControllerMethods.displayTask);
    lblHints.textProperty().bind(ControllerMethods.displayHints);

    // Initialise the inventory items
    fishingRodIcon = getFishingRodIcon();
    axeIcon = getAxeIcon();
    fishIcon = getFishIcon();
    planksIcon = getPlanksIcon();
    blueOrb = getBlueOrb();
    greenOrb = getGreenOrb();
    redOrb = getRedOrb();

    // Bind the inventory images to their image properties
    bindInventory();

    // Bind the trees image to its image property
    trees.imageProperty().bind(ControllerMethods.treesImageProperty);
    treesOutline.imageProperty().bind(ControllerMethods.treesOutlineImageProperty);

    int randomInt = new Random().nextInt(10);
    if (randomInt > 4) {
      initialiseGreenTrees();
    } else {
      initialisePinkTrees();
    }

    // Based on minigame selected, either show dragon scenario or broken bridge scenario:
    if (GameState.isForestTreeChopping) {
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

  @FXML
  private void onHelpHover(MouseEvent event) {
    helpTwo.setOpacity(1);
  }

  @FXML
  private void onHelpUnhover(MouseEvent event) {
    helpTwo.setOpacity(0);
  }

  @FXML
  private void onHelpPressed(MouseEvent event) {
    helpThree.setOpacity(1);
  }

  /**
   * Opens the help window GUI.
   *
   * @param event Mouse click event.
   */
  @FXML
  private void onHelpReleased(MouseEvent event) {
    helpThree.setOpacity(0);

    // Store forrest room scene in stack:
    GameState.lastScene = AppScene.FOREST;

    App.setScene(AppScene.HELP);
  }

  @FXML
  private void onSettingsHover(MouseEvent event) {
    settingsTwo.setOpacity(1);
  }

  @FXML
  private void onSettingsUnhover(MouseEvent event) {
    settingsTwo.setOpacity(0);
  }

  @FXML
  private void onSettingsPressed(MouseEvent event) {
    settingsThree.setOpacity(1);
  }

  /**
   * Opens the settings scene.
   *
   * @param event
   */
  @FXML
  private void onSettingsReleased(MouseEvent event) {
    settingsThree.setOpacity(0);

    // Store forrest room scene in stack:
    GameState.lastScene = AppScene.FOREST;

    App.setScene(AppScene.SETTINGS);
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
  private void rockClick(MouseEvent event) {
    // Notification to the user
    Notifications message =
        NotificationBuilder.createNotification("CLOUD: ", "Nothing under there...", 5);
    message.show();
  }

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
    // Add the fishing rod to inventory
    if (GameState.isForestFishing) {
      GameState.isFishingRodTaken = true;
      updateTask();
      fishingRod.setOpacity(0);
      fishingRodOutline.setOpacity(0);
      dock.setOpacity(1);
      dockWithoutRod.setDisable(false);
      fishingMini.setDisable(true);
      findFishingRod();
    }
  }

  @FXML
  private void fishingHover(MouseEvent event) {
    if (GameState.isForestFishing) {
      fishingRodOutline.setOpacity(1);
    }
  }

  @FXML
  private void fishingUnhover(MouseEvent event) {
    if (GameState.isForestFishing) {
      fishingRodOutline.setOpacity(0);
    }
  }

  // Dock without fishing rod
  @FXML
  private void dockClick(MouseEvent event) {

    // If the game selected is the fishing game:
    if (GameState.isForestFishing) {
      // If the riddle has not been found prompt user to find the riddle:
      if (!GameState.isRiddleFound) {
        findRiddle();

        return;
      } else if (!GameState.isRiddleResolved) {
        // If the riddle has been found but not solved, prompt user to solve the riddle:
        solveRiddle();

        return;
      } else if (!GameState.isRoomOrbCollected) {
        // If the riddle has been solved but the orb has not been collected, prompt user to find
        findRoomOrb();

        return;
      } else if (GameState.isFishingRodTaken) {
        App.setScene(AppScene.FISHING);
      }
    } else {
      // If it is the tree chopping game.
      Notifications message =
          NotificationBuilder.createNotification(
              "CLOUD: ", "Hah! Good luck trying to swim away.", 5);
      message.show();
    }
  }

  @FXML
  private void dockHover(MouseEvent event) {
    if (!GameState.isForestFishing || GameState.isFishingRodTaken) {
      dockOutline.setOpacity(1);
    }
  }

  @FXML
  private void dockUnhover(MouseEvent event) {
    if (!GameState.isForestFishing || GameState.isFishingRodTaken) {
      dockOutline.setOpacity(0);
    }
  }

  // Trees
  @FXML
  private void treesClick(MouseEvent event) {
    if (GameState.isForestTreeChopping) {
      if (!GameState.isRiddleFound) {
        // Prompt user to find the riddle first before interacting with the trees
        findRiddle();

        return;
      } else if (!GameState.isRiddleResolved) {
        // Prompt user to solve the riddle first before interacting with the trees
        solveRiddle();

        return;
      } else if (!GameState.isRoomOrbCollected) {
        // Prompt user to find the orb first before interacting with the trees
        findRoomOrb();

        return;
      } else if (GameState.isAxeTaken) {
        // If the axe has been taken, take user to tree chopping game:
        App.setScene(AppScene.TREES);
      } else {
        // Axe not taken but riddle solved and orb collected
        Notifications message =
            NotificationBuilder.createNotification(
                "CLOUD: ", "Try finding something to cut down the trees!", 5);
        message.show();
      }
    } else {
      // Do something else if they are in the other version of the game
      Notifications message =
          NotificationBuilder.createNotification(
              "CLOUD: ", "Leaf me alone, I'm trying to escape", 5);
      message.show();
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
    if (GameState.isForestTreeChopping && GameState.isChopped) {
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

  // Axe logic
  @FXML
  private void axeClick(MouseEvent event) {
    // If the axe has not been taken:
    if (!GameState.isAxeTaken) {
      // Remove the axe from inventory
      findAxe();

      // Remove axe image from the scene
      axe.setOpacity(0);
      axeOutline.setOpacity(0);
      axeRemoved.setOpacity(1);

      GameState.isAxeTaken = true;
      updateTask();

      // Disable the axe and enable the empty log
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
  private void emptyLogClick(MouseEvent event) {
    // if it isnt the tree chopping game:
    if (!GameState.isForestTreeChopping) {
      Notifications message =
          NotificationBuilder.createNotification(
              "The Lorax: ", "I am the Lorax and I speak for the trees!", 5);
      message.show();
    } else {
      // If it is the tree chopping game
      Notifications message =
          NotificationBuilder.createNotification("CLOUD: ", "Axe already taken!", 5);
      message.show();
    }
  }

  @FXML
  private void emptyLogHover(MouseEvent event) {
    if (GameState.isAxeTaken || !GameState.isForestTreeChopping) {
      axeRemovedOutline.setOpacity(1);
    }
  }

  @FXML
  private void emptyLogUnhover(MouseEvent event) {
    if (GameState.isAxeTaken || !GameState.isForestTreeChopping) {
      axeRemovedOutline.setOpacity(0);
    }
  }

  // Bottom Right Game Master Button
  @FXML
  private void gameMasterOnHover(MouseEvent event) {
    gameMasterDefault.setOpacity(0);
    gameMasterChat.setOpacity(1);
  }

  @FXML
  private void gameMasterOnUnhover(MouseEvent event) {
    gameMasterDefault.setOpacity(1);
    gameMasterChat.setOpacity(0);
  }

  @FXML
  private void gameMasterOnClick(MouseEvent event) {

    if (GameState.isForestTreeChopping) {
      setForestAxeOpacity();
    } else {
      setForestRodOpacity();
    }
    if (GameState.isChopped) {
      setForestTreesRemovedOpacity();
    }

    // Store forrest room scene in stack:
    GameState.lastScene = AppScene.FOREST;

    App.setScene(AppScene.CHAT);
  }

  public void findRiddle() {
    // Initialize orb notification message
    Notifications orbMessage =
        NotificationBuilder.createNotification("CLOUD: ", "See if you can find a riddle first!", 6);
    orbMessage.show();
  }

  public void solveRiddle() {
    // Initialize orb notification message
    Notifications orbMessage =
        NotificationBuilder.createNotification(
            "CLOUD: ", "Hmm... Try solving the riddle first!", 6);
    orbMessage.show();
  }

  public void findRoomOrb() {
    // Initialize orb notification message
    Notifications orbMessage =
        NotificationBuilder.createNotification("CLOUD: ", "Try searching for an orb first!", 6);
    orbMessage.show();
  }
}
