package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import Scenes.BaseScreen;
import Scenes.Leaderboards;
import Scenes.Lobby;
import Scenes.MainMenu;
import Scenes.Play;
import Scenes.Settings;
import Scenes.Test;

/**
 * FilePlayMain is the starting point of the application and initializes the screenManager to be at
 * the MainMenu screen.
 */
public class FilePlayMain extends ApplicationAdapter {
  private BaseScreen currentScreen;

  // Enumeration of program states used to change the state of the screen manager.
  public enum ScreenType {
    MAINMENU,
    SETTINGS,
    PLAY,
    LOBBY,
    LEADERBOARDS,
    TEST
  }

  @Override
  public void create() {
    this.setScreen(ScreenType.MAINMENU);
  }

  /**
   * Render class draws objects created in the create class to the screen.
   * It is refreshed at the clock rate of the phones screen
   */
  @Override
  public void render() {
    // Render gsm's current screen with the change in time from last call
    currentScreen.render(Gdx.graphics.getDeltaTime());
  }

  /**
   * Dispose removes items from the game and memory as needed.
   */
  @Override
  public void dispose() {
    // Dispose of gsm's current screen
    currentScreen.dispose();
  }

  /**
   * Changes 'this.currentScreen' to a new instance of the screen corresponding to 'state'.
   *
   * @param state
   */
  public void setScreen(ScreenType state) {
    // Dispose of the current screen and switch to the new one.
    if (currentScreen != null) {
      currentScreen.dispose();
    }
    // Switch to the appropriate screen.
    switch (state) {
      case MAINMENU:
        currentScreen = new MainMenu(this);
        break;
      case SETTINGS:
        currentScreen = new Settings(this);
        break;
      case PLAY:
        currentScreen = new Play(this);
        break;
      case LOBBY:
        currentScreen = new Lobby(this);
        break;
      case LEADERBOARDS:
        currentScreen = new Leaderboards(this);
        break;
      case TEST:
        currentScreen = new Test(this);
        break;
      default:
        currentScreen = new MainMenu(this);
        break;
    }
    // Need to resize when switching between different screens.
    currentScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }
}
