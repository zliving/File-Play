package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import Scenes.GameStateManager;

public class FilePlayMain extends ApplicationAdapter {
	private GameStateManager gsm;

	/*
		Create class is where you create objects that are needed to be used in the game
	 */

	@Override
	public void create () {
        // Create new state manager and start off at test screen
        gsm = new GameStateManager(GameStateManager.Screens.TEST);
	}

	/*
		Render class draws objects created in the create class to the screen.
		It is refreshed at the clock rate of the phones screen
	 */

	@Override
	public void render () {
        // Render gsm's current screen with the change in time from last call
        gsm.render(Gdx.graphics.getDeltaTime());
	}

	/*
		!! IMPORTANT !!
		Dispose class removes items from the game and memory as need.
		When an object is created it must be destroyed.
	 */
	
	@Override
	public void dispose () {
        // Dispose of gsm's current screen
        gsm.dispose();
	}
}
