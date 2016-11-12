package GameObjects;

/**
 * The UrlBuilder class is a utility class used to build the URL for OpenDB required to retrieve
 * questions given preferences regarding number of questions, category, and difficulty.
 */
public final class UrlBuilderImp implements UrlBuilder {
  private static final String BASE = "https://www.opentdb.com/api.php";
  private static final String DIFFICULTY = "difficulty=";
  private static final String CATEGORY= "category=";
  private static final String AMOUNT = "amount=";
  private static final String MULTIPLE_CHOICE_QUERY = "type=multiple";

  public UrlBuilderImp() {
  }

  @Override
  public String buildUrl(AmountType amount, CategoryType category, DifficultyType difficulty) {
    return BASE + "?" + AMOUNT + amount.getVal() + "&" + CATEGORY + category.getVal() + "&" +
            DIFFICULTY + difficulty.getVal() + "&" + MULTIPLE_CHOICE_QUERY;
  }
}