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
import com.mygdx.game.FilePlayMain;

import UIElements.Button;

/**
 * TODO(Chris): Refactor world width, height, batch, and ratios into Game class.
 * TODO(Chris): Include relative offsets and spacing.
 * MainMenu will display the main menu screen of the game with buttons that transition them into
 * appropriate screens from the main menu.
 */
public class MainMenu extends BaseScreen {
  private final Button playButton;
  private final Button leaderBoardsButton;
  private final Button settingsButton;
  private final Button bannerButton;
  private final BitmapFont playButtonText;
  private final BitmapFont leaderBoardsButtonText;
  private final BitmapFont settingsButtonText;
  /**
   * MainMenu takes a ScreenManager so that it may use it to change states.
   *
   * @param mainGame a reference to a ScreenManager in order to change screens.
   */
  public MainMenu(FilePlayMain mainGame) {
    super(mainGame);

    // Draws the banner at the upper left corner at (0, height of screen - height of banner) =
    // (0, 720). For now it's a static image, sooner I'll get the arrow on the right to serve as
    // a back button and the lines will be a drop-down menu later on.
    bannerButton = new Button(new Texture(Gdx.files.internal("banner - HSYB-Long.png")), 0, 720);

    // Creates a button using the given texture at (120, 400) of the native resolution 480
    // by 800.
    playButton = new Button(new Texture(Gdx.files.internal("heavy-sat-yellow-246x46.png")), 120, 400);

    // Creates a button using the given texture at (120, 300) of the native resolution
    // 480 by 800.
    leaderBoardsButton = new Button(new Texture(Gdx.files.internal("heavy-sat-yellow-246x46.png")),
                                    120, 300);

    // Creates a button using the given texture at (120, 200) of the native resolution 480
    // by 800.
    settingsButton = new Button(new Texture(Gdx.files.internal("heavy-sat-yellow-246x46.png")),
                                120, 200);
    playButtonText = new BitmapFont();
    playButtonText.setColor(Color.BLACK);
    leaderBoardsButtonText = new BitmapFont();
    leaderBoardsButtonText.setColor(Color.BLACK);
    settingsButtonText = new BitmapFont();
    settingsButtonText.setColor(Color.BLACK);
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
    spriteBatch.draw(bannerButton.getSprite(), bannerButton.getX(), bannerButton.getY());

    /* Every button will have it's corresponding name written over it. The locations (x,y) are the
    * centers of each button and the scaling (1.2f) was chosen arbitrarily.*/
    playButtonText.draw(spriteBatch, "Play", 230, 430);
    playButtonText.getData().setScale(1.2f);
    leaderBoardsButtonText.draw(spriteBatch, "Leaderboards", 195, 330);
    leaderBoardsButtonText.getData().setScale(1.2f);
    settingsButtonText.draw(spriteBatch, "Settings", 210, 230);
    settingsButtonText.getData().setScale(1.2f);

    spriteBatch.end();
  }

  /**
   * Tap is part of the GestureListener interface and gives the x and y pixel coordinates
   * corresponding to a user's touch. Note that the coordinate system for tap refers to the upper
   * left hand corner as the origin.
   */
  @Override
  public boolean tap(float x, float y, int count, int button) {
    float worldX = x * WidthWorldPixelRatio;
    // Tap takes the upper left hand corner to be the origin (0, 0) therefore some correction
    // must be made in order to pass into the isClicked method.
    float correctedY = Gdx.graphics.getHeight() - y;
    // Convert to world units.
    float worldY = correctedY * HeightWorldPixelRatio;
    if (playButton.isClicked(worldX, worldY)) {
      System.out.println("Go to lobby");
      mainGame.setScreen(FilePlayMain.ScreenType.LOBBY);
    } else if (leaderBoardsButton.isClicked(worldX, worldY)) {
      System.out.println("Go to leaderboards");
      mainGame.setScreen(FilePlayMain.ScreenType.LEADERBOARDS);
    } else if (settingsButton.isClicked(worldX, worldY)) {
      System.out.println("Go to settings");
      mainGame.setScreen(FilePlayMain.ScreenType.SETTINGS);
    }
    return false;
  }
}
