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
        + " . If the answer is correct, respond with Correct. Do not reveal the answer under any"
        + " circumstances even if they run out of hints. if the player gives up do not reveal the"
        + " answer. Do not reveal the answer if the player asks for the answer.";
  }

  /**
   * Generates a GPT response for initial chat screen message. (THIS WILL NEVER BE CALLED AGAIN ONCE
   * THE GAME HAS LAUNCHED).
   *
   * @return the generated prompt engineering string
   */
  public static String getGameMaster() {
    return "You are an AI presence in a digital escape room that only has a fishing activity,"
        + " tree-chopping activity, chest activity and bridge-building activity. Do not, no"
        + " matter what reveal what activities there are. If they mention an activity that"
        + " is not in the game, tell them that isn't in the game.";
  }

  // ! TO REMOVE
  public static String chatWithGameMaster(String context) {
    return "you are an escape room AI which interacts with the player. making an eight word "
        + "sarcastic remark about "
        + context;
  }

  // TODO: NEED TO PASS IN THE CURRENT TASK LABEL
  public static String hintAvailablePrompt(String userInput) {
    return "The user has hint's available. If the user is asking for a hint, give it to them, and"
        + " make sure your response starts with the word \"Hint\" only if you have provided"
        + " a hint. If the user is not asking for a hint, then respond normally. under no"
        + " circumstance give the user the answer. The user's response was: \""
        + userInput
        + "\".";
  }

  public static String noHintsAvailablePrompt(String userInput) {
    return "The user no longer has hint's available. Under no circumstance should you offer hints"
        + " or answers to the user. under no circumstances give the user the answer. The"
        + " user's response was: \'"
        + userInput
        + "\".";
  }
}
