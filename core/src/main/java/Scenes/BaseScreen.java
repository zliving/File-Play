package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FilePlayMain;

/**
 * Created by James on 10/24/2016.
 */

public abstract class BaseScreen implements Screen {
  protected final SpriteBatch spriteBatch;
  protected final OrthographicCamera camera;
  //  protected final GestureDetector gestureDetector;
//  protected final Viewport viewport;
  protected final FilePlayMain mainGame;
  protected final Stage stage;

  // This is the native screen size that will be the reference for everything placed on the screen.
  protected static final float WORLD_WIDTH = 480;
  protected static final float WORLD_HEIGHT = 800;

  // Ratio of world units and pixels of a screen.
  protected float HeightWorldPixelRatio = WORLD_HEIGHT / (float) Gdx.graphics.getHeight();
  protected float WidthWorldPixelRatio = WORLD_WIDTH / Gdx.graphics.getWidth();

  BaseScreen(FilePlayMain mainGame) {
    this.mainGame = mainGame;
    spriteBatch = new SpriteBatch();
    camera = new OrthographicCamera();
    //viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
    //viewport.apply();
    stage = new Stage(new ScreenViewport(camera), spriteBatch);
    Gdx.input.setInputProcessor(stage);
//    gestureDetector = new GestureDetector(this);
//    Gdx.input.setInputProcessor(gestureDetector);
  }

  @Override
  public void show() {
  }

  /**
   * The render method updates the screen every iteration given the change in time between
   * iterations.
   *
   * @param delta time between iterations
   */
  @Override
  public void render(float delta) {
    //camera.update();
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    // This is equivalent to updating actors that are added to the stage.
    stage.act();
    stage.draw();
  }

  /**
   * The resize method updates the viewport and camera in the case that the window is resized.
   *
   * @param width  width that window is resized to
   * @param height height that window is resized to
   */
  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
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
    stage.dispose();
  }
}
