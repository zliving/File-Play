package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.FilePlayMain;

import UIElements.ButtonActor;

/**
 * The play screen will be where two users answer trivia questions retrieved from the database and
 * where the actual gameplay will occur.
 */
public class Play extends BaseScreen {
  private final BitmapFont playScreenText;
  private final Texture playMockUp;
  private final ButtonActor backButton;

  /**
   * Refer to MainMenu.java for comments regarding each section. Play should operate in the same way
   * with changes to the textures/sprites that must be drawn to the screen.
   */
  public Play(FilePlayMain mainGame) {
    super(mainGame);
    // Creates a button with the given texture at a location of (20, 650) of the native screen
    // resolution 480 by 800.
    backButton = new ButtonActor(new Texture(Gdx.files.internal("black-back-arrow.png")), 20, 650);
    playScreenText = new BitmapFont();
    playScreenText.setColor(Color.YELLOW);
    playMockUp = new Texture(Gdx.files.internal("play_mockup.png"));
    addAllListeners();
    addAllActors();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draws the text "Play Screen(To be implemented" located at (20, 750) of the native
    // resolution 480 by 800.
    playScreenText.draw(spriteBatch, "Play Screen (To be implemented)", 20, 750);
    // Draws a sprite using the playMockUp texture  located at (65, 300) of the native
    // resolution 480 by 800.
    spriteBatch.draw(new Sprite(playMockUp), 65, 300);
    spriteBatch.end();
  }

  /**
   * This adds listeners to each of the buttons along with what to do upon being touched. A new
   * InputListener is added inline overriding the touchDown method to determine what to do when
   * touched.
   */
  @Override
  protected void addAllListeners() {
    backButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change to lobby screen.
        mainGame.setScreen(FilePlayMain.ScreenType.LOBBY);
        return true;
      }
    });
  }

  /**
   * Adds all of the buttons to the stage so that they are drawn to the screen.
   */
  @Override
  protected void addAllActors() {
    stage.addActor(backButton);
  }

  @Override
  public void createButtons() {

  }
}
