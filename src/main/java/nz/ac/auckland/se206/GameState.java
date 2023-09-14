package nz.ac.auckland.se206;

/** Represents the state of the game. */
public class GameState {

  // Indicates which riddle has been selected:
  public static boolean isCabinet = false;
  public static boolean isRug = false;

  // Indicates if light switch is on or off
  public static boolean isLightOn = true;

  // Indicates if map has been removed off the wall
  public static boolean isMapOnWall = true;

  // Indicates if all the orbs have been placed in the terminal
  public static boolean isOrbsPlaced = false;

  // Indicates if trees have been chopped down
  public static boolean isChopped = false;

  // Indicates if the axe has been taken
  public static boolean isAxeTaken = false;

  /** Indicates whether the riddle has been resolved. */
  public static boolean isRiddleResolved =
      false; // this is set to true when the GPT thing sees correct

  // check if item as told by the riddle has been clicked - this will allow the TV to be accessed
  public static boolean itemClicked = false;

  // Check if the player has escaped
  public static boolean isRoomEscaped = false;
}
