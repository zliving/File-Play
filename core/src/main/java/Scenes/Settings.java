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
 * The settings screen is where users will be able to access their profiles and set preferences
 * regarding their gameplay.
 */
public class Settings extends BaseScreen {
  private final ButtonActor backButton;
  private final BitmapFont settingsText;
  private final Texture settingsMockUp;
  private final Texture banner;

  /**
   * Refer to MainMenu.java for comments regarding each section. Settings should operate in the same
   * way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Settings(FilePlayMain mainGame) {
    super(mainGame);
    // Creates a button using the given texture at (20, 650) of the native resolution 480
    // by 800.
    backButton = new ButtonActor(new Texture(Gdx.files.internal("black-back-arrow.png")), 400, 735);
    settingsText = new BitmapFont();
    settingsText.setColor(Color.YELLOW);
    settingsMockUp = new Texture(Gdx.files.internal("settings_mockup.png"));
    banner = new Texture(Gdx.files.internal("banner - HSYB-Long.png"));
    addAllListeners();
    addAllActors();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draws the text "Settings (To be implemented)" at the location (20, 750) of the native 480
    // by 800 resolution.
    settingsText.draw(spriteBatch, "Settings (To be implemented)", 20, 750);
    // Draws a sprite using the given texture at the location (65, 300) of the native 480 by 800
    // resolution.
    spriteBatch.draw(new Sprite(settingsMockUp), 65, 300);
    spriteBatch.draw(new Sprite(banner), 0, 720);
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
    stage.addActor(backButton);
  }
}
