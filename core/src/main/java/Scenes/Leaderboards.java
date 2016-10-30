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
 * TODO (Chris): Include private helper methods to help with readability. The leaderboards screen
 * will display a ranking of users and statistics based on their gameplay. This class implements the
 * LibGDX screen interface in order to render the contents to a user's phone screen. User touch
 * input is handled by implementing the GestureListener interface provided by LibGDX.
 */
public class Leaderboards extends BaseScreen {
  private final ButtonActor backButton;
  private final BitmapFont leaderboardText;
  private final Texture leaderboardsMockUp;

  /**
   * Refer to MainMenu.java for comments regarding each section. Leaderboards should operate in the
   * same way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Leaderboards(FilePlayMain mainGame) {
    super(mainGame);
    // Create a new button using the "back_button.png" located at (20, 650) of the native
    // resolution  of 480 by 800.
    backButton = new ButtonActor(new Texture(Gdx.files.internal("back_button.png")), 20, 650);
    leaderboardText = new BitmapFont();
    leaderboardText.setColor(Color.YELLOW);
    leaderboardsMockUp = new Texture(Gdx.files.internal("leaderboards_mockup.png"));
    addAllListeners();
    addAllActors();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draws the text "Leaderboards (To be implemented)"  located at (20, 750) of the native
    // resolution 480 by 800.
    leaderboardText.draw(spriteBatch, "Leaderboards (To be implemented)", 20, 750);
    // Draws a sprite using the leaderboardsMockUp texture located at (65, 3000) of the native
    // resolution 480 by 800.
    spriteBatch.draw(new Sprite(leaderboardsMockUp), 65, 300);
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
