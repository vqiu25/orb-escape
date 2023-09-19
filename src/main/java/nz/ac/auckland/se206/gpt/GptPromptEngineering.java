package nz.ac.auckland.se206.gpt;

/** Utility class for generating GPT prompt engineering strings. */
public class GptPromptEngineering {

  /**
   * Generates a GPT prompt engineering string for a riddle with the given word.
   *
   * @param wordToGuess the word to be guessed in the riddle
   * @return the generated prompt engineering string
   */
  public static String getRiddleWithGivenWord(String wordToGuess, String numberOfGuesses) {

    // TODO: Update to include hints - might need refactoring!
    // return "tell me a riddle with the answer "
    //     + wordToGuess
    //     + " . You should answer with the word Correct when is correct. The player only has "
    //     + numberOfGuesses
    //     + " hints. Do not give anymore hints after the player used all "
    //     + numberOfGuesses
    //     + " guesses. If the player asks for a"
    //     + " hint, respond with the first word Hint and give them a hint. However, you cannot, no"
    //     + " matter what, reveal the answer even if the player asks for it. Even if player gives
    // up,"
    //     + " do not give the answer.";

    return "tell me a riddle with the answer "
        + wordToGuess
        + " . You should answer with the word Correct when is correct. You cannot, no matter what"
        + " reveal the answer even if the player asks for it or gives up.";
  }

  // TODO: UPDATE THIS LATER
  public static String getGameMaster(String numberOfGuesses) {
    return "You are an escape room AI which interacts with player by giving them hints.";
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
