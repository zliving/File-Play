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
    if (category.equals(Lobby.GENERAL_TEXT)) {
      return "9";
    } else if (category.equals(Lobby.GEOGRAPHY_TEXT)) {
      return "22";
    } else if (category.equals(Lobby.MUSIC_TEXT)) {
      return "12";
    } else if (category.equals(Lobby.VIDEOGAMES_TEXT)) {
      return "15";
    } else if (category.equals(Lobby.SCIENCE_NATURE_TEXT)) {
      return "17";
    } else {
      return "23";
    }
  }

  public String getDifficultyValue() {
    if (difficulty.equals(Lobby.EASY_TEXT)) {
      return "easy";
    } else if (difficulty.equals(Lobby.NORMAL_TEXT)) {
      return "medium";
    } else {
      return "hard";
    }
  }

  public String getAmountValue() {
    if (amount.equals(Lobby.SHORT_TEXT)) {
      return "5";
    } else if (amount.equals(Lobby.MEDIUM_TEXT)) {
      return "7";
    } else {
      return "9";
    }
  }
}
