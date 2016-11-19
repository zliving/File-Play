package com.mygdx.game;

import com.badlogic.gdx.Game;

import Scenes.MainMenu;

/**
 * FilePlayMain is the starting point of the application and initializes the screenManager to be at
 * the MainMenu screen.
 */
public class FilePlayMain extends Game {
  @Override
  public void create() {
    this.setScreen(new MainMenu(this));
  }

  /**
   * Releases resources from the game and the current screen.
   */
  @Override
  public void dispose() {
    // Dispose of the current screen.
    getScreen().dispose();
  }
}
