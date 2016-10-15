package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;

import Scenes.ScreenManager;


public class FilePlayMain extends ApplicationAdapter {
  private ScreenManager screenManager;

  @Override
  public void create() {
    // Create new state manager and start off at test screen
    screenManager = new ScreenManager(ScreenManager.Screens.TEST);
  }

	/*
		Render class draws objects created in the create class to the screen.
		It is refreshed at the clock rate of the phones screen
	 */

  @Override
  public void render() {
    // Render gsm's current screen with the change in time from last call
    screenManager.render(Gdx.graphics.getDeltaTime());
  }

	/*
		!! IMPORTANT !!
		Dispose class removes items from the game and memory as need.
		When an object is created it must be destroyed.
	 */

  @Override
  public void dispose() {
    // Dispose of gsm's current screen
    screenManager.dispose();
  }
}
