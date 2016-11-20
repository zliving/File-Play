package GameObjects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chris on 11/20/2016.
 */
public class GameSettingsTest {
  GameSettings settings;

  @Test
  /**
   * Test general category.
   */
  public void getCategoryValue_general(){
    settings = new GameSettings("7", "General", "medium");
    assertEquals(settings.getCategoryValue(), "9");
  }

  @Test
  /**
   * Test the amount value.
   */
  public void getAmountValue_9(){
    settings = new GameSettings("9", "General", "medium");
    assertEquals(settings.getAmountValue(), "9");
  }

  @Test
  /**
   * Test the hard difficulty.
   */
  public void getDifficultyValue_hard(){
    settings = new GameSettings("7", "General", "hard");
    assertEquals(settings.getDifficultyValue(), "hard");
  }
}
