package nz.ac.auckland.se206;

import java.io.IOException;
import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nz.ac.auckland.se206.SceneManager.AppScene;
import nz.ac.auckland.se206.controllers.ForestRoomController;
import nz.ac.auckland.se206.controllers.LavaRoomController;
import nz.ac.auckland.se206.controllers.RoomController;

/**
 * This is the entry point of the JavaFX application, while you can change this class, it should
 * remain as the class that runs the JavaFX application.
 */
public class App extends Application {

  private static Scene currentScene;

  // Used to store a reference which can be passed:
  private static RoomController roomController;
  private static LavaRoomController lavaRoomController;
  private static ForestRoomController forestRoomController;
  private static Stage stageTest;

  public static void main(final String[] args) {
    launch();
  }

  /**
   * Returns a stage reference:
   *
   * @return Stage reference.
   */
  public static Stage getStage() {
    return stageTest;
  }

  /**
   * Returns the node associated to the input file. The method expects that the file is located in
   * "src/main/resources/fxml".
   *
   * @param fxml The name of FXML files to load without the .fxml extension.
   * @returnv The node associated to the input file.
   * @throws IOException
   */
  private static FXMLLoader loadLoader(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
    return fxmlLoader;
  }

  /**
   * Used to set scenes that DO require the state the be saved.
   *
   * @param scene The scene to be set.
   */
  public static void setScene(AppScene scene) {
    currentScene.setRoot(SceneManager.getScene(scene));
  }

  /**
   * Returns the room controller.
   *
   * @return Reference to the lava room controller.
   */
  public static RoomController getRoomController() {
    return roomController;
  }

  /**
   * Returns the lava room controller.
   *
   * @return Reference to the lava room controller.
   */
  public static LavaRoomController getLavaRoomController() {
    return lavaRoomController;
  }

  public static ForestRoomController getForestRoomController() {
    return forestRoomController;
  }

  /**
   * This method is invoked when the application starts. It loads and shows the "Start" scene.
   *
   * @param stage The primary stage of the application.
   * @throws IOException If "src/main/resources/fxml/canvas.fxml" is not found.
   */
  @Override
  public void start(final Stage stage) throws IOException {

    // Set mini game states:
    int randomInt = new Random().nextInt(10);
    System.out.println(randomInt);

    if (randomInt > 5) {
      GameState.isLavaBridge = true;
      GameState.isForrestTreeChopping = true;
    } else {
      GameState.isLavaDragon = true;
      GameState.isForrestFishing = true;
    }

    // Add scenes to hashmap.
    // SceneManager.addScene(AppScene.START, loadLoader("start").load());
    // SceneManager.addScene(AppScene.TUTORIAL, loadLoader("tutorial").load());
    // SceneManager.addScene(AppScene.SETTINGS, loadLoader("settings").load());
    // SceneManager.addScene(AppScene.GAMEOVER, loadLoader("gameover").load());
    // SceneManager.addScene(AppScene.GAMEFINISHED, loadLoader("gamefinished").load());
    // SceneManager.addScene(AppScene.STORY, loadLoader("story").load());
    // SceneManager.addScene(AppScene.OPTIONS, loadLoader("options").load());
    // SceneManager.addScene(AppScene.HELP, loadLoader("help").load());
    // SceneManager.addScene(AppScene.CHAT, loadLoader("chat").load());
    // SceneManager.addScene(AppScene.TERMINAL, loadLoader("terminal").load());
    // SceneManager.addScene(AppScene.TELEVISION, loadLoader("television").load());
    // SceneManager.addScene(AppScene.KEYPAD, loadLoader("keypad").load());
    // SceneManager.addScene(AppScene.FISHING, loadLoader("fishingMiniGame").load());
    // SceneManager.addScene(AppScene.TREES, loadLoader("treeChoppingMiniGame").load());
    // SceneManager.addScene(AppScene.LAVA, loadLoader("lavaRoom").load());
    // SceneManager.addScene(AppScene.BRIDGE_GAME, loadLoader("bridgeGame").load());
    // SceneManager.addScene(AppScene.CASTLE, loadLoader("castleRoom").load());

    // // Store references to the room controller and forest room controller
    // FXMLLoader room = loadLoader("room");
    // SceneManager.addScene(AppScene.ROOM, room.load());
    // roomController = room.getController();

    // // Store references to the lava room controller:
    // FXMLLoader lavaRoom = loadLoader("lavaRoom");
    // SceneManager.addScene(AppScene.LAVA, lavaRoom.load());
    // lavaRoomController = lavaRoom.getController();

    // FXMLLoader forestRoom = loadLoader("forestRoom");
    // SceneManager.addScene(AppScene.FOREST, forestRoom.load());
    // forestRoomController = forestRoom.getController();

    // // Store stage reference:
    // stageTest = stage;

    // Fetch start scene from hashmap and set scene:
    currentScene = new Scene(loadLoader("castleRoom").load(), 800, 625);
    stage.setScene(currentScene);
    stage.setResizable(false);
    stage.show();

    stage.setOnCloseRequest(e -> System.exit(0));
  }
}
