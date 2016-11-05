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
 * TODO (Chris): Include private helper methods to help with readability. The leaderboards screen
 * will display a ranking of users and statistics based on their gameplay. This class implements the
 * LibGDX screen interface in order to render the contents to a user's phone screen. User touch
 * input is handled by implementing the GestureListener interface provided by LibGDX.
 */
public class Leaderboards extends BaseScreen {
  private ButtonActor backButton;
  private final Texture leaderboardsMockUp;
  private final int glyphCenterX;


  /**
   * Refer to MainMenu.java for comments regarding each section. Leaderboards should operate in the
   * same way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Leaderboards(FilePlayMain mainGame) {
    super(mainGame);
    // Creates GlyphLayout with the given string to get width of the BitmapFont in order to
    // center the text.
    bannerTextGlyphLayout = new GlyphLayout(bannerText, "Leaderboards");
    // Calculate the center in terms of x for the GlyphLayout.
    glyphCenterX = ((int) WORLD_WIDTH - (int) bannerTextGlyphLayout.width) / 2;
    leaderboardsMockUp = new Texture(Gdx.files.internal("leaderboards_mockup.png"));
    createButtons();
    addAllListeners();
    addAllActors();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draws the banner as a texture located at the top.
    spriteBatch.draw(new Sprite(banner), 0, 720);
    // Draws the text "Leaderboards" in the center of the banner.
    bannerText.draw(spriteBatch, bannerTextGlyphLayout, glyphCenterX, 770);
    // Draws a sprite using the leaderboardsMockUp texture located at (65, 300) of the native
    // resolution 480 by 800.
    spriteBatch.draw(new Sprite(leaderboardsMockUp), 65, 300);
    spriteBatch.end();
  }

  /**
   * Creates all of the  buttons that will be drawn to the screen.
   */
  @Override
  protected void createButtons() {
    // Creates the back button on the banner to go back a screen.
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
