package UIElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button {
  private Sprite sprite;
  private float x, y, width, height;

  public Button(Sprite sprite, float x, float y, float width, float height) {
    this.sprite = sprite;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public Button(Texture t, float x, float y){
    sprite = new Sprite(t);
    sprite.setX(x);
    sprite.setY(y);
  }

  public boolean isClicked(float x, float y) {
    if (x >= this.x && x <= this.x + this.width) {
      if (y >= this.y && y <= this.y + this.height) {
        return true;
      }
    }
    return false;
  }
}
