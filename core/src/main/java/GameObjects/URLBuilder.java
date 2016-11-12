package GameObjects;

/**
 * The UrlBuilder class is a utility class used to build the URL for OpenDB required to retrieve
 * questions given preferences regarding number of questions, category, and difficulty.
 */
public final class UrlBuilder implements UrlBuilderWrapper {
  private final String base = "https://www.opentdb.com/api.php";
  private final String difficultyBase = "&difficulty=";
  private final String categoryBase = "&category=";
  private final String amountBase = "?amount=";
  private final String multipleChoice = "&type=multiple";

  public UrlBuilder() {
  }

  @Override
  public String buildUrl(AmountType amount, CategoryType category, DifficultyType difficulty) {
    return base + amountBase + amount.getVal() + categoryBase + category.getVal() +
            difficultyBase + difficulty.getVal() + multipleChoice;
  }
}
