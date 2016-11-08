package InputListeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by zach on 10/24/16.
 * TriviaButtonListener is a gesture listener specifically for trivia buttons.
 * When each button is clicked the listener will make calculations on each button.
 */

public class TriviaButtonListener extends InputListener {

    // Check if there is an initial button pressed.
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log("Button has been ", "Pressed");
        return true;
    }

    // When button is released calculations will be performed here.
    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log("Button ahs been", "Released");
    }

}
