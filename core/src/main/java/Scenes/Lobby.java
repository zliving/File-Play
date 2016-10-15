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

import UIElements.Button;

public class Lobby implements Screen, GestureDetector.GestureListener {
  private OrthographicCamera camera;
  private GestureDetector gestureDetector;
  private BitmapFont lobbyScreenText;
  private BitmapFont playText;
  private Button backButton;
  private Button playButton;
  private SpriteBatch spriteBatch;
  private ScreenManager screenManager;

  public Lobby(ScreenManager screenManager) {
    this.screenManager = screenManager;
    backButton = new Button(new Texture(Gdx.files.internal("testButton.jpg")), 20, 650);
    playButton = new Button(new Texture(Gdx.files.internal("testButton.jpg")), 360, 650);
    lobbyScreenText = new BitmapFont();
    lobbyScreenText.setColor(Color.TEAL);
    playText = new BitmapFont();
    playText.setColor(Color.TEAL);
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    spriteBatch.begin();
    spriteBatch.draw(backButton.getSprite(), backButton.getX(), backButton.getY());
    spriteBatch.draw(playButton.getSprite(), playButton.getX(), playButton.getY());
    lobbyScreenText.draw(spriteBatch, "Lobby Screen", 20, 750);
    playText.draw(spriteBatch, "Play", 400, 750);
    spriteBatch.end();
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
    spriteBatch.dispose();
    lobbyScreenText.dispose();
    playText.dispose();
  }

  @Override
  public boolean touchDown(float x, float y, int pointer, int button) {
    return false;
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    float correctedY = Gdx.graphics.getHeight() - y;
    if (backButton.isClicked(x, correctedY)) {
      screenManager.setState(ScreenManager.Screens.MAINMENU);
    } else if (playButton.isClicked(x, correctedY)) {
      screenManager.setState(ScreenManager.Screens.PLAY);
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
