package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import UIElements.Button;

public class MainMenu implements Screen, GestureDetector.GestureListener {
  private Button leaderboardsButton;
  private Button playButton;
  private Button settingsButton;
  private SpriteBatch spriteBatch;
  private OrthographicCamera camera;
  private GestureDetector gestureDetector;

  public MainMenu() {
    leaderboardsButton = new Button(new Texture(Gdx.files.internal("testButton.jpg")), 240, 0);
    playButton = new Button(new Sprite(new Texture(Gdx.files.internal("testButton.jpg"))), 240,
            300);
    settingsButton = new Button(new Texture(Gdx.files.internal("testButton.jpg")), 240, 700);
    spriteBatch = new SpriteBatch();
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    gestureDetector = new GestureDetector(this);
    Gdx.input.setInputProcessor(gestureDetector);
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    spriteBatch.begin();
    spriteBatch.draw(playButton.getSprite(), playButton.getX(), playButton.getY());
    spriteBatch.draw(leaderboardsButton.getSprite(), leaderboardsButton.getX(), leaderboardsButton.getY());
    spriteBatch.draw(settingsButton.getSprite(), settingsButton.getX(), settingsButton.getY());
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
  }

  @Override
  public boolean touchDown(float x, float y, int pointer, int button) {
    return false;
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
    float correctedY = Gdx.graphics.getHeight() - y;
    if (playButton.isClicked(x, correctedY)) {
      System.out.println("Change to play screen");
    } else if (leaderboardsButton.isClicked(x, correctedY)) {
      System.out.println("Change to leaderboards screen");
    } else if (settingsButton.isClicked(x, correctedY)) {
      System.out.println("Change to settings screen");
    } else {
      System.out.println("Nothing tapped");
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
