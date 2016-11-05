package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
  private BitmapFont playText;
  private final Texture playMockUp;
  private ButtonActor backButton;
  private final Texture banner;
  private GlyphLayout playTextGlyphLayout;
  private final int glyphCenterX;

  /**
   * Refer to MainMenu.java for comments regarding each section. Play should operate in the same way
   * with changes to the textures/sprites that must be drawn to the screen.
   */
  public Play(FilePlayMain mainGame) {
    super(mainGame);
    playText = new BitmapFont();
    playText = generateNewFont("VacationPostcardNF.ttf", 36, Color.BLACK);
    // Creates Glyph to get width of the BitmapFont in order to center.
    playTextGlyphLayout = new GlyphLayout(playText, "Play");
    // Calculate the center in terms of x for the glyph.
    glyphCenterX = ((int) WORLD_WIDTH - (int) playTextGlyphLayout.width) / 2;
    playMockUp = new Texture(Gdx.files.internal("play_mockup.png"));
    banner = new Texture(Gdx.files.internal("banner - HSYB-Long.png"));
    createButtons();
    addAllListeners();
    addAllActors();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draws the banner
    spriteBatch.draw(new Sprite(banner), 0, 720);
    // Draws the text "Play" in the center of the banner.
    playText.draw(spriteBatch, playTextGlyphLayout, glyphCenterX, 770);
    // Draws a sprite using the playMockUp texture  located at (65, 300) of the native
    // resolution 480 by 800.
    spriteBatch.draw(new Sprite(playMockUp), 65, 300);
    spriteBatch.end();
  }

  /**
   * Creates all of the  buttons that will be drawn to the screen.
   */
  @Override
  public void createButtons() {
    // Creates a button with the given texture at a location of (20, 650) of the native screen
    // resolution 480 by 800.
    backButton = new ButtonActor(new Texture(Gdx.files.internal("black-back-arrow.png")), 400, 735);
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
}
