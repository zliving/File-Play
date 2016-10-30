package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.FilePlayMain;

import UIElements.ButtonActor;

/**
 * TODO(Chris): Refactor world width, height, batch, and ratios into Game class. TODO(Chris):
 * Include relative offsets and spacing. MainMenu will display the main menu screen of the game with
 * buttons that transition them into appropriate screens from the main menu.
 */
public class MainMenu extends BaseScreen {
  private final ButtonActor playButton;
  private final ButtonActor leaderboardsButton;
  private final ButtonActor settingsButton;
  private final BitmapFont mainMenuText;

  /**
   * MainMenu takes in mainGame so that it may use it to change states.
   *
   * @param mainGame a reference to the main game in order to change screens.
   */
  public MainMenu(FilePlayMain mainGame) {
    super(mainGame);
    // Creates a button using the given texture at (120, 400) of the native resolution 480
    // by 800.
    playButton = new ButtonActor(new Texture(Gdx.files.internal("play_button.png")), 120, 400);
    // Creates a button using the given texture at (120, 300) of the native resolution
    // 480 by 800.
    leaderboardsButton = new ButtonActor(new Texture(Gdx.files.internal("leaderboards_button.png")),
            120, 300);
    // Creates a button using the given texture at (120, 200) of the native resolution 480
    // by 800.
    settingsButton = new ButtonActor(new Texture(Gdx.files.internal("options_button.png")),
            120, 200);
    mainMenuText = new BitmapFont();
    mainMenuText.setColor(Color.YELLOW);
    addAllListeners();
    addAllActors();
  }

  /**
   * The render method updates the screen every iteration given the change in time between
   * iterations.
   *
   * @param delta time between iterations
   */
  @Override
  public void render(float delta) {
    super.render(delta);
    // This tells LibGDX's 3D engine how to render in 2D.
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draws the text "Main Menu" at the location (20, 750) of the native screen resolution 480 by
    // 800.
    mainMenuText.draw(spriteBatch, "Main Menu", 20, 750);
    spriteBatch.end();
  }

  /**
   * This adds listeners to each of the buttons along with what to do upon being touched. A new
   * InputListener is added inline overriding the touchDown method to determine what to do when
   * touched.
   */
  private void addAllListeners() {
    playButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change to lobby screen.
        mainGame.setScreen(FilePlayMain.ScreenType.LOBBY);
        return true;
      }
    });
    leaderboardsButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        mainGame.setScreen(FilePlayMain.ScreenType.LEADERBOARDS);
        return true;
      }
    });
    settingsButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        mainGame.setScreen(FilePlayMain.ScreenType.SETTINGS);
        return true;
      }
    });
  }

  /**
   * Adds all of the buttons to the stage so that they are drawn to the screen.
   */
  private void addAllActors() {
    stage.addActor(playButton);
    stage.addActor(leaderboardsButton);
    stage.addActor(settingsButton);
  }
}
