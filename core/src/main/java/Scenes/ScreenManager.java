package Scenes;

import com.badlogic.gdx.Screen;

/*
    ScreenManager switches the current scene depending on which state the game is in, i.e pause, main menu, and play mode.
 */

public class ScreenManager {
  private Screen currentScreen;

  public enum Screens {
    MAINMENU, SETTINGS, PLAY, LOBBY, LEADERBOARDS, TEST;
  }

  public ScreenManager() {
    setState(Screens.MAINMENU);
  }

  public ScreenManager(Screens screen) {
    setState(screen);
  }

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
        currentScreen = new Settings();
        break;
      case PLAY:
        currentScreen = new Play(this);
        break;
      case LOBBY:
        currentScreen = new Lobby();
        break;
      case LEADERBOARDS:
        currentScreen = new Leaderboards();
        break;
      case TEST:
        currentScreen = new Test();
        break;
      default:
        currentScreen = new MainMenu(this);
        break;
    }
  }

  public void render(float delta) {
    currentScreen.render(delta);
  }

  public void dispose() {
    currentScreen.dispose();
  }

}
