package nz.ac.auckland.se206.controllers;

import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class TreeChoppingMiniGameController extends ControllerMethods {

  @FXML private Label lblTimer;
  @FXML private Label lblTask;
  @FXML private Label lblHints;
  @FXML private Label chopCountLabel;

  @FXML private ImageView miniTrees;
  @FXML private ImageView miniTreesHit;
  @FXML private ImageView choppedMiniTrees;

  @FXML private ImageView blueButtonOne;
  @FXML private ImageView blueButtonTwo;
  @FXML private ImageView blueButtonThree;
  @FXML private ImageView backButtonOne;
  @FXML private ImageView backButtonTwo;
  @FXML private ImageView backButtonThree;

  // Inventory Items
  @FXML private ImageView fishingRodIcon;
  @FXML private ImageView axeIcon;
  @FXML private ImageView fishIcon;
  @FXML private ImageView planksIcon;
  @FXML private ImageView blueOrb;
  @FXML private ImageView greenOrb;
  @FXML private ImageView redOrb;

  private int chopCount = 0;
  private int orbDropAfter = new Random().nextInt(9);

  public void initialize() {
    // Bind the timer label to the display time
    lblTimer.textProperty().bind(ControllerMethods.displayTime);

    // Bind the inventory images to their image properties
    fishingRodIcon.imageProperty().bind(ControllerMethods.fishingRodIconImageProperty);
    axeIcon.imageProperty().bind(ControllerMethods.axeIconImageProperty);
    fishIcon.imageProperty().bind(ControllerMethods.fishIconImageProperty);
    planksIcon.imageProperty().bind(ControllerMethods.planksIconImageProperty);
    blueOrb.imageProperty().bind(ControllerMethods.blueOrbImageProperty);
    greenOrb.imageProperty().bind(ControllerMethods.greenOrbImageProperty);
    redOrb.imageProperty().bind(ControllerMethods.redOrbImageProperty);
  }

  @FXML
  private void blueHover(MouseEvent event) {
    blueButtonTwo.setOpacity(1);
  }

  @FXML
  private void blueUnhover(MouseEvent event) {
    blueButtonTwo.setOpacity(0);
  }

  @FXML
  private void bluePressed(MouseEvent event) {
    blueButtonThree.setOpacity(1);
  }

  @FXML
  private void blueReleased(MouseEvent event) {
    if (chopCount < 10) {
      chopCount++;
      chopCountLabel.setText("Chops: " + chopCount + "/10");

      if (chopCount == orbDropAfter) {
        // TODO: Make the orb fall from the tree

        // TODO: Make a notification to say an orb fell from the tree
        System.out.println("An orb fell from the tree!");

        // Make the green orb appear in the inventory
        // NOTE: This should only be done once the orb is clicked by the user (implemented later)
        findGreenOrb();

      } else if (chopCount == 10) {
        // Completed the mini game
        GameState.isChopped = true;
        GameState.isForrestGameCompleted = true;

        // Make the planks appear in the inventory
        findPlanks();

        // TODO: Make a notification to say you cut all the trees down
        System.out.println("You completed the mini game!");

        // Update the trees image in the mini game
        miniTrees.setOpacity(0);
        choppedMiniTrees.setOpacity(1);

        // Update the trees image in the forest room to show the trees are chopped
        App.getForestRoomController().chopTrees();
      }
    } else {
      // TODO: Make a notification say that the tress have already been chopped
      System.out.println("You've already chopped those trees enough");
    }
    blueButtonThree.setOpacity(0);
  }

  @FXML
  private void backHover(MouseEvent event) {
    backButtonTwo.setOpacity(1);
  }

  @FXML
  private void backUnhover(MouseEvent event) {
    backButtonTwo.setOpacity(0);
  }

  @FXML
  private void backPressed(MouseEvent event) {
    backButtonThree.setOpacity(1);
  }

  @FXML
  private void backReleased(MouseEvent event) {
    backButtonThree.setOpacity(0);
    App.setScene(AppScene.FOREST);
  }
}
