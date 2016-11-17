package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.FilePlayMain;

/**
 * Created by James on 10/24/2016.
 */

public abstract class BaseScreen implements Screen {
  protected final SpriteBatch spriteBatch;
  protected final OrthographicCamera camera;
  protected final FilePlayMain mainGame;
  protected final Stage stage;
  protected TextureAtlas buttonAtlas;
  protected final Sprite banner;
  protected GlyphLayout bannerTextGlyphLayout;
  protected BitmapFont bannerText;
  protected Skin buttonSkin;
  private FreeTypeFontGenerator generator;
  private FreeTypeFontParameter parameter;
  //public Music music;

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
    // Creates new FreeTypeFontParameter to modify fonts.
    parameter = new FreeTypeFontParameter();
    // Creates BitmapFont for the text that is going to be in the center of the banner.
    bannerText = new BitmapFont();
    bannerText = generateNewFont("BROADSolid.ttf", 36, Color.BLACK);
    // Creates an atlas object which can use all the textures within it. Each screen will have
    // access to the atlas in order to create button skins from it.
    buttonAtlas = new TextureAtlas(Gdx.files.internal("final atlas 4.pack"));
    buttonSkin = new Skin();
    // Initializes the banner Sprite.
    banner = buttonAtlas.createSprite("banner - plain");
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
    // Sets the background to a medium dark shade of grey.
    Gdx.gl.glClearColor(64/225.0f, 64/225.0f, 64/225.0f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    // Draw the banner at the top of the screen.
    spriteBatch.draw(banner, 0, 720);
    spriteBatch.end();
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
    generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + fontPath));
    BitmapFont smoothFont = generator.generateFont(parameter);
    // Linearly filter the text to prevent pixelation. 
    smoothFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    return smoothFont;
  }

  /**
   * This method returns the x value of 'offset' from the right end of 'button'.
   *
   * @param button the button to calculate the offset from
   * @param offset the offset from the right end that is desired.
   * @return the x coordinate 'offset' pixels from the right end of 'button'
   */
  protected float getButtonXOffset(TextButton button, float offset){
    return button.getX() + button.getWidth() + offset;
  }

  /**
   * This method returns the y value of 'offset' from the bottom end of 'button' using an offset of
   * -('button' height + offset) from the bottom end of 'button'
   *
   * @param button the button to calculate the offset from
   * @param offset the offset from the bottom end that is desired.
   * @return the y coordinate 'offset' pixels from the bottom end of 'button'
   */
  protected float getButtonYOffset(TextButton button, float offset){
    return button.getY() - (button.getHeight() + offset);
  }

  protected abstract void addAllActors();

  protected abstract void addAllListeners();

  protected abstract void createButtons();
}
