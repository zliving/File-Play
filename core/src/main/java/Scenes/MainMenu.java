package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
public class MainMenu implements Screen, GestureDetector.GestureListener {
  private Button playButton;
  private Button leaderBoardsButton;
  private Button settingsButton;
  private SpriteBatch spriteBatch;
  private OrthographicCamera camera;
  private GestureDetector gestureDetector;
  private BitmapFont mainMenuText;
  private ScreenManager screenManager;
  private Viewport viewport;
  // This is the native screen size that will be the reference for everything placed on the screen.
  private static final float WORLD_WIDTH = 480;
  private static final float WORLD_HEIGHT = 800;
  // Ratio of world units and pixels of a screen.
  private float HeightWorldPixelRatio = WORLD_HEIGHT / (float) Gdx.graphics.getHeight();
  private float WidthWorldPixelRatio = WORLD_WIDTH / Gdx.graphics.getWidth();

  public MainMenu(ScreenManager screenManager) {
    this.screenManager = screenManager;
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
    spriteBatch = new SpriteBatch();
    mainMenuText = new BitmapFont();
    mainMenuText.setColor(Color.YELLOW);
    camera = new OrthographicCamera();
    // This creates a viewport of the screen using the camera.
    viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
    viewport.apply();
    // This centers the camera at the center of the viewport.
    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    // Set up listener for events happening on the screen.
    gestureDetector = new GestureDetector(this);
    Gdx.input.setInputProcessor(gestureDetector);
    this.resize((int) WORLD_WIDTH, (int) WORLD_HEIGHT);
  }

  @Override
  public void show() {
  }

  /**
   * The render method updates the screen every iteration given the change in time between
   * iterations.
   */
  @Override
  public void render(float delta) {
    camera.update();
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
   * The resize method updates the viewport and camera in the case that the window is resized.
   */
  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void hide() {
  }

  @Override
  public void dispose() {
    spriteBatch.dispose();
    mainMenuText.dispose();
  }

  @Override
  public boolean touchDown(float x, float y, int pointer, int button) {
    return false;
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
      screenManager.setState(ScreenManager.Screens.LOBBY);
    } else if (leaderBoardsButton.isClicked(worldX, worldY)) {
      System.out.println("Go to leaderboards");
      screenManager.setState(ScreenManager.Screens.LEADERBOARDS);
    } else if (settingsButton.isClicked(worldX, worldY)) {
      System.out.println("Go to settings");
      screenManager.setState(ScreenManager.Screens.SETTINGS);
    }
    return false;
  }

  @Override
  public boolean longPress(float x, float y) {
    return false;
  }

  @Override
  public boolean fling(float velocityX, float velocityY, int button) {
    return false;
  }

  @Override
  public boolean pan(float x, float y, float deltaX, float deltaY) {
    return false;
  }

  @Override
  public boolean panStop(float x, float y, int pointer, int button) {
    return false;
  }

  @Override
  public boolean zoom(float initialDistance, float distance) {
    return false;
  }

  @Override
  public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
    return false;
  }

  @Override
  public void pinchStop() {
  }
}
