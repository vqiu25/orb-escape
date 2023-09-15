package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;

public class BridgeGameController {

  @FXML
  private void goBack() {
    App.setScene(AppScene.LAVA);
  }
}
