package UIElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * The button class extends the LibGDX actor class which allows it to be modified and added to a
 * stage.
 */
public class ButtonActor extends Actor {
  Sprite sprite;

  /**
   * Constructor to create a new button actor so that it can be added to the stage.
   *
   * @param t a texture of what to draw for the button being created.
   * @param x the x coordinate of where to draw the button.
   * @param y the y coordinate of where to draw the button.
   */
  public ButtonActor(Texture t, float x, float y) {
    // Create a sprite with the given texture
    sprite = new Sprite(t);
    // Sets the location of where to draw the sprite.
    sprite.setX(x);
    sprite.setY(y);
    // The bounds for the actor must be set so that user touch can be registered correctly.
    setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    // Allows the button to be touched.
    setTouchable(Touchable.enabled);
  }

  /**
   * draw is called when LibGDX's stage.draw() method is called.
   *
   * @param batch       the batch to draw to. When stage is created the batch that is passed in is
   *                    used.
   * @param parentAlpha the opacity of the sprite to be drawn.
   */
  @Override
  public void draw(Batch batch, float parentAlpha) {
    sprite.draw(batch, parentAlpha);
  }
}
