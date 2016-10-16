package UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button {
  private Sprite sprite;
  private float x, y, width, height;
  private final float WORLD_WIDTH = 480;
  private final float WORLD_HEIGHT = 800;

  public Button(Sprite sprite, float x, float y) {
    this.sprite = sprite;
    // x and y should be given in world units
    this.x = x;
    this.y = y;
    // This sets the width and height in world units
    this.width = sprite.getWidth() * WORLD_WIDTH / Gdx.graphics.getWidth();
    this.height = sprite.getHeight() * WORLD_HEIGHT / Gdx.graphics.getHeight();
  }

  public Button(Texture t, float x, float y) {
    sprite = new Sprite(t);
    this.x = x;
    this.y = y;
    this.width = sprite.getWidth() * WORLD_WIDTH / Gdx.graphics.getWidth();
    this.height = sprite.getHeight() * WORLD_HEIGHT / Gdx.graphics.getHeight();
  }

  public boolean isClicked(float x, float y) {

    if (x >= this.x && x <= this.x + this.width) {
//      System.out.println("x is in range");
//      System.out.println("checking y: " + y);
//      System.out.println("by is: " + this.y);
//      System.out.println("by width is: " + this.width);
//      System.out.println("by height is: " + this.height);
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