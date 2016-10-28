package UIElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * The button class extends the LibGDX actor class which allows it to be modified and added to a
 * stage.
 */
public class ButtonActor extends Actor{
  Sprite sprite;
  public ButtonActor(Texture t, float x, float y){
    sprite = new Sprite(t);
    // Sets the location of where to draw the sprite.
    sprite.setX(x);
    sprite.setY(y);
    // The bounds for the actor must be set so that user touch can be registered correctly.
    setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    // Allows the button to be touched.
    setTouchable(Touchable.enabled);
    addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        System.out.println("You touched me...");
        return true;
      }
    });
  }

  @Override
  public void draw(Batch batch, float parentAlpha){
    sprite.draw(batch, parentAlpha);
  }

}
