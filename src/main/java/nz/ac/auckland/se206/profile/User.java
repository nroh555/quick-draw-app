package nz.ac.auckland.se206.profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import nz.ac.auckland.se206.models.Level;

public class User implements Serializable {
  private String username;

  // User statistics
  private Integer wins;
  private Integer losses;
  private Integer fastestWin;

  // Difficulty information
  private Level accuracySetting;
  private Level wordsSetting;
  private Level timeSetting;
  private Level confidenceSetting;

  // Used words
  private ArrayList<String> usedWords;

  public User(String username) {
    this.username = username;
    this.wins = 0;
    this.losses = 0;
    this.fastestWin = 0;
    this.accuracySetting = Level.EASY;
    this.wordsSetting = Level.EASY;
    this.timeSetting = Level.EASY;
    this.confidenceSetting = Level.EASY;
    this.usedWords = new ArrayList<String>();
  }

  /**
   * This loads user details from the file to create a user instance
   *
   * @param username
   * @param wins
   * @param losses
   * @param fastestWin
   */
  public void loadUser(
      String username,
      Integer wins,
      Integer losses,
      Integer fastestWin,
      Level accuracySetting,
      Level wordsSetting,
      Level timeSetting,
      Level confidenceSetting) {
    // Stores the user's details into corresponding variables
    this.username = username;
    this.wins = wins;
    this.losses = losses;
    this.fastestWin = fastestWin;
    this.accuracySetting = accuracySetting;
    this.wordsSetting = wordsSetting;
    this.timeSetting = timeSetting;
    this.confidenceSetting = confidenceSetting;
  }

  /**
   * This formats all information about the user so it can be saved to a file
   *
   * @return String in the format to go into the save file
   */
  // TODO SAVE DIFFICULTY DETAILS
  public String getSaveDetails() {
    // Format the difficulty settings for save
    ArrayList<Level> difficultyArray =
        new ArrayList<Level>(
            Arrays.asList(accuracySetting, wordsSetting, timeSetting, confidenceSetting));
    String difficultySettingsString = formatDifficultySettings(difficultyArray);

    // Creates string of the users details
    String saveString =
        username
            + ":"
            + wins.toString()
            + ":"
            + losses.toString()
            + ":"
            + fastestWin.toString()
            + ":"
            + difficultySettingsString;

    /**
     * Returns the user details which includes username, number of wins and losses as well as
     * fastest win time, and all the difficulty settings
     */
    return saveString;
  }

  /**
   * This gets and formats all key information (excluding words) about a user to be displayed
   *
   * @return String to display the key details
   */
  public String formatUserDetails() {
    String fastestWinDisplay;

    // Change fastest win display text depending on if the user has won yet
    if (fastestWin == 0) {
      fastestWinDisplay = "N/A";
    } else {
      fastestWinDisplay = fastestWin.toString() + "s";
    }

    // Creates and formats string of the key user details
    String displayString =
        "Wins: "
            + wins.toString()
            + "\nLosses: "
            + losses.toString()
            + "\nFastest win: "
            + fastestWinDisplay
            + "\nUsed words: ";

    return displayString;
  }

  /**
   * This returns the array list of used words
   *
   * @return ArrayList<String>
   */
  public ArrayList<String> getUsedWords() {
    return usedWords;
  }

  /**
   * This adds a word to the list of used words
   *
   * @param word
   */
  public void addUsedWord(String word) {
    this.usedWords.add(word);
  }

  /**
   * This returns a string containing the used words on new lines
   *
   * @return
   */
  public String getUsedWordsString() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < usedWords.size(); i++) {
      sb.append(usedWords.get(i) + "\n");
    }

    return sb.toString();
  }

  /**
   * Takes the list of used words and chucks it all into a string so it can be saved to a file
   *
   * @param usedWords
   * @return String format of the used words
   */
  public String formatWordsForSave(ArrayList<String> usedWords) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < usedWords.size(); i++) {
      sb.append(";" + usedWords.get(i));
    }

    return sb.toString().substring(1, sb.length());
  }

  /**
   * This gets the string version of all the used words and chucks them all into an array
   *
   * @param wordsString
   */
  public void getWordsToArray(String wordsString) {

    String[] parts = wordsString.split(";");

    for (String part : parts) {
      this.usedWords.add(part);
    }
  }

  /**
   * Sets either of the wins, losses, or fastestWins stats for the user
   *
   * @param wins
   * @param losses
   * @param fastestWin
   */
  public void setStats(Integer wins, Integer losses, Integer fastestWin) {
    this.wins = wins;
    this.losses = losses;
    this.fastestWin = fastestWin;
  }

  /**
   * Gets the user's username
   *
   * @return String
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the user's number of wins
   *
   * @return Integer
   */
  public Integer getWins() {
    return wins;
  }

  /**
   * Gets the user's number of losses
   *
   * @return Integer
   */
  public Integer getLosses() {
    return losses;
  }

  /**
   * Gets the user's fastest win
   *
   * @return Integer
   */
  public Integer getFastestWin() {
    return fastestWin;
  }

  private String formatDifficultySettings(ArrayList<Level> difficultyArray) {
    String difficultyString = "";
    for (int i = 0; i < difficultyArray.size(); i++) {
      System.out.println(difficultyArray.get(i));
      if (difficultyArray.get(i) == Level.EASY) {
        difficultyString = difficultyString + "E";
      } else if (difficultyArray.get(i) == Level.MEDIUM) {
        difficultyString = difficultyString + "M";
      } else if (difficultyArray.get(i) == Level.HARD) {
        difficultyString = difficultyString + "H";
      } else if (difficultyArray.get(i) == Level.MASTER) {
        difficultyString = difficultyString + "S";
      } else {
        System.out.println("Error - no corresponding difficulty found");
      }
    }

    return difficultyString;
  }
}
