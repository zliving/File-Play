package Scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;




/**
 * Created by zach on 10/6/16.
 *
 * This class implements the main menu screen. So far all it does is draw the screen,  no input
 * handling yet. The buttons are play, options and leaderboards.
 */

public class MainMenu implements Screen {

  private SpriteBatch mainMenuSpriteBatch;
  private BitmapFont font;
  private GlyphLayout fontLayout;

  private Texture playButton;
  private Texture optionsButton;
  private Texture leaderBoardsButton;
  private Texture background;

  // These constants will be used to space buttons from one another.
  public static final int VERTICAL_SPACING = 90;
  public static final int HORIZONTAL_SPACING = 110;

  // The constructor defines the sprite batch, right now consisting of three buttons,
  // the background and the text at the very bottom.
  public MainMenu(){
    mainMenuSpriteBatch = new SpriteBatch();
    font = new BitmapFont();
    fontLayout = new GlyphLayout();
    playButton = new Texture("play_button.png");
    optionsButton = new Texture("options_button.png");
    leaderBoardsButton = new Texture("leaderboards_button.png");
    background = new Texture("background.png");

  }



  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {

    // Set the background to black and clear the color buffer bit.
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Get the width and height of the device screen.
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    // Calculate where the center of the screen is based on the size of the fontLayout GlyphLayout.
    float middleScreenWidth = width / 2;
    float middleScreenHeight = height / 2;

    mainMenuSpriteBatch.begin();
    // The background position and scaling numbers were chosen arbitrarily and different from the buttons
    mainMenuSpriteBatch.draw(background, middleScreenWidth-140, middleScreenHeight-250, 300, 600);
    // The buttons are aligned horizontally, and are apart the same amount of space vertically.
    mainMenuSpriteBatch.draw(playButton, middleScreenWidth-HORIZONTAL_SPACING, middleScreenHeight +
                              VERTICAL_SPACING);
    mainMenuSpriteBatch.draw(optionsButton, middleScreenWidth-HORIZONTAL_SPACING,
                              middleScreenHeight);
    mainMenuSpriteBatch.draw(leaderBoardsButton, middleScreenWidth-HORIZONTAL_SPACING,
                              middleScreenHeight - VERTICAL_SPACING);
    // This two lines draw a watermark at the bottom.
    font.draw(mainMenuSpriteBatch, "-by Rubber Duck", 20, 20);
    font.setColor(Color.YELLOW);

    mainMenuSpriteBatch.end();


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

  }
}