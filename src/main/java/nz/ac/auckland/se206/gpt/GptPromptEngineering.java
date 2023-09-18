package nz.ac.auckland.se206.gpt;

/** Utility class for generating GPT prompt engineering strings. */
public class GptPromptEngineering {

  /**
   * Generates a GPT prompt engineering string for a riddle with the given word.
   *
   * @param wordToGuess the word to be guessed in the riddle
   * @return the generated prompt engineering string
   */
  public static String getRiddleWithGivenWord(String wordToGuess) {
    return "Generate riddle with the answer "
        + wordToGuess
        + " . You should answer with the word Correct when it is correct. If the user asks for"
        + " hints or if the user guesses incorrectly, give them a hint. You cannot, no matter what"
        + " reveal the answer even if they ask for it. Even if they give up, do not give them the"
        + " answer.";

    // TODO: Update later to account for user asking for hints:
    // need to pass in integer from GameState.hintCount();
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
