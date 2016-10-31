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
      return Integer.toString(val);
    }
  }

  /**
   * Amount Category Difficulty = easy, medium, hard Type = multiple Each separated by &
   */
  public URLBuilder() {
    // Assert that this constructor should never be called.
    throw new AssertionError();
  }

  public static String buildURL(int numberOfQuestions, CategoryType category, int difficulty) {
  }
}
