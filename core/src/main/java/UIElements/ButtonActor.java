package UIElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * The button class extends the LibGDX actor class which allows it to be modified and added to a
 * stage.
 */
public class ButtonActor extends Actor{
  Texture texture;
  public ButtonActor(Texture t, float x, float y){
    this.texture = t;
    this.setX(x);
    this.setY(y);
  }

  @Override
  public void draw(Batch batch, float parentAlpha){
    batch.draw(texture, this.getX(), this.getY());
  }

}
