package UIElements;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;


import GameObjects.TriviaQuestion;
import GameObjects.TriviaQuestionBuilder;
import InputListeners.TriviaButtonListener;


/**
 * Created by zach on 10/23/16. TriviaButtonBuilder will create sets of buttons for each trivia
 * question. Will return an array of buttons for each question. To update the screen you need to
 * call updateTriviaButton after each question is answered.
 */

public class TriviaButtonBuilder extends Game {

  // The stage is where we place the buttons since they
  // will be actors now rather than just textures.
  Stage stage;
  // TextButton is the standard actor we will use to display any text on the buttons.
  // These buttons have a their own listeners and states
  TextButton button;
  // TextButtonStyle allows us to change the look of the button based on its state
  TextButton.TextButtonStyle textButtonStyle;
  // Allows us to place a created font on the buttons
  BitmapFont font;
  // The Skin allows us to dynamically change how the buttons look.
  Skin skin;
  // This is how we will use textures as they will be in one file and we use
  // The atlas to get regions of the texture pack to place as single textures
  TextureAtlas buttonAtlas;
  String question;
  float xButtonPosition = 50;
  float yButtonPosition = 400;
  int questionNumber = 0;
  TriviaButtonListener buttonListener = new TriviaButtonListener();


  private Array<TriviaQuestion> generateTriviaData(String url) {

    // Get an array of questions from the database.
    TriviaQuestionBuilder newTriviaGame = new TriviaQuestionBuilder();
    url = "https://www.opentdb.com/api.php?amount=10&type=multiple";
    Array<TriviaQuestion> newQuestionSet = newTriviaGame.getTriviaQuestions(url);

    return newQuestionSet;
  }

  private Array<TextButton> generateButtons(TriviaQuestion currentQuestion) {
    font = new BitmapFont();
    skin = new Skin();
    //buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
    //skin.addRegions(buttonAtlas);
    textButtonStyle = new TextButton.TextButtonStyle();
    textButtonStyle.font = font;
    //textButtonStyle.up = skin.getDrawable("up-button");
    //textButtonStyle.down = skin.getDrawable("down-button");
    //textButtonStyle.checked = skin.getDrawable("checked-button");
    //button = new TextButton("Button1", textButtonStyle);

    // Generate new array of TextButtons for each question.
    // Build static buttons for the correct answer and question.
    Array<TextButton> newQuestionBlock = new Array<TextButton>();
    TextButton questionButton = new TextButton(currentQuestion.question, textButtonStyle);
    questionButton.setPosition(xButtonPosition, yButtonPosition);
    yButtonPosition -= questionButton.getHeight();
    TextButton correctAnswerButton = new TextButton(currentQuestion.correctAnswer, textButtonStyle);
    correctAnswerButton.setPosition(xButtonPosition, yButtonPosition);
    yButtonPosition -= questionButton.getHeight();

    // Push the question and correctAnswerButton onto the array.
    newQuestionBlock.add(questionButton);
    newQuestionBlock.add(correctAnswerButton);

    // Create a button for each incorrect answer.
    TextButton incorrectAnswerButton;
    Array<TextButton> incorrectButtons = new Array<TextButton>();
    for (int i = 0; i < currentQuestion.incorrectAnswers.length; i++) {
      incorrectAnswerButton = new TextButton(currentQuestion.incorrectAnswers[i], textButtonStyle);
      incorrectAnswerButton.setPosition(xButtonPosition, yButtonPosition);
      yButtonPosition -= incorrectAnswerButton.getHeight();
      newQuestionBlock.add(incorrectAnswerButton);
    }

    // Add the custom buttonListener to each button.
    for (int i = 0; i < newQuestionBlock.size; i++) {
      newQuestionBlock.get(i).addListener(buttonListener);
    }


    //button = new TextButton(question, textButtonStyle);
    //stage.addActor(button);
    return newQuestionBlock;
  }

  private Array<TextButton> updateTriviaButtons(Array<TextButton> questionSet, TriviaQuestion newQuestion) {

    return questionSet;
  }

  @Override
  public void create() {
    stage = new Stage();

    // On the creation of the class build the trivia buttons and add them to the stage.
    Array<TriviaQuestion> newTriviaGame = generateTriviaData("");

    Array<TextButton> questionSet = generateButtons(newTriviaGame.get(questionNumber));
    for (int i = 0; i < questionSet.size; i++) {
      stage.addActor(questionSet.get(i));
    }
    Gdx.input.setInputProcessor(stage);


  }

  @Override
  public void resize(int width, int height) {
  }

  @Override
  public void render() {
    super.render();
    stage.draw();
  }

  @Override
  public void dispose() {
    stage.dispose();
  }
}
