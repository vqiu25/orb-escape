package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class OptionScreenController extends ControllerMethods {
  @FXML private ImageView easyOneButton;
  @FXML private ImageView easyTwoButton;
  @FXML private ImageView easyThreeButton;
  @FXML private ImageView mediumOneButton;
  @FXML private ImageView mediumTwoButton;
  @FXML private ImageView mediumThreeButton;
  @FXML private ImageView hardOneButton;
  @FXML private ImageView hardTwoButton;
  @FXML private ImageView hardThreeButton;
  @FXML private ImageView shortOneButton;
  @FXML private ImageView shortTwoButton;
  @FXML private ImageView shortThreeButton;
  @FXML private ImageView mediumTimeOneButton;
  @FXML private ImageView mediumTimeTwoButton;
  @FXML private ImageView mediumTimeThreeButton;
  @FXML private ImageView longOneButton;
  @FXML private ImageView longTwoButton;
  @FXML private ImageView longThreeButton;
  @FXML private ImageView backOneButtn;
  @FXML private ImageView backTwoButton;
  @FXML private ImageView backThreeButton;

  @FXML
  private void easyHover(MouseEvent event) {
    easyTwoButton.setOpacity(1);
  }

  @FXML
  private void easyUnhover(MouseEvent event) {
    easyTwoButton.setOpacity(0);
  }

  @FXML
  private void easyClicked(MouseEvent event) {
    if (GameState.isEasySelected) {
      return;
    }
    // Unselect all difficulty buttons
    unselectDifficultyButtons();

    // Select the clicked button
    GameState.isEasySelected = true;
    easyThreeButton.setOpacity(1);
  }

  @FXML
  private void mediumHover(MouseEvent event) {
    mediumTwoButton.setOpacity(1);
  }

  @FXML
  private void mediumUnhover(MouseEvent event) {
    mediumTwoButton.setOpacity(0);
  }

  @FXML
  private void mediumClicked(MouseEvent event) {
    if (GameState.isMediumSelected) {
      return;
    }
    // Unselect all difficulty buttons
    unselectDifficultyButtons();

    // Select the clicked button
    GameState.isMediumSelected = true;
    mediumThreeButton.setOpacity(1);
  }

  @FXML
  private void hardHover(MouseEvent event) {
    hardTwoButton.setOpacity(1);
  }

  @FXML
  private void hardUnhover(MouseEvent event) {
    hardTwoButton.setOpacity(0);
  }

  @FXML
  private void hardClicked(MouseEvent event) {
    if (GameState.isHardSelected) {
      return;
    }
    // Unselect all difficulty buttons
    unselectDifficultyButtons();

    // Select the clicked button
    GameState.isHardSelected = true;
    hardThreeButton.setOpacity(1);
  }

  @FXML
  private void shortTimeHover(MouseEvent event) {
    shortTwoButton.setOpacity(1);
  }

  @FXML
  private void shortTimeUnhover(MouseEvent event) {
    shortTwoButton.setOpacity(0);
  }

  @FXML
  private void shortTimeClicked(MouseEvent event) {
    if (GameState.isShortTimeSelected) {
      return;
    }
    // Unselect all time buttons
    unselectTimeButtons();

    // Select the clicked button
    GameState.isShortTimeSelected = true;
    shortThreeButton.setOpacity(1);
  }

  @FXML
  private void mediumTimeHover(MouseEvent event) {
    mediumTimeTwoButton.setOpacity(1);
  }

  @FXML
  private void mediumTimeUnhover(MouseEvent event) {
    mediumTimeTwoButton.setOpacity(0);
  }

  @FXML
  private void mediumTimeClicked(MouseEvent event) {
    if (GameState.isMediumTimeSelected) {
      return;
    }
    // Unselect all time buttons
    unselectTimeButtons();

    // Select the clicked button
    GameState.isMediumTimeSelected = true;
    mediumTimeThreeButton.setOpacity(1);
  }

  @FXML
  private void longTimeHover(MouseEvent event) {
    longTwoButton.setOpacity(1);
  }

  @FXML
  private void longTimeUnhover(MouseEvent event) {
    longTwoButton.setOpacity(0);
  }

  @FXML
  private void longTimeClicked(MouseEvent event) {
    if (GameState.isLongTimeSelected) {
      return;
    }
    // Unselect all time buttons
    unselectTimeButtons();

    // Select the clicked button
    GameState.isLongTimeSelected = true;
    longThreeButton.setOpacity(1);
  }

  @FXML
  private void backHover(MouseEvent event) {
    backTwoButton.setOpacity(1);
  }

  @FXML
  private void backUnhover(MouseEvent event) {
    backTwoButton.setOpacity(0);
  }

  @FXML
  private void backPressed(MouseEvent event) {
    backThreeButton.setOpacity(1);
  }

  @FXML
  private void backReleased(MouseEvent event) {
    backThreeButton.setOpacity(0);
    App.setScene(AppScene.START);
  }

  private void unselectDifficultyButtons() {
    GameState.isEasySelected = false;
    GameState.isMediumSelected = false;
    GameState.isHardSelected = false;

    // Set opacity for all difficulty buttons accordingly
    easyThreeButton.setOpacity(0);
    mediumThreeButton.setOpacity(0);
    hardThreeButton.setOpacity(0);
  }

  private void unselectTimeButtons() {
    GameState.isShortTimeSelected = false;
    GameState.isMediumTimeSelected = false;
    GameState.isLongTimeSelected = false;

    // Set opacity for all time buttons accordingly
    shortThreeButton.setOpacity(0);
    mediumTimeThreeButton.setOpacity(0);
    longThreeButton.setOpacity(0);
  }
}
