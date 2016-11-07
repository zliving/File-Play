package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
  protected final TextureAtlas buttonAtlas;
  protected final Texture banner;
  protected GlyphLayout bannerTextGlyphLayout;
  protected BitmapFont bannerText;
  protected Skin buttonSkin;
  private FreeTypeFontGenerator generator;
  private FreeTypeFontParameter parameter;

  // This is the native screen size that will be the reference for everything placed on the screen.
  protected static final float WORLD_WIDTH = 480;
  protected static final float WORLD_HEIGHT = 800;

  // Used to calculate where to draw the text in the banner so that it is centered.
  protected int glyphCenterX;

  // Ratio of world units and pixels of a screen.
  protected float HeightWorldPixelRatio = WORLD_HEIGHT / (float) Gdx.graphics.getHeight();
  protected float WidthWorldPixelRatio = WORLD_WIDTH / Gdx.graphics.getWidth();

  BaseScreen(FilePlayMain mainGame) {
    this.mainGame = mainGame;
    spriteBatch = new SpriteBatch();
    // Initializes the banner Texture.
    banner = new Texture(Gdx.files.internal("banner - HSYB-Long.png"));
    // Creates new FreeTypeFontParameter to modify fonts.
    parameter = new FreeTypeFontParameter();
    // Creates BitmapFont for the text that is going to be in the center of the banner.
    bannerText = new BitmapFont();
    bannerText = generateNewFont("VacationPostcardNF.ttf", 36, Color.BLACK);
    // Creates an atlas object which can use all the textures within it. Each screen will have
    // access to the atlas in order to create button skins from it.
    buttonAtlas = new TextureAtlas(Gdx.files.internal("nano.pack"));
    buttonSkin = new Skin();
    // Adds all the regions from the atlas so that it can getDrawable using the name of each
    // texture.
    buttonSkin.addRegions(buttonAtlas);
    camera = new OrthographicCamera();
    stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera), spriteBatch);
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
    if (generator != null) {
      generator.dispose();
    }
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
    return generator.generateFont(parameter);
  }

  protected abstract void addAllActors();

  protected abstract void addAllListeners();

  protected abstract void createButtons();
}
