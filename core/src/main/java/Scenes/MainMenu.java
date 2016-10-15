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
 */

public class MainMenu implements Screen {

  private SpriteBatch mainMenuSB;
  private BitmapFont font;
  private GlyphLayout fontLayout;

  private Texture playButton;
  private Texture optionsButton;
  private Texture leaderBoardsButton;
  private Texture background;

  //this macro definition will be used to space buttons from one another
  public final int VERTICAL_SPACING = 90;
  //this one does the same horizontally
  public final int HORIZONTAL_SPACING = 110;

  //constructor defines the sprite batch, the two buttons and the font
  public MainMenu(){
    mainMenuSB = new SpriteBatch();
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

    //code for drawing the background copied from test.java
    // Get the width and height of the device screen.
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    // Calculate where the center of the screen is based on the size of the fontLayout GlyphLayout.
    float middleScreenWidth = width / 2;
    float middleScreenHeight = height / 2;

    mainMenuSB.begin();
    mainMenuSB.draw(background, middleScreenWidth-140, middleScreenHeight-250, 300, 600);
    //hard  code the button's positions
    mainMenuSB.draw(playButton, middleScreenWidth-HORIZONTAL_SPACING, middleScreenHeight + VERTICAL_SPACING);
    mainMenuSB.draw(optionsButton, middleScreenWidth-HORIZONTAL_SPACING, middleScreenHeight);
    mainMenuSB.draw(leaderBoardsButton, middleScreenWidth-HORIZONTAL_SPACING, middleScreenHeight - VERTICAL_SPACING);
    font.draw(mainMenuSB, "-by Rubber Duck", 20, 20);
    font.setColor(Color.YELLOW);



    mainMenuSB.end();


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