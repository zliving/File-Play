package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.FilePlayMain;

/**
 * Created by James on 10/24/2016.
 */

public abstract class BaseScreen implements Screen {
  protected final SpriteBatch spriteBatch;
  protected final OrthographicCamera camera;
  protected final FilePlayMain mainGame;
  protected final Stage stage;
  private FreeTypeFontGenerator generator;
  private FreeTypeFontParameter parameter;
  private BitmapFont font;

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
    stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera), spriteBatch);
    font = new BitmapFont();
    parameter = new FreeTypeFontParameter();
    Gdx.input.setInputProcessor(stage);
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
    generator.dispose();
  }

  /**
   * This function will change the font object in this class to be whatever font is specified by the
   * 'fontPath' string. The parameter 'fontPath' is assumed to be relative to the fonts directory
   * inside of the assets folder.
   *
   * @param fontPath string representing a relative path from the fonts directory of the assets
   *                 folder to a .ttf file.
   * @param size     the desired size in pixels of the font.
   */
  protected BitmapFont generateNewFont(String fontPath, int size) {
    if (generator != null) {
      generator.dispose();
    }
    parameter.size = size;
    generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts\\" + fontPath));
    font = generator.generateFont(parameter);
    return font;
  }

  /**
   * Overloaded method of generateNewFont which takes an addition parameter which is the color.
   *
   * @param fontPath string representing a relative path from the fonts directory of the assets
   *                 folder to a .ttf file.
   * @param size     the desired size in pixels of the font.
   * @param color    the desired color of the font. Note that this may vary depending on the
   *                 properties of each individual .ttf file.
   */
  protected BitmapFont generateNewFont(String fontPath, int size, Color color) {
    if (generator != null) {
      generator.dispose();
    }
    parameter.size = size;
    parameter.color = color;
    generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts\\" + fontPath));
    font = generator.generateFont(parameter);
    return font;
  }


  protected abstract void addAllActors();

  protected abstract void addAllListeners();
}
