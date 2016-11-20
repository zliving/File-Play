package GameObjects;

import Scenes.Lobby;

/**
 * The GameSettings class will store the information for the preferences that were selected in the
 * lobby screen and will have methods that return values corresponding to the values needed in the
 * buildUrl method of the UrlBuilderImp class.
 */
public class GameSettings {
  private final String amount;
  private final String category;
  private final String difficulty;

  public GameSettings(String amount, String category, String difficulty) {
    this.amount = amount;
    this.category = category;
    this.difficulty = difficulty;
  }

  public String getCategoryValue() {
    if (category.compareTo(Lobby.GENERAL_TEXT) == 0) {
      return "9";
    } else if (category.compareTo(Lobby.GEOGRAPHY_TEXT) == 0) {
      return "22";
    } else if (category.compareTo(Lobby.MUSIC_TEXT) == 0) {
      return "12";
    } else if (category.compareTo(Lobby.VIDEOGAMES_TEXT) == 0) {
      return "15";
    } else if (category.compareTo(Lobby.SCIENCE_NATURE_TEXT) == 0) {
      return "17";
    } else {
      return "23";
    }
  }

  public String getDifficultyValue() {
    if (difficulty.compareTo(Lobby.EASY_TEXT) == 0) {
      return "easy";
    } else if (difficulty.compareTo(Lobby.NORMAL_TEXT) == 0) {
      return "medium";
    } else {
      return "hard";
    }
  }

  public String getAmountValue() {
    if (amount.compareTo("Short (5)") == 0) {
      return "5";
    } else if (amount.compareTo("Medium (7)") == 0) {
      return "7";
    } else {
      return "9";
    }
  }
}
