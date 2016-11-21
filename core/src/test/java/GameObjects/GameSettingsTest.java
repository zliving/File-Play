package GameObjects;

import org.junit.Test;

import Scenes.Lobby;

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
    settings = new GameSettings(Lobby.MEDIUM_TEXT, Lobby.GENERAL_TEXT, Lobby.MEDIUM_TEXT);
    assertEquals(settings.getCategoryValue(), "9");
  }

  @Test
  /**
   * Test the amount value.
   */
  public void getAmountValue_9(){
    settings = new GameSettings(Lobby.LONG_TEXT, Lobby.GENERAL_TEXT, Lobby.MEDIUM_TEXT);
    System.out.println(settings.getAmountValue());
    assertEquals(settings.getAmountValue(), "9");
  }

  @Test
  /**
   * Test the hard difficulty.
   */
  public void getDifficultyValue_hard(){
    settings = new GameSettings(Lobby.MEDIUM_TEXT, Lobby.GENERAL_TEXT, Lobby.HARD_TEXT);
    assertEquals(settings.getDifficultyValue(), "hard");
  }

  @Test
  /**
   * Test unexpected category value.
   */
  public void getCategoryValue_unexpectedCategory(){
    settings = new GameSettings(Lobby.SHORT_TEXT, "asdf", Lobby.EASY_TEXT);
    // Default to general category.
    assertEquals(settings.getCategoryValue(), "9");
  }

  @Test
  /**
   * Test unexpected amount value.
   */
  public void getCategoryValue_unexpectedAmount(){
    settings = new GameSettings("Hello", Lobby.GENERAL_TEXT, Lobby.EASY_TEXT);
    // Default to 7 questions.
    assertEquals(settings.getAmountValue(), "7");
  }


}
