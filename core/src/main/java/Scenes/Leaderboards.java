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
 * TODO (Chris): Refactor constants into Game class.
 * TODO (Chris): Include private helper methods to help with readability.
 * The leaderboards screen will display a ranking of users and statistics based on their
 * gameplay. This class implements the LibGDX screen interface in order to render the contents to
 * a user's phone screen. User touch input is handled by implementing the GestureListener interface
 * provided by LibGDX.
 */
class Leaderboards extends BaseScreen {
  private final Button backButton;
  private final BitmapFont leaderboardText;
  private final Texture leaderboardsMockUp;

  /**
   * Refer to MainMenu.java for comments regarding each section. Leaderboards should operate in
   * the same way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Leaderboards(ScreenManager screenManager) {
    super(screenManager);

    // Create a new button using the "back_button.png" located at (20, 650) of the native
    // resolution  of 480 by 800.
    backButton = new Button(new Texture(Gdx.files.internal("back_button.png")), 20, 650);
    leaderboardText = new BitmapFont();
    leaderboardText.setColor(Color.YELLOW);
    leaderboardsMockUp = new Texture(Gdx.files.internal("leaderboards_mockup.png"));
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    spriteBatch.draw(backButton.getSprite(), backButton.getX(), backButton.getY());
    // Draws the text "Leaderboards (To be implemented)"  located at (20, 750) of the native
    // resolution 480 by 800.
    leaderboardText.draw(spriteBatch, "Leaderboards (To be implemented)", 20, 750);
    // Draws a sprite using the leaderboardsMockUp texture located at (65, 3000) of the native
    // resolution 480 by 800.
    spriteBatch.draw(new Sprite(leaderboardsMockUp), 65, 300);
    spriteBatch.end();
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    HeightWorldPixelRatio = WORLD_HEIGHT / (float) Gdx.graphics.getHeight();
    WidthWorldPixelRatio = WORLD_WIDTH / (float) Gdx.graphics.getWidth();
    float worldX = x * WidthWorldPixelRatio;
    float correctedY = Gdx.graphics.getHeight() - y;
    float worldY = correctedY * HeightWorldPixelRatio;
    if (backButton.isClicked(worldX, worldY)) {
      System.out.println("Go back to main menu");
      screenManager.setState(ScreenManager.ScreenType.MAINMENU);
    }
    return false;
  }
}
