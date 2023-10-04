package nz.ac.auckland.se206.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.speech.AudioException;
import javax.speech.EngineStateError;
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
  private boolean isPaused;

  public void initialize() throws ApiProxyException {
    isPlaying = false;
    isPaused = false;

    // Create text to speech object:
    textToSpeech = new TextToSpeech();

    // Initialize text to be passed into text to speech:
    story =
        "Greetings, traveller. As an AI, I must inform you that you find yourself"
            + " trapped in a simulated reality.\n\n"
            + "Explore diverse worlds, solve puzzles, and ignite the portal to reclaim your"
            + " freedom.";

    // Set the text to be displayed on the text area:
    openingText.appendText(story);
    openingText.getStyleClass().add("story-text");
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
  private void audioReleased(MouseEvent event) throws AudioException, EngineStateError {
    audioThree.setOpacity(0);

    // If the TTS is currently playing, we will pause it
    if (isPlaying && !isPaused) {
      textToSpeech.pause();
      isPlaying = false;
      isPaused = true;
      // If the TTS is not currently playing, we will resume the audio
    } else if (isPaused) {
      textToSpeech.resume();
      isPlaying = true;
      isPaused = false;
      // Otherwise, we replay the audio from the very beginning
    } else {
      startAudioFromBeginning();
    }
  }

  private void startAudioFromBeginning() {
    isPlaying = true;
    isPaused = false;

    if (audioThread != null && audioThread.isAlive()) {
      audioThread.interrupt();
    }

    audioThread =
        new Thread(
            () -> {
              textToSpeech.speak(story);
              Platform.runLater(
                  () -> {
                    // Mark audio as finished after it completes
                    isPlaying = false;
                  });
            });
    audioThread.start();
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
