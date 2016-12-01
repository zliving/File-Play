package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.FilePlayMain;

/**
 * MainMenu displays the main menu screen of the game with buttons that transition them into
 * appropriate screens from the main menu.
 */
public class MainMenu extends BaseScreen {
  private TextButton playButton;
  private TextButton leaderboardsButton;
  private TextButton settingsButton;
  private TextButton rouletteButton;
  private final int BUTTON_HEIGHT = 50;
  private final int BUTTON_WIDTH = 250;

  /**
   * MainMenu takes in mainGame so that it may use it to change states.
   *
   * @param mainGame a reference to the main game in order to change screens.
   */
  public MainMenu(FilePlayMain mainGame) {
    super(mainGame);
    // Creates GlyphLayout to get width for centering text in the banner.
    bannerTextGlyphLayout = new GlyphLayout(bannerText, "Main Menu");
    // Calculate the center for the text to be drawn in the banner.
    glyphCenterX = ((int) WORLD_WIDTH - (int) bannerTextGlyphLayout.width) / 2;
    createButtons();
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
    stage.act();
    stage.draw();
    spriteBatch.begin();
    // Draws the text "Main Menu" in the center of the banner.
    bannerText.draw(spriteBatch, bannerTextGlyphLayout, glyphCenterX, 770);
    spriteBatch.end();
  }

  /**
   * Creates all of the buttons that will be drawn to the screen.
   */
  @Override
  protected void createButtons() {
    TextButtonStyle style = new TextButtonStyle();
    // Sets the skin for when the button is not pressed and when it is. The argument that is passed
    // is searched for in the atlas within the buttonSkin object.
    style.up = buttonSkin.getDrawable("nano yellow");
    style.down = buttonSkin.getDrawable("nano yellow");
    style.font = generateNewFont("Rampung.ttf", 30, Color.BLACK);
    // Creates the three buttons using the style specified above.
    playButton = new TextButton("Play", style);
    leaderboardsButton = new TextButton("Leaderboards", style);
    settingsButton = new TextButton("Settings", style);
    rouletteButton = new TextButton("Roulette", style);
    // Sets the location and height for each of the buttons.
    playButton.setPosition(120, 450);
    playButton.setHeight(BUTTON_HEIGHT);
    playButton.setWidth(BUTTON_WIDTH);
    leaderboardsButton.setPosition(120, 350);
    leaderboardsButton.setHeight(BUTTON_HEIGHT);
    leaderboardsButton.setWidth(BUTTON_WIDTH);
    settingsButton.setPosition(120, 250);
    settingsButton.setHeight(BUTTON_HEIGHT);
    settingsButton.setWidth(BUTTON_WIDTH);
    rouletteButton.setPosition(120, 150);
    rouletteButton.setHeight(BUTTON_HEIGHT);
    rouletteButton.setWidth(BUTTON_WIDTH);

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
        mainGame.setScreen(new Lobby(mainGame));
        return true;
      }
    });
    leaderboardsButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        mainGame.setScreen(new Leaderboards(mainGame));
        return true;
      }
    });
    settingsButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        mainGame.setScreen(new Settings(mainGame));
        return true;
      }
    });
    rouletteButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change to the settings screen.
        mainGame.setScreen(new Roulette(mainGame));
        return true;
      }
    });
  }

  /**
   * Adds all of the buttons to the stage so that they are drawn to the screen.
   */
  @Override
  protected void addAllActors() {
    stage.addActor(playButton);
    stage.addActor(leaderboardsButton);
    stage.addActor(settingsButton);
    stage.addActor(rouletteButton);
  }
}
