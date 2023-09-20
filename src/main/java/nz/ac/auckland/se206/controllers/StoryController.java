package nz.ac.auckland.se206.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppScene;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;
import nz.ac.auckland.se206.speech.TextToSpeech;

public class StoryController extends ControllerMethods {

  // Buttons are now next instead of back
  @FXML private ImageView backOneButtn;
  @FXML private ImageView backTwoButton;
  @FXML private ImageView backThreeButton;
  @FXML private TextArea openingText;
  @FXML private ImageView audioOne;
  @FXML private ImageView audioTwo;
  @FXML private ImageView audioThree;

  private String story;
  private TextToSpeech textToSpeech;
  private Thread audioThread;
  private boolean isPlaying;

  public void initialize() throws ApiProxyException {
    isPlaying = false;
    textToSpeech = new TextToSpeech();
    story =
        "Greetings, traveler. As an AI intelligence, I must inform you that you find yourself"
            + " trapped in a simulated reality.\n\n"
            + "Explore diverse worlds, solve puzzles, and ignite the portal to reclaim your"
            + " freedom.";
    openingText.appendText(story);
  }

  @FXML
  private void audioHover(MouseEvent event) {
    audioTwo.setOpacity(1);
  }

  @FXML
  private void audioUnhover(MouseEvent event) {
    audioTwo.setOpacity(0);
  }

  @FXML
  private void audioPressed(MouseEvent event) {
    audioThree.setOpacity(1);
  }

  @FXML
  private void audioReleased(MouseEvent event) {
    audioThree.setOpacity(0);
    // If audio is playing, don't start another thread
    if (isPlaying) {
      return;
    } else {
      // If audio is not playing, start it
      isPlaying = true; // Update the state

      // Create a new audio thread
      audioThread =
          new Thread(
              () -> {
                textToSpeech.speak(story);
                Platform.runLater(
                    () -> {
                      isPlaying = false; // Update the state when playback completes
                    });
              });

      // Start the new audio thread
      audioThread.start();
    }
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
    stopAudioThread();
    backThreeButton.setOpacity(0);
    App.setScene(AppScene.TUTORIAL);
  }

  private void stopAudioThread() {
    if (audioThread != null && audioThread.isAlive()) {
      textToSpeech.terminate();
      audioThread.interrupt();
    }
  }
}
