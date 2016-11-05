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
 * The lobby screen will be where users set their preferences for a game prior to searching for
 * one.
 */
public class Lobby extends BaseScreen {
  private final BitmapFont lobbyScreenText;
  private final BitmapFont playText;
  private final ButtonActor backButton;
  private final ButtonActor playButton;
  private final Texture lobbyMockUp;

  /**
   * Refer to MainMenu.java for comments regarding each section. Lobby should operate in the same
   * way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Lobby(FilePlayMain mainGame) {
    super(mainGame);
    // Creates a button using the given texture at location (20, 650) of the native resolution 480
    // by 800.
    backButton = new ButtonActor(new Texture(Gdx.files.internal("back_button.png")), 20, 650);
    // Creates a button using the given texture at location (320, 650) of the native resolution 480
    // by 800.
    playButton = new ButtonActor(new Texture(Gdx.files.internal("next_button.png")), 320, 650);
    lobbyScreenText = new BitmapFont();
    lobbyScreenText.setColor(Color.YELLOW);
    playText = new BitmapFont();
    playText.setColor(Color.YELLOW);
    lobbyMockUp = new Texture(Gdx.files.internal("lobby_mockup.png"));
    addAllListeners();
    addAllActors();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draws the text "Lobby Screen (To be implemented)" at the location (20, 750) of the native
    // resolution 480 by 800.
    lobbyScreenText.draw(spriteBatch, "Lobby Screen (To be implemented)", 20, 750);
    // Draws the text "Play" at the location (400, 750) of the native resolution 480 by 800.
    playText.draw(spriteBatch, "Play", 400, 750);
    // Draws a sprite using the given texture at a location of (65, 300) of the native resolution.
    spriteBatch.draw(new Sprite(lobbyMockUp), 65, 300);
    spriteBatch.end();
  }

  /**
   * This adds listeners to each of the buttons along with what to do upon being touched. A new
   * InputListener is added inline overriding the touchDown method to determine what to do when
   * touched.
   */
  @Override
  protected void addAllListeners() {
    playButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change to play screen.
        mainGame.setScreen(FilePlayMain.ScreenType.PLAY);
        return true;
      }
    });
    backButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change back to main menu.
        mainGame.setScreen(FilePlayMain.ScreenType.MAINMENU);
        return true;
      }
    });
  }

  /**
   * Adds all of the buttons to the stage so that they are drawn to the screen.
   */
  @Override
  protected void addAllActors() {
    stage.addActor(playButton);
    stage.addActor(backButton);
  }

  @Override
  public void createButtons() {

  }
}
