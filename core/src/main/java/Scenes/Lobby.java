package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FilePlayMain;

import UIElements.Button;

/**
 * The lobby screen will be where users set their preferences for a game prior to searching for
 * one.
 */
public class Lobby extends BaseScreen {
  private final BitmapFont lobbyScreenText;
  private final BitmapFont playText;
  private final Button backButton;
  private final Button playButton;
  private final Texture lobbyMockUp;

  /**
   * Refer to MainMenu.java for comments regarding each section. Lobby should operate in the same
   * way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Lobby(FilePlayMain mainGame) {
    super(mainGame);

    // Creates a button using the given texture at location (20, 650) of the native resolution 480
    // by 800.
    backButton = new Button(new Texture(Gdx.files.internal("back_button.png")), 20, 650);

    // Creates a button using the given texture at location (320, 650) of the native resolution 480
    // by 800.
    playButton = new Button(new Texture(Gdx.files.internal("next_button.png")), 320, 650);
    lobbyScreenText = new BitmapFont();
    lobbyScreenText.setColor(Color.YELLOW);
    playText = new BitmapFont();
    playText.setColor(Color.YELLOW);
    lobbyMockUp = new Texture(Gdx.files.internal("lobby_mockup.png"));
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    spriteBatch.draw(backButton.getSprite(), backButton.getX(), backButton.getY());
    // The play button will need to be added in the next iteration.
    spriteBatch.draw(playButton.getSprite(), playButton.getX(), playButton.getY());
    // Draws the text "Lobby Screen (To be implemented)" at the location (20, 750) of the native
    // resolution 480 by 800.
    lobbyScreenText.draw(spriteBatch, "Lobby Screen (To be implemented)", 20, 750);
    // Draws the text "Play" at the location (400, 750) of the native resolution 480 by 800.
    playText.draw(spriteBatch, "Play", 400, 750);
    // Draws a sprite using the given texture at a location of (65, 300) of the native resolution.
    spriteBatch.draw(new Sprite(lobbyMockUp), 65, 300);
    spriteBatch.end();
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    float worldX = x * WidthWorldPixelRatio;
    float correctedY = Gdx.graphics.getHeight() - y;
    float worldY = correctedY * HeightWorldPixelRatio;
    if (backButton.isClicked(worldX, worldY)) {
      System.out.println("Go to mainmenu");
      mainGame.setScreen(FilePlayMain.ScreenType.MAINMENU);
    } else if (playButton.isClicked(worldX, worldY)) {
      System.out.println("Go to play screen");
      mainGame.setScreen(FilePlayMain.ScreenType.PLAY);
    }
    return false;
  }
}
