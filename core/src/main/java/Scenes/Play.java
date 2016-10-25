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

import UIElements.Button;

/**
 * The play screen will be where two users answer trivia questions retrieved from the database
 * and where the actual gameplay will occur.
 */
class Play extends BaseScreen {
  private final BitmapFont playScreenText;
  private final Texture playMockUp;
  private final Button backButton;

  /**
   * Refer to MainMenu.java for comments regarding each section. Play should operate in the
   * same way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Play(ScreenManager screenManager) {
    super(screenManager);

    // Creates a button with the given texture at a location of (20, 650) of the native screen
    // resolution 480 by 800.
    backButton = new Button(new Texture(Gdx.files.internal("back_button.png")), 20, 650);
    playScreenText = new BitmapFont();
    playScreenText.setColor(Color.YELLOW);
    playMockUp = new Texture(Gdx.files.internal("play_mockup.png"));
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    spriteBatch.draw(backButton.getSprite(), backButton.getX(), backButton.getY());
    // Draws the text "Play Screen(To be implemented" located at (20, 750) of the native
    // resolution 480 by 800.
    playScreenText.draw(spriteBatch, "Play Screen (To be implemented)", 20, 750);
    // Draws a sprite using the playMockUp texture  located at (65, 300) of the native
    // resolution 480 by 800.
    spriteBatch.draw(new Sprite(playMockUp), 65, 300);
    spriteBatch.end();
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    float worldX = x * WidthWorldPixelRatio;
    float correctedY = Gdx.graphics.getHeight() - y;
    float worldY = correctedY * HeightWorldPixelRatio;
    if (backButton.isClicked(worldX, worldY)) {
      System.out.println("Go back to lobby");
      screenManager.setState(ScreenManager.ScreenType.LOBBY);
    }
    return false;
  }
}
