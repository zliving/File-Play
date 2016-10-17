package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/*
    ScreenManager switches the current scene depending on which state the game is in, i.e pause, main menu, and play mode.
 */

public class ScreenManager {
  private Screen currentScreen;

  // Enumeration of program states used to change the state of the screen manager.
  public enum Screens {
    MAINMENU, SETTINGS, PLAY, LOBBY, LEADERBOARDS, TEST;
  }

  public ScreenManager() {
    setState(Screens.MAINMENU);
  }

  public ScreenManager(Screens screen) {
    setState(screen);
  }

  /**
   * Changes 'this.currentScreen' to a new instance of the screen corresponding to 'state'.
   * @param state
   */
  public void setState(Screens state) {
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
        currentScreen = new Test();
        break;
      default:
        currentScreen = new MainMenu(this);
        break;
    }
    // Need to resize when switching between different screens.
    currentScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  }

  public void render(float delta) {
    currentScreen.render(delta);
  }

  public void dispose() {
    currentScreen.dispose();
  }

}
