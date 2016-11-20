package GameObjects;

import Scenes.Lobby;

/**
 * The UrlBuilder class is a utility class used to build the URL for OpenDB required to retrieve
 * questions given preferences regarding number of questions, category, and difficulty.
 */
public final class UrlBuilderImp implements UrlBuilder {
  private static final String BASE = "https://www.opentdb.com/api.php";
  private static final String DIFFICULTY = "difficulty=";
  private static final String CATEGORY = "category=";
  private static final String AMOUNT = "amount=";
  private static final String MULTIPLE_CHOICE_QUERY = "type=multiple";

  public UrlBuilderImp() {
  }

  @Override
  public String buildUrl(String amount, String category, String difficulty) {
    return BASE + "?" + AMOUNT + getAmountValue(amount) + "&" + CATEGORY +
           getCategoryValue(category) + "&" + DIFFICULTY + getDifficultyValue(difficulty) + "&" +
           MULTIPLE_CHOICE_QUERY;
  }

  private String getCategoryValue(String category) {
    if (category.compareTo(Lobby.GENERALTEXT) == 0) {
      return "9";
    } else if (category.compareTo(Lobby.GEOGRAPHYTEXT) == 0) {
      return "22";
    } else if (category.compareTo(Lobby.MUSICTEXT) == 0) {
      return "12";
    } else if (category.compareTo(Lobby.VIDEOGAMESTEXT) == 0) {
      return "15";
    } else if (category.compareTo(Lobby.SCIENCENATURETEXT) == 0) {
      return "17";
    } else {
      return "23";
    }
  }

  private String getDifficultyValue(String difficulty) {
    if (difficulty.compareTo(Lobby.EASYTEXT) == 0) {
      return "easy";
    } else if (difficulty.compareTo(Lobby.MEDIUMTEXT) == 0) {
      return "medium";
    } else {
      return "hard";
    }
  }

  private String getAmountValue(String amount) {
    if (amount.compareTo("Short (5)") == 0) {
      return "5";
    } else if (amount.compareTo("Medium (7)") == 0) {
      return "7";
    } else {
      return "9";
    }
  }
}