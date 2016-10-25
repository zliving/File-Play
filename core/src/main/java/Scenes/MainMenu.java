package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import UIElements.Button;

/**
 * TODO(Chris): Refactor world width, height, batch, and ratios into Game class.
 * TODO(Chris): Include relative offsets and spacing.
 * MainMenu will display the main menu screen of the game with buttons that transition them into
 * appropriate screens from the main menu.
 */
class MainMenu extends BaseScreen {
  private final Button playButton;
  private final Button leaderBoardsButton;
  private final Button settingsButton;
  private final BitmapFont mainMenuText;

  /**
   * MainMenu takes a ScreenManager so that it may use it to change states.
   *
   * @param screenManager a reference to a ScreenManager in order to change screens.
   */
  public MainMenu(ScreenManager screenManager) {
    super(screenManager);

    // Creates a button using the given texture at (120, 400) of the native resolution 480
    // by 800.
    playButton = new Button(new Texture(Gdx.files.internal("play_button.png")), 120, 400);

    // Creates a button using the given texture at (120, 300) of the native resolution
    // 480 by 800.
    leaderBoardsButton = new Button(new Texture(Gdx.files.internal("leaderboards_button.png")),
                                    120, 300);

    // Creates a button using the given texture at (120, 200) of the native resolution 480
    // by 800.
    settingsButton = new Button(new Texture(Gdx.files.internal("options_button.png")), 120, 200);
    mainMenuText = new BitmapFont();
    mainMenuText.setColor(Color.YELLOW);
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
    spriteBatch.draw(playButton.getSprite(), playButton.getX(), playButton.getY());
    spriteBatch.draw(leaderBoardsButton.getSprite(), leaderBoardsButton.getX(),
                     leaderBoardsButton.getY());
    spriteBatch.draw(settingsButton.getSprite(), settingsButton.getX(), settingsButton.getY());
    // Draws the text "Main Menu" at the location (20, 750) of the native screen resolution 480 by
    // 800.
    mainMenuText.draw(spriteBatch, "Main Menu", 20, 750);
    spriteBatch.end();
  }

  /**
   * Tap is part of the GestureListener interface and gives the x and y pixel coordinates
   * corresponding to a user's touch. Note that the coordinate system for tap refers to the upper
   * left hand corner as the origin.
   */
  @Override
  public boolean tap(float x, float y, int count, int button) {
    HeightWorldPixelRatio = WORLD_HEIGHT / (float) Gdx.graphics.getHeight();
    WidthWorldPixelRatio = WORLD_WIDTH / (float) Gdx.graphics.getWidth();
    float worldX = x * WidthWorldPixelRatio;
    // Tap takes the upper left hand corner to be the origin (0, 0) therefore some correction
    // must be made in order to pass into the isClicked method.
    float correctedY = Gdx.graphics.getHeight() - y;
    // Convert to world units.
    float worldY = correctedY * HeightWorldPixelRatio;
    if (playButton.isClicked(worldX, worldY)) {
      System.out.println("Go to lobby");
      screenManager.setState(ScreenManager.ScreenType.LOBBY);
    } else if (leaderBoardsButton.isClicked(worldX, worldY)) {
      System.out.println("Go to leaderboards");
      screenManager.setState(ScreenManager.ScreenType.LEADERBOARDS);
    } else if (settingsButton.isClicked(worldX, worldY)) {
      System.out.println("Go to settings");
      screenManager.setState(ScreenManager.ScreenType.SETTINGS);
    }
    return false;
  }
}
