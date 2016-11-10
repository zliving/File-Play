package InputListeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

import GameObjects.TriviaQuestion;
import UIElements.TriviaButtonBuilder;

/**
 * Created by zach on 10/24/16. TriviaButtonListener is a gesture listener specifically for trivia
 * buttons. When each button is clicked the listener will make calculations on each button.
 */

public class TriviaButtonListener extends InputListener {

  TriviaButtonBuilder builderClass;
  Array<TriviaQuestion> newTriviaGame;
  public Array<TextButton> questionSet;
  private int index;
  private int buttonNumber;

  public TriviaButtonListener(TriviaButtonBuilder builderClass, int index, int buttonNumber) {
    this.builderClass = builderClass;
    this.newTriviaGame= builderClass.newTriviaGame;
    this.questionSet = builderClass.questionSet;
    this.index = index;
    this.buttonNumber = buttonNumber;
  }

  // Check if there is an initial button pressed.
  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
    Gdx.app.log("Button has been ", "Pressed");
    String correctAnswer = newTriviaGame.get(index).correctAnswer;
    String selectedAnswer = questionSet.get(buttonNumber).getText().toString();

    System.out.println("Correct Answer = "+correctAnswer);
    System.out.println("Selected Answer = "+selectedAnswer);
    if(selectedAnswer.compareTo(correctAnswer) == 0) {
      System.out.println("The Answer is correct");
      builderClass.updateTriviaButtons();

    }

    return true;
  }

  // When button is released calculations will be performed here.
  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
    Gdx.app.log("Button ahs been", "Released");
  }

}
