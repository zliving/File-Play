package GameObjects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Tests for the UrlBuilder. The URLs provided were taken directly from the database using their
 * API.
 */
@RunWith(MockitoJUnitRunner.class)
public class UrlBuilderImpTest {
  @Mock
  GameSettings settingsMock;

  @InjectMocks
  UrlBuilderImp urlBuilder;

  @Test
  /**
   * Test when general category is used with 9 questions and easy difficulty.
   */
  public void longGeneralEasy(){
    when(settingsMock.getCategoryValue()).thenReturn("9");
    when(settingsMock.getAmountValue()).thenReturn("9");
    when(settingsMock.getDifficultyValue()).thenReturn("easy");
    assertEquals(urlBuilder.buildUrl(),
            "https://www.opentdb.com/api.php?amount=9&category=9&difficulty=easy&type=multiple");
  }

  @Test
  /**
   * Test for geography category, short game, normal difficulty.
   */
  public void shortGeographyMedium(){
    when(settingsMock.getCategoryValue()).thenReturn("22");
    when(settingsMock.getAmountValue()).thenReturn("5");
    when(settingsMock.getDifficultyValue()).thenReturn("medium");
    assertEquals(urlBuilder.buildUrl(),
            "https://www.opentdb.com/api.php?amount=5&category=22&difficulty=medium&type=multiple");
  }

  @Test
  /**
   * Test for music category, medium game, hard difficulty.
   */
  public void mediumMusicHard(){
    when(settingsMock.getCategoryValue()).thenReturn("12");
    when(settingsMock.getAmountValue()).thenReturn("7");
    when(settingsMock.getDifficultyValue()).thenReturn("hard");
    assertEquals(urlBuilder.buildUrl(),
            "https://www.opentdb.com/api.php?amount=7&category=12&difficulty=hard&type=multiple");
  }

  @Test
  /**
   * Test for video games category, short game, easy difficulty.
   */
  public void shortVideoGamesEasy(){
    when(settingsMock.getCategoryValue()).thenReturn("15");
    when(settingsMock.getAmountValue()).thenReturn("5");
    when(settingsMock.getDifficultyValue()).thenReturn("easy");
    assertEquals(urlBuilder.buildUrl(),
            "https://www.opentdb.com/api.php?amount=5&category=15&difficulty=easy&type=multiple");
  }

  @Test
  /**
   * Test for science and nature category, long game, normal difficulty.
   */
  public void longScienceNatureNormal(){
    when(settingsMock.getCategoryValue()).thenReturn("17");
    when(settingsMock.getAmountValue()).thenReturn("9");
    when(settingsMock.getDifficultyValue()).thenReturn("medium");
    assertEquals(urlBuilder.buildUrl(),
            "https://www.opentdb.com/api.php?amount=9&category=17&difficulty=medium&type=multiple");
  }

  @Test
  /**
   * Test for history category, medium game, hard difficulty.
   */
  public void mediumHistoryHard(){
    when(settingsMock.getCategoryValue()).thenReturn("23");
    when(settingsMock.getAmountValue()).thenReturn("7");
    when(settingsMock.getDifficultyValue()).thenReturn("hard");
    assertEquals(urlBuilder.buildUrl(),
            "https://www.opentdb.com/api.php?amount=7&category=23&difficulty=hard&type=multiple");
  }
}
