package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.FilePlayMain;

import GameObjects.TriviaQuestionBuilder;
import UIElements.ButtonActor;


public class Test extends BaseScreen implements Screen, GestureDetector.GestureListener {

  public Test(FilePlayMain mainGame) {
    super(mainGame);
    ButtonActor a = new ButtonActor(new Texture(Gdx.files.internal("play_button.png")), 240, 400);
    stage.addActor(a);
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    super.render(delta);
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);
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
  protected void addAllActors() {
  }

  @Override
  protected void addAllListeners() {
  }

  @Override
  public boolean touchDown(float x, float y, int pointer, int button) {
    return false;
  }

  @Override
  public boolean tap(float x, float y, int count, int button) {
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

  @Override
  public void createButtons() {

  }


}
