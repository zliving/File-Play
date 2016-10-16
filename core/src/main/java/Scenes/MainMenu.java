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

public class MainMenu implements Screen, GestureDetector.GestureListener {
  private Button playButton;
  private Sprite settingsButton;
  private SpriteBatch spriteBatch;
  private OrthographicCamera camera;
  private GestureDetector gestureDetector;
  private BitmapFont mainMenuText;
  private ScreenManager screenManager;
  private Viewport viewport;
  private final float WORLD_WIDTH = 480;
  private final float WORLD_HEIGHT = 800;
  private float HeightWorldPixelRatio = WORLD_HEIGHT / (float) Gdx.graphics
          .getHeight();
  private float WidthWorldPixelRatio = WORLD_WIDTH / Gdx.graphics.getWidth();

  public MainMenu(ScreenManager screenManager) {
    this.screenManager = screenManager;
    //TODO: Include relative offsets and spacing
    playButton = new Button(new Texture(Gdx.files.internal("testButton.jpg")), 200, 400);
    spriteBatch = new SpriteBatch();
    mainMenuText = new BitmapFont();
    mainMenuText.setColor(Color.TEAL);
    camera = new OrthographicCamera();
    viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
    viewport.apply();
    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
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
    spriteBatch.draw(playButton.getSprite(), playButton.getX(),
            playButton.getY());
//    mainMenuText.draw(spriteBatch, "Main Menu", 20, 750);
    spriteBatch.end();

  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
    camera.position.set(camera.viewportWidth/2 , camera.viewportHeight / 2, 0);
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

  @Override
  public boolean tap(float x, float y, int count, int button) {
    HeightWorldPixelRatio = WORLD_HEIGHT / (float) Gdx.graphics.getHeight();
    WidthWorldPixelRatio = WORLD_WIDTH / Gdx.graphics.getWidth();
    float worldX = x * WidthWorldPixelRatio;
    float worldY = y * HeightWorldPixelRatio;
    if(playButton.isClicked(worldX, worldY)){
      System.out.println("Go to leaderboards");
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
