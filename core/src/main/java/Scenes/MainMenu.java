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
 * TODO(Chris): Change buttons to allow for text Include relative offsets and spacing. MainMenu will
 * display the main menu screen of the game with buttons that transition them into appropriate
 * screens from the main menu.
 */
public class MainMenu extends BaseScreen {
  private final ButtonActor playButton;
  private final ButtonActor leaderboardsButton;
  private final ButtonActor settingsButton;
  private ButtonActor bannerButton;
  private BitmapFont mainMenuText;
  private BitmapFont playButtonText;
  private BitmapFont leaderboardsbuttontext;
  private BitmapFont settingsButtonText;

  /**
   * MainMenu takes in mainGame so that it may use it to change states.
   *
   * @param mainGame a reference to the main game in order to change screens.
   */
  public MainMenu(FilePlayMain mainGame) {
    super(mainGame);
    /* Creates a button using the given texture at (120, 400) of the native resolution 480
     * by 800.*/
    playButton = new ButtonActor(new Texture(Gdx.files.internal("heavy-sat-yellow-246x46.png")), 120, 400);
    /* Creates a button using the given texture at (120, 300) of the native resolution
     * 480 by 800. */
    leaderboardsButton = new ButtonActor(new Texture(Gdx.files.internal("heavy-sat-yellow-246x46.png")),
            120, 300);
    /* Creates a button using the given texture at (120, 200) of the native resolution 480
     * by 800. */
    settingsButton = new ButtonActor(new Texture(Gdx.files.internal("heavy-sat-yellow-246x46.png")),
            120, 200);
    /* Creates the banner button at the location (0, 720) of the native resolution. */
    bannerButton = new ButtonActor(new Texture(Gdx.files.internal("banner - HSYB-Long.png")),
            0, 720);
    addAllText();
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
    /* This tells LibGDX's 3D engine how to render in 2D. */
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();

    /* Every button will have it's corresponding name written over it. The locations (x,y) are the
     * centers of each button and the scaling (1.2f) was chosen arbitrarily.*/
    playButtonText.draw(spriteBatch, "Play", 230, 430);
    playButtonText.getData().setScale(1.2f);
    leaderboardsbuttontext.draw(spriteBatch, "Leaderboards", 195, 330);
    leaderboardsbuttontext.getData().setScale(1.2f);
    settingsButtonText.draw(spriteBatch, "Settings", 210, 230);
    settingsButtonText.getData().setScale(1.2f);
    /* Draws the text "Main Menu" at the location (20, 750) of the native screen resolution 480 by
     * 800. */
    mainMenuText.draw(spriteBatch, "Main Menu", 20, 700);
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
   * Adds all the text needed for buttons and the "Main Menu" text
   */
  protected void addAllText() {
    mainMenuText = font;
    mainMenuText.setColor(Color.WHITE);
    playButtonText = new BitmapFont();
    playButtonText.setColor(Color.BLACK);
    leaderboardsbuttontext = new BitmapFont();
    leaderboardsbuttontext.setColor(Color.BLACK);
    settingsButtonText = new BitmapFont();
    settingsButtonText.setColor(Color.BLACK);
  }

  /**
   * Adds all of the buttons to the stage so that they are drawn to the screen.
   */
  @Override
  protected void addAllActors() {
    stage.addActor(bannerButton);
    stage.addActor(playButton);
    stage.addActor(leaderboardsButton);
    stage.addActor(settingsButton);
  }
}
