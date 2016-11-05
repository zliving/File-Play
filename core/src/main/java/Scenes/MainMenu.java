package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.FilePlayMain;


import UIElements.ButtonActor;

/**
 * TODO(Chris): Change buttons to allow for text Include relative offsets and spacing. MainMenu will
 * display the main menu screen of the game with buttons that transition them into appropriate
 * screens from the main menu.
 */
public class MainMenu extends BaseScreen {
  private BitmapFont mainMenuText;
  private ButtonActor bannerButton;
  private TextureAtlas buttonsAtlas;
  private Skin buttonSkin;
  private TextButton playButton;
  private TextButton leaderboardsButton;
  private TextButton settingsButton;

  /**
   * MainMenu takes in mainGame so that it may use it to change states.
   *
   * @param mainGame a reference to the main game in order to change screens.
   */
  public MainMenu(FilePlayMain mainGame) {
    super(mainGame);
    mainMenuText = new BitmapFont();
    mainMenuText = generateNewFont("VacationPostcardNF.ttf", 36, Color.WHITE);
    /* Creates the banner button at the location (0, 720) of the native resolution. */
    bannerButton = new ButtonActor(new Texture(Gdx.files.internal("banner - HSYB-Long.png")),
            0, 720);
    createButtons();
    setButtonLocations();
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
    /* Draws the text "Main Menu" at the location (20, 750) of the native screen resolution 480 by
     * 800. */
    mainMenuText.draw(spriteBatch, "Main Menu", 20, 700);
    spriteBatch.end();
  }

  /**
   * Creates all of the buttons that will be drawn to the screen.
   */
  @Override
  protected void createButtons() {
    TextButtonStyle style = new TextButtonStyle();
    /* Creates and atlas object and a new skin which can use all the textures of the given atlas. */
    buttonsAtlas = new TextureAtlas(Gdx.files.internal("buttons.pack"));
    buttonSkin = new Skin();
    /* Adds all the regions from the atlas so that it can getDrawable using the name of each
     * texture. */
    buttonSkin.addRegions(buttonsAtlas);
    /* Sets the skin for when the button is not pressed and when it is. The argument that is passed
     * is taken from the atlas used for the buttonSkin object. */
    style.up = buttonSkin.getDrawable("heavy-sat-yellow-246x46");
    style.down = buttonSkin.getDrawable("heavy-sat-yellow-246x46");
    style.font = generateNewFont("Rampung.ttf", 30, Color.BLACK);
    playButton = new TextButton("Play", style);
    leaderboardsButton = new TextButton("Leaderboards", style);
    settingsButton = new TextButton("Settings", style);
  }

  /**
   * This function is where all of the button locations are set.
   */
  private void setButtonLocations() {
    playButton.setPosition(120, 400);
    leaderboardsButton.setPosition(120, 300);
    settingsButton.setPosition(120, 200);
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
