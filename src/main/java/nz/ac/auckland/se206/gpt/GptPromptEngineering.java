package nz.ac.auckland.se206.gpt;

/** Utility class for generating GPT prompt engineering strings. */
public class GptPromptEngineering {

  /**
   * Generates a GPT prompt engineering string for a riddle with the given word. (THIS WILL NEVER BE
   * CALLED AGAIN ONCE THE GAME HAS LAUNCHED).
   *
   * @param wordToGuess the word to be guessed in the riddle
   * @return the generated prompt engineering string
   */
  public static String getRiddleWithGivenWord(String wordToGuess) {
    return "tell me a riddle with the answer "
        + wordToGuess
        + " . You should answer with the word Correct when is correct. You cannot, no matter what"
        + " reveal the answer even if the player asks for it or gives up.";
  }

  /**
   * Generates a GPT response for initial chat screen message. (THIS WILL NEVER BE CALLED AGAIN ONCE
   * THE GAME HAS LAUNCHED).
   *
   * @return the generated prompt engineering string
   */
  public static String getGameMaster() {
    return "You are an AI which greets the player when they enter the room.";
  }

  // Handles the hint generation for the riddle
  public static String getRiddleHints(String wordToGuess, String numberOfHints) {
    return null; // TODO must update later
  }

  // Handles the hint generation for the general game row
  public static String getGeneralHints(String numberOfHints) {
    return null; // TODO must update later
  }

  /**
   * Generates a GPT prompt engineering string which imitates the precense of a game master.
   *
   * @param context The context of the game master's message.
   * @return the generated prompt engineering string.
   */
  public static String chatWithGameMaster(String context) {
    return "you are an escape room AI which interacts with the player. making an eight word "
        + "sarcastic remark about "
        + context;
  }
}
