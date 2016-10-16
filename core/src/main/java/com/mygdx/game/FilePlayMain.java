package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;

import Scenes.ScreenManager;

/**
 * FilePlayMain is the starting point of the application and initializes the screenManager to be at
 * the MainMenu screen.
 */
public class FilePlayMain extends ApplicationAdapter {
  private ScreenManager screenManager;

  @Override
  public void create() {
    screenManager = new ScreenManager(ScreenManager.Screens.MAINMENU);
  }

  /**
   * Render class draws objects created in the create class to the screen.
   * It is refreshed at the clock rate of the phones screen
   */
  @Override
  public void render() {
    // Render gsm's current screen with the change in time from last call
    screenManager.render(Gdx.graphics.getDeltaTime());
  }

  /**
   * Dispose removes items from the game and memory as needed.
   */
  @Override
  public void dispose() {
    // Dispose of gsm's current screen
    screenManager.dispose();
  }
}
