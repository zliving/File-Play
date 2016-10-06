package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FilePlayMain extends ApplicationAdapter {
	SpriteBatch batch;
	private BitmapFont font;
	private GlyphLayout fontLayout;

	/*
		Create class is where you create objects that are needed to be used in the game
	 */

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		fontLayout = new GlyphLayout();
		font.setColor(Color.TEAL);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		font.getData().setScale(2);

	}

	/*
		Render class draws objects created in the create class to the screen.
		It is refreshed at the clock rate of the phones screen
	 */

	@Override
	public void render () {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float middleScreenWidth = (width/2)-(fontLayout.width/2);
		float middleScreenHeight = height/2;

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		fontLayout.setText(font,"File Play");

		font.draw(batch, fontLayout, middleScreenWidth,middleScreenHeight);

		batch.end();

	}

	/*
		!! IMPORTANT !!
		Dispose class removes items from the game and memory as need.
		When an object is created it must be destroyed.
	 */
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
