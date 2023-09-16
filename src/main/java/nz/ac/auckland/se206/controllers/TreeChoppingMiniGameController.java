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

  @FXML private Label roomTimerLabel;
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

  private int chopCount = 0;
  private int orbDropAfter = new Random().nextInt(9);

  public void initialize() {
    // Bind the timer label to the display time
    roomTimerLabel.textProperty().bind(ControllerMethods.displayTime);
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

        System.out.println("An orb fell from the tree!");
        // TODO: Make a notification to say an orb fell from the tree

      } else if (chopCount == 10) {
        // Completed the mini game
        GameState.isChopped = true;
        GameState.isForrestGameCompleted = true;
        System.out.println("You completed the mini game!");
        // TODO: Make a notification to say you cut all the trees down

        // Update the trees image in the mini game
        miniTrees.setOpacity(0);
        choppedMiniTrees.setOpacity(1);

        // TODO: Update the trees image in the forest room to show the trees are chopped

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
