package UIElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button {
  private Sprite sprite;
  private float x, y, width, height;

  public Button(Sprite sprite, float x, float y) {
    this.sprite = sprite;
    this.x = x;
    this.y = y;
    sprite.setPosition(x, y);
    this.width = sprite.getWidth();
    this.height = sprite.getHeight();
  }

  public Button(Texture t, float x, float y) {
    sprite = new Sprite(t);
    sprite.setPosition(x, y);
    this.x = x;
    this.y = y;
    this.width = sprite.getWidth();
    this.height = sprite.getHeight();
  }

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
