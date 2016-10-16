package UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button {
  private Sprite sprite;
  private float x, y, width, height;
  private final float WORLD_WIDTH = 480;
  private final float WORLD_HEIGHT = 800;
  // TODO (Chris): Think of a better name for this class. This is pretty much a wrapper for
  // sprites/textures but allows for touch calculations in regards to world units.

  // The button constructor can take in a Sprite and two floats which should correspond to
  //  the world units of where the object should be drawn.
  public Button(Sprite sprite, float x, float y) {
    this.sprite = sprite;
    // x and y should be given in world units
    this.x = x;
    this.y = y;
    // This sets the width and height in world units
    this.width = sprite.getWidth() * WORLD_WIDTH / Gdx.graphics.getWidth();
    this.height = sprite.getHeight() * WORLD_HEIGHT / Gdx.graphics.getHeight();
  }

  // This is an overloaded constructor which takes in a texture and two floats which correspond
  // to the world units of where the object should be drawn. It simply makes a Sprite of the
  // texture that is passed in and then prooceeds to do the same as the first constructor.
  //
  public Button(Texture t, float x, float y) {
    sprite = new Sprite(t);
    this.x = x;
    this.y = y;
    this.width = sprite.getWidth() * WORLD_WIDTH / Gdx.graphics.getWidth();
    this.height = sprite.getHeight() * WORLD_HEIGHT / Gdx.graphics.getHeight();
  }

  // This method returns true if the button that called it has been pressed. The x and y
  // parameters should correspond to the world units of where a click occurred and it assumes that
  // the origin (0, 0) is in the lower left hand corner.
  public boolean isClicked(float x, float y) {
    if (x >= this.x && x <= this.x + this.width) {
      if (y >= this.y && y <= this.y + this.height) {
        return true;
      }
    }
    return false;
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
