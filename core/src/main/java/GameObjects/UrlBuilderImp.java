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
  private static GameSettings settings;
  
  public UrlBuilderImp(GameSettings settings) {
    this.settings = settings;
  }

  @Override
  public String buildUrl() {
    return BASE + "?" + AMOUNT + settings.getAmountValue() + "&" + CATEGORY +
           settings.getCategoryValue() + "&" + DIFFICULTY + settings.getDifficultyValue() + "&" +
           MULTIPLE_CHOICE_QUERY;
  }
}