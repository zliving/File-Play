package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.FilePlayMain;

import UIElements.ButtonActor;
import UIElements.TriviaButtonBuilder;

/**
 * The play screen will be where two users answer trivia questions retrieved from the database and
 * where the actual gameplay will occur.
 */
public class Play extends BaseScreen {
  private ButtonActor backButton;
  private final int glyphCenterX;
  private TriviaButtonBuilder newTriviaGame;

  /**
   * Refer to MainMenu.java for comments regarding each section. Play should operate in the same way
   * with changes to the textures/sprites that must be drawn to the screen.
   */
  public Play(FilePlayMain mainGame) {
    super(mainGame);
    // Creates GlyphLayout to get width of the BitmapFont in order to center the text in the banner.
    bannerTextGlyphLayout = new GlyphLayout(bannerText, "Play");
    // Calculate the center in terms of x for the glyph.
    glyphCenterX = ((int) WORLD_WIDTH - (int) bannerTextGlyphLayout.width) / 2;
    newTriviaGame = new TriviaButtonBuilder();
    createButtons();
    addAllListeners();
    addAllActors();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    stage.act(delta);
    stage.draw();
    spriteBatch.begin();
    // Draws the text "Play" in the center of the banner.
    bannerText.draw(spriteBatch, bannerTextGlyphLayout, glyphCenterX, 770);
    // Draws a sprite using the playMockUp texture  located at (65, 300) of the native
    // resolution 480 by 800.
    //spriteBatch.draw(new Sprite(playMockUp), 65, 300);
    newTriviaGame.render();
    spriteBatch.end();
  }

  /**
   * Creates all of the  buttons that will be drawn to the screen.
   */
  @Override
  public void createButtons() {
    // Creates back button at the given location to go back a screen.
    backButton = new ButtonActor(new Texture(Gdx.files.internal("black-back-arrow.png")), 0, 735);
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
        mainGame.setScreen(new Lobby(mainGame));
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
}
