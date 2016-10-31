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
 * The settings screen is where users will be able to access their profiles and set preferences
 * regarding their gameplay.
 */
public class Settings extends BaseScreen {
  private final Button backButton;
  private final BitmapFont settingsText;
  private final Texture settingsMockUp;
  private final Texture banner;

  /**
   * Refer to MainMenu.java for comments regarding each section. Settings should operate in the
   * same way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Settings(FilePlayMain mainGame) {
    super(mainGame);

    // Creates a button using the given texture at (400, 735) of the native resolution 480
    // by 800.
    backButton = new Button(new Texture(Gdx.files.internal("black-back-arrow.png")), 400, 735);
    settingsText = new BitmapFont();
    settingsText.setColor(Color.YELLOW);
    settingsMockUp = new Texture(Gdx.files.internal("settings_mockup.png"));
    banner = new Texture(Gdx.files.internal("banner - HSYB-Long.png"));
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draws the text "Settings (To be implemented)" at the location (20, 750) of the native 480
    // by 800 resolution.
    settingsText.draw(spriteBatch, "Settings (To be implemented)", 20, 750);
    spriteBatch.draw(backButton.getSprite(), backButton.getX(), backButton.getY());
    // Draws a sprite using the given texture at the location (65, 300) of the native 480 by 800
    // resolution.
    spriteBatch.draw(new Sprite(settingsMockUp), 65, 300);
    spriteBatch.draw(new Sprite(banner), 0, 720);
    spriteBatch.end();
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    float worldX = x * WidthWorldPixelRatio;
    float correctedY = Gdx.graphics.getHeight() - y;
    float worldY = correctedY * HeightWorldPixelRatio;
    if (backButton.isClicked(worldX, worldY)) {
      System.out.println("Go back to main menu");
      mainGame.setScreen(FilePlayMain.ScreenType.MAINMENU);
    }
    return false;
  }
}
