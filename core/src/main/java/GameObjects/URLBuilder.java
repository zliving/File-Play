package GameObjects;

/**
 * The URLBuilder class is a utility class used to build the URL for OpenDB required to retrieve
 * questions given preferences regarding number of questions, category, and difficulty.
 */
public final class URLBuilder {
  public enum CategoryType {
    GENERAL(9),
    BOOKS(10),
    FILM(11),
    MUSIC(12),
    MUSICALTHEATRES(13),
    TELEVISION(14),
    VIDEOGAMES(15),
    BOARDGAMES(16),
    SCIENCENATURE(17),
    COMPUTERS(18),
    MATHEMATICS(19),
    MYTHOLOGY(20),
    SPORTS(21),
    GEOGRAPHY(22),
    HISTORY(23),
    POLITICS(24),
    ART(25),
    CELEBRITIES(26),
    ANIMALS(27);

    private int val;

    CategoryType(int val) {
      this.val = val;
    }

    public String getVal() {
      String returnStirng = "&category=";
      return returnStirng + Integer.toString(val);
    }
  }

  public enum DifficultyType {
    EASY(0),
    MEDIUM(1),
    HARD(2);

    private int val;

    DifficultyType(int val) {
      this.val = val;
    }

    public String getVal() {
      String returnString = "&difficulty=";
      switch (this.val) {
        case 0:
          returnString = returnString + "easy";
          break;
        case 1:
          returnString = returnString + "medium";
          break;
        case 2:
          returnString = returnString + "hard";
          break;
        default:
          returnString = returnString + "easy";
          break;
      }
      return returnString;
    }
  }

  public enum AmountType {
    SHORT(5),
    MEDIUM(7),
    LONG(9);

    private int val;

    AmountType(int val) {
      this.val = val;
    }

    public String getVal() {
      String returnString = "amount=";
      return returnString + Integer.toString(val);
    }
  }

  /**
   * Amount Category Difficulty = easy, medium, hard Type = multiple Each separated by &
   */
  private URLBuilder() {
    // Assert that this constructor should never be called.
    throw new AssertionError();
  }

  public static final String buildURL(AmountType numberOfQuestions, CategoryType category,
                                DifficultyType difficulty) {
    String base = "https://www.opentdb.com/api.php?";
    String multipleChoice = "&type=multiple";
    return base + numberOfQuestions.getVal() + category.getVal() + difficulty.getVal()
            + multipleChoice;
  }
}
