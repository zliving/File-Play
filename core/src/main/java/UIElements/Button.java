package UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * The button class stores information for a given sprite in the native resolution of 480 by 800
 * and provides a way of checking if a button is clicked or not.
 */
public class Button {
  private final Sprite sprite;
  private float x, y, width, height;
  private static final float WORLD_WIDTH = 480;
  private static final float WORLD_HEIGHT = 800;

  /**
   * The button constructor can take in a Sprite and two floats which should correspond to
   * the world units of where the object should be drawn.
   *
   * @param sprite sprite of what to create a button of.
   * @param x      x-coordinate of lower lefthand corner of where t should be drawn.
   * @param y      y-coordinate of lower lefthand corner of where t should be drawn.
   */
  public Button(Sprite sprite, float x, float y) {
    this.sprite = sprite;
    // x and y should be given in world units
    this.x = x;
    this.y = y;
    // This sets the width and height in world units
    this.width = sprite.getWidth() * WORLD_WIDTH / Gdx.graphics.getWidth();
    this.height = sprite.getHeight() * WORLD_HEIGHT / Gdx.graphics.getHeight();
  }

  /**
   * This is an overloaded constructor which takes in a texture and two floats which correspond
   * to the world units of where the object should be drawn. It simply makes a Sprite of the
   * texture that is passed in and then proceeds to do the same as the first constructor.
   *
   * @param t texture of what to create a button of
   * @param x x-coordinate of lower lefthand corner of where t should be drawn.
   * @param y y-coordinate of lower lefthand corner of where t should be drawn.
   */
  public Button(Texture t, float x, float y) {
    sprite = new Sprite(t);
    this.x = x;
    this.y = y;
    this.width = sprite.getWidth() * WORLD_WIDTH / Gdx.graphics.getWidth();
    this.height = sprite.getHeight() * WORLD_HEIGHT / Gdx.graphics.getHeight();
  }

  /**
   * This method returns true if the button that called it has been pressed. The 'x' and 'y'
   * parameters should correspond to the world units of where a click occurred, assuming the
   * origin (0, 0) is in the lower left hand corner.
   *
   * @param x x-coordinate of where a click occurs in world units
   * @param y y-coordinate of where a click occurs in world units
   * @return boolean if the button has been pressed or not
   */
  public boolean isClicked(float x, float y) {
    return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
  }

  public Sprite getSprite() {
    return sprite;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
  }
}
