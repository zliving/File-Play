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

  public void disableButtons() {
    for(int i=0; i<questionSet.size; i++) {
      questionSet.get(i).setDisabled(true);
    }
  }

  // Check if there is an initial button pressed.
  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
    String correctAnswer = newTriviaGame.get(index).correctAnswer;
    String selectedAnswer = questionSet.get(buttonNumber).getText().toString();

    if(selectedAnswer.compareTo(correctAnswer) == 0 && !questionSet.get(buttonNumber).isDisabled()) {
      System.out.println("The answer is correct");
      disableButtons();
    } else if(!questionSet.get(buttonNumber).isDisabled()) {
      System.out.println("The answer is incorrect.");
      disableButtons();
    }


    return true;
  }


  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

  }

}
