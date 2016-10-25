package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import GameObjects.TriviaQuestionBuilder;


public class Test implements Screen, GestureDetector.GestureListener {
  private final SpriteBatch batch;
  private final BitmapFont font;
  private final GlyphLayout fontLayout;
  private final TriviaQuestionBuilder newTriviaRound;
  private final Camera camera;
  private final Sprite badSprite;
  private final GestureDetector gestureDetector;


  public Test() {
    batch = new SpriteBatch();
    font = new BitmapFont();
    fontLayout = new GlyphLayout();
    String url = "http://www.opentdb.com/api.php?amount=2";
    newTriviaRound = new TriviaQuestionBuilder();
    newTriviaRound.getTriviaQuestions(url);
    font.setColor(Color.TEAL);
    font.getData().setScale(2);
    font.getRegion().getTexture()
        .setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    badSprite = new Sprite(new Texture(Gdx.files.internal("testButton.jpg")));
    badSprite.setPosition(Gdx.graphics.getWidth() / 2, 700);
    gestureDetector = new GestureDetector(this);
    Gdx.input.setInputProcessor(gestureDetector);
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    // Get the width and height of the device screen.
    camera.update();
    batch.setProjectionMatrix(camera.combined);
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    // Calculate where the center of the screen is based on the size of the fontLayout GlyphLayout.
    float middleScreenWidth = (width / 2) - (fontLayout.width / 2);
    float middleScreenHeight = height / 2;
    // Set the background to black and clear the color buffer bit.
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    // Begin batch and add items for it to draw.
    batch.begin();
    batch.draw(badSprite, badSprite.getX(), badSprite.getY());
    fontLayout.setText(font, "File Play");
    font.draw(batch, fontLayout, middleScreenWidth, middleScreenHeight);
    batch.end();
  }

  @Override
  public void resize(int width, int height) {
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
    batch.dispose();
    font.dispose();
  }

  @Override
  public boolean touchDown(float x, float y, int pointer, int button) {
    return false;
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    // The origin is taken from the upper left corner therefore you must convert the y to be
    // relative to the lower left hand corner.
    float actualY = Gdx.graphics.getHeight() - y;
    System.out.println("Position x: " + badSprite.getX() + " Position y: " + badSprite.getY());
    System.out.println("X tap: " + x + " Y tap: " + actualY);
    System.out.println("Size: x" + badSprite.getHeight() + " Size: y: " + badSprite.getWidth());
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
  public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1,
                       Vector2 pointer2) {
    return false;
  }

  @Override
  public void pinchStop() {
  }
}
