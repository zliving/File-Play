package GameObjects;

/**
 * Created by Chris on 11/12/2016.
 */

public interface UrlBuilderWrapper {
  enum CategoryType {
    GENERAL(9),
    FILM(11),
    MUSIC(12),
    VIDEOGAMES(15),
    BOARDGAMES(16),
    SCIENCENATURE(17),
    HISTORY(23);

    private int val;

    CategoryType(int val) {
      this.val = val;
    }

    public String getVal() {
      return Integer.toString(val);
    }
  }

  enum DifficultyType {
    EASY(0),
    MEDIUM(1),
    HARD(2);

    private int val;

    DifficultyType(int val) {
      this.val = val;
    }

    public String getVal() {
      switch (this.val) {
        case 0:
          return "easy";
        case 1:
          return "medium";
        case 2:
          return "hard";
        default:
          return "easy";
      }
    }
  }

  enum AmountType {
    SHORT(5),
    MEDIUM(7),
    LONG(9);

    private int val;

    AmountType(int val) {
      this.val = val;
    }

    public String getVal() {
      return Integer.toString(val);
    }
  }

  String buildUrl(AmountType amount, CategoryType category, DifficultyType difficulty);
}
