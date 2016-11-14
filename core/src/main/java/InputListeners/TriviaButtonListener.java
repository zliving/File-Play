package InputListeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

import GameObjects.TriviaQuestion;
import GameObjects.TriviaScoreManager;
import UIElements.TriviaButtonBuilder;

/**
 * Created by zach on 10/24/16. TriviaButtonListener is a gesture listener specifically for trivia
 * buttons. When each button is clicked the listener will make calculations on each button.
 */

public class TriviaButtonListener extends InputListener {

  TriviaButtonBuilder triviaButtonBuilder;
  Array<TriviaQuestion> newTriviaGameQuestions;
  public Array<TextButton> questionSetButtons;
  private int index;
  private int buttonNumber;
  private TriviaScoreManager scoreManager;

  public TriviaButtonListener(TriviaButtonBuilder triviaButtonBuilder, int index, int buttonNumber,
                              TriviaScoreManager scoreManager) {
    this.triviaButtonBuilder = triviaButtonBuilder;
    this.newTriviaGameQuestions= triviaButtonBuilder.newTriviaGame;
    this.questionSetButtons = triviaButtonBuilder.questionSetButtons;
    this.index = index;
    this.buttonNumber = buttonNumber;
    this.scoreManager = scoreManager;
  }

  public void disableButtons() {
    for(int i=0; i<questionSetButtons.size; i++) {
      questionSetButtons.get(i).setDisabled(true);
    }
  }

  // Check if there is an initial button pressed.
  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
    String correctAnswer = newTriviaGameQuestions.get(index).correctAnswer;
    String selectedAnswer = questionSetButtons.get(buttonNumber).getText().toString();
    // Check if the answer is correct and there hasn't been a click registered yet.
    if(selectedAnswer.compareTo(correctAnswer) == 0 && !questionSetButtons.get(buttonNumber).isDisabled()) {
      scoreManager.setPlayerScore(10*(15-triviaButtonBuilder.timePassed));
      System.out.println("The answer is correct, current score is: " + scoreManager.getPlayerScore());
      disableButtons();
    } else if(!questionSetButtons.get(buttonNumber).isDisabled()) {
      System.out.println("The answer is incorrect.");
      System.out.println("The answer is incorrect.");
      disableButtons();
    }


    return true;
  }


  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

  }

}
