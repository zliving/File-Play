package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.FilePlayMain;


import UIElements.ButtonActor;

/**
 * MainMenu displays the main menu screen of the game with buttons that transition them into
 * appropriate screens from the main menu.
 */
public class MainMenu extends BaseScreen {
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
    // This tells LibGDX's 3D engine how to render in 2D.
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draws the banner.
    spriteBatch.draw(new Sprite(banner), 0, 720);
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
    style.up = buttonSkin.getDrawable("heavy-sat-yellow-246x46");
    style.down = buttonSkin.getDrawable("heavy-sat-yellow-246x46");
    style.font = generateNewFont("Rampung.ttf", 30, Color.BLACK);
    // Creates the three buttons using the style specified above.
    playButton = new TextButton("Play", style);
    leaderboardsButton = new TextButton("Leaderboards", style);
    settingsButton = new TextButton("Settings", style);
    // Sets the location for each of the buttons.
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
        // Change to leaderboards screen.
        mainGame.setScreen(FilePlayMain.ScreenType.LEADERBOARDS);
        return true;
      }
    });
    settingsButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change to the settings screen.
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
    stage.addActor(playButton);
    stage.addActor(leaderboardsButton);
    stage.addActor(settingsButton);
  }
}
