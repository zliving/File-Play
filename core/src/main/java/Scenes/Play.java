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
 * The play screen will be where two users answer trivia questions retrieved from the database
 * and where the actual gameplay will occur.
 */
public class Play implements Screen, GestureDetector.GestureListener {
  private OrthographicCamera camera;
  private GestureDetector gestureDetector;
  private BitmapFont playScreenText;
  private Texture playMockUp;
  private Button backButton;
  private SpriteBatch spriteBatch;
  private ScreenManager screenManager;
  private Viewport viewport;
  private static final float WORLD_WIDTH = 480;
  private static final float WORLD_HEIGHT = 800;
  private float HeightWorldPixelRatio = WORLD_HEIGHT / (float) Gdx.graphics.getHeight();
  private float WidthWorldPixelRatio = WORLD_WIDTH / Gdx.graphics.getWidth();

  /**
   * Refer to MainMenu.java for comments regarding each section. Play should operate in the
   * same way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Play(ScreenManager screenManager) {
    this.screenManager = screenManager;
    // Creates a button with the given texture at a location of (20, 650) of the native screen
    // resolution 480 by 800.
    backButton = new Button(new Texture(Gdx.files.internal("back_button.png")), 20, 650);
    playScreenText = new BitmapFont();
    playScreenText.setColor(Color.YELLOW);
    playMockUp = new Texture(Gdx.files.internal("play_mockup.png"));
    camera = new OrthographicCamera();
    viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
    viewport.apply();
    camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    spriteBatch = new SpriteBatch();
    gestureDetector = new GestureDetector(this);
    Gdx.input.setInputProcessor(gestureDetector);
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    camera.update();
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    spriteBatch.draw(backButton.getSprite(), backButton.getX(), backButton.getY());
    // Draws the text "Play Screen(To be implemented" located at (20, 750) of the native
    // resolution 480 by 800.
    playScreenText.draw(spriteBatch, "Play Screen (To be implemented)", 20, 750);
    // Draws a sprite using the playMockUp texture  located at (65, 300) of the native
    // resolution 480 by 800.
    spriteBatch.draw(new Sprite(playMockUp), 65, 300);
    spriteBatch.end();
  }

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
    playScreenText.dispose();
    playMockUp.dispose();
  }

  @Override
  public boolean touchDown(float x, float y, int pointer, int button) {
    return false;
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    HeightWorldPixelRatio = WORLD_HEIGHT / (float) Gdx.graphics.getHeight();
    WidthWorldPixelRatio = WORLD_WIDTH / (float) Gdx.graphics.getWidth();
    float worldX = x * WidthWorldPixelRatio;
    float correctedY = Gdx.graphics.getHeight() - y;
    float worldY = correctedY * HeightWorldPixelRatio;
    if (backButton.isClicked(worldX, worldY)) {
      System.out.println("Go back to lobby");
      screenManager.setState(ScreenManager.Screens.LOBBY);
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
