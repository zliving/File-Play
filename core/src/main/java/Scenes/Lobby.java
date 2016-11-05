package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.FilePlayMain;

import UIElements.ButtonActor;

/**
 * The lobby screen will be where users set their preferences for a game prior to searching for
 * one.
 */
public class Lobby extends BaseScreen {
  private BitmapFont lobbyText;
  private GlyphLayout lobbyGlyphLayout;
  private ButtonActor backButton;
  private TextButton playButton;
  private final Texture banner;
  private final Texture lobbyMockUp;
  private final int glyphCenterX;

  /**
   * Refer to MainMenu.java for comments regarding each section. Lobby should operate in the same
   * way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Lobby(FilePlayMain mainGame) {
    super(mainGame);
    lobbyText = new BitmapFont();
    lobbyText = generateNewFont("VacationPostcardNF.ttf", 36, Color.BLACK);
    // Creates Glyph to get width of the BitmapFont in order to center.
    lobbyGlyphLayout = new GlyphLayout(lobbyText, "Lobby");
    // Calculate the center in terms of x for the glyph.
    glyphCenterX = ((int) WORLD_WIDTH - (int) lobbyGlyphLayout.width) / 2;
    lobbyMockUp = new Texture(Gdx.files.internal("lobby_mockup.png"));
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
    // Draws the text "Lobby" in the center of the banner.
    lobbyText.draw(spriteBatch, lobbyGlyphLayout, glyphCenterX, 770);
    // Draws the text "Play" at the location (400, 750) of the native resolution 480 by 800.
    // Draws a sprite using the given texture at a location of (65, 300) of the native resolution.
    spriteBatch.draw(new Sprite(lobbyMockUp), 65, 300);
    // Draws the banner

    spriteBatch.end();
  }

  /**
   * Creates all of the  buttons that will be drawn to the screen.
   */
  @Override
  protected void createButtons() {
    // Creates a button using the given texture at location (20, 650) of the native resolution 480
    // by 800.
    backButton = new ButtonActor(new Texture(Gdx.files.internal("black-back-arrow.png")), 400, 735);
    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
    // Sets the skin for when the button is not pressed and when it is. The argument that is passed
    // is taken from the atlas used for the buttonSkin object.
    style.up = buttonSkin.getDrawable("heavy-sat-yellow-246x46");
    style.down = buttonSkin.getDrawable("heavy-sat-yellow-246x46");
    style.font = generateNewFont("Rampung.ttf", 30, Color.BLACK);
    // Creates the play button at the specificed location with the given style
    playButton = new TextButton("Play", style);
    playButton.setPosition(220, 20);
  }

  /**
   * This adds listeners to each of the buttons along with what to do upon being touched. A new
   * InputListener is added inline overriding the touchDown method to determine what to do when
   * touched.
   */
  @Override
  protected void addAllListeners() {
    backButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change back to main menu.
        mainGame.setScreen(FilePlayMain.ScreenType.MAINMENU);
        return true;
      }
    });
    playButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change to Play screen.
        mainGame.setScreen(FilePlayMain.ScreenType.PLAY);
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
    stage.addActor(playButton);
  }
}
