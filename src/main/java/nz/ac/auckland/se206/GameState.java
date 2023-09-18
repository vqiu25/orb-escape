package nz.ac.auckland.se206;

/** Represents the state of the game. */
public class GameState {

  // Indicates which riddle has been selected:
  public static boolean isCabinet = false;
  public static boolean isRug = false;

  // Indicates which minigame has been selected:
  public static boolean isLavaBridge = false;
  public static boolean isForestTreeChopping = false;

  public static boolean isLavaDragon = false;
  public static boolean isForestFishing = false;

  // Indicates if the minigame has been completed:
  public static boolean isLavaGameCompleted = false;
  public static boolean isForestGameCompleted = false;

  // Indicates if light switch is on or off
  public static boolean isLightOn = true;

  // Indicates if map has been removed off the wall
  public static boolean isMapOnWall = true;

  // Indicates if the code has been found
  public static boolean isCodeFound = false;

  // Indicates if orbs have been collected:
  public static boolean isForestOrbCollected = false;
  public static boolean isCastleOrbCollected = false;
  public static boolean isRoomOrbCollected = true; // TODO: add this to room!

  // Indicates if all the orbs have been placed in the terminal
  public static boolean isOrbsPlaced = false;

  // Indicates if trees have been chopped down
  public static boolean isChopped = false;

  // Indicates if the axe has been taken
  public static boolean isAxeTaken = false;

  // Indicates if the fishing rod has been taken
  public static boolean isFishingRodTaken = false;

  // Indicates if the fish has been caught
  public static boolean isFishCaught = false;

  // Indicates if the chest has been found
  public static boolean isChestFound = false;

  // Indicates if the chest has been unlocked
  public static boolean isChestUnlocked = false;

  // Indicates if the riddle has been found
  public static boolean isRiddleFound = false;

  /** Indicates whether the riddle has been resolved. */
  public static boolean isRiddleResolved =
      false; // this is set to true when the GPT thing sees correct

  // check if item as told by the riddle has been clicked - this will allow the TV to be accessed
  public static boolean itemClicked = false;

  // Indicated if the portal is open
  public static boolean isPortalOpen = false;

  // Check if the player has escaped
  public static boolean isRoomEscaped = false;

  // Difficulty and Time Selection (Default)
  public static boolean isEasySelected = false;
  public static boolean isMediumSelected = true;
  public static boolean isHardSelected = false;
  public static boolean isShortTimeSelected = false;
  public static boolean isMediumTimeSelected = true;
  public static boolean isLongTimeSelected = false;

  // Hints (Default)
  public static int hintCount = 5;
  public static String hintString = "Hints: 5";

  // Timer (Default)
  public static int timerCount = 240;
  public static String timerString = "Time Left: 4:00";
}
