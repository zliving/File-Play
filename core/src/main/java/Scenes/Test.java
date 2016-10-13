package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import GameObjects.BuildTriviaQuestions;


public class Test implements Screen {
    SpriteBatch batch;
    private BitmapFont font;
    private GlyphLayout fontLayout;
    private BuildTriviaQuestions newTriviaRound;

    public Test() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        fontLayout = new GlyphLayout();
        String url = "http://www.opentdb.com/api.php?amount=2";
        newTriviaRound = new BuildTriviaQuestions(url);
        newTriviaRound.getTriviaQuestions();
        font.setColor(Color.TEAL);
        font.getData().setScale(2);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Get the width and height of the device screen.
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        // Calculate where the center of the screen is based on the size of the fontLayout GlyphLayout.
        float middleScreenWidth = (width / 2) - (fontLayout.width / 2);
        float middleScreenHeight = height / 2;

        // Set the background to black and clear the color buffer bit.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin batch and add items for it to draw.
        batch.begin();
        fontLayout.setText(font, "File Play");
        font.draw(batch, fontLayout, middleScreenWidth,middleScreenHeight);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
