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
import GameObjects.TriviaScoreManager;
import InputListeners.TriviaButtonListener;


/**
 * Created by zach on 10/23/16. TriviaButtonBuilder will create sets of buttons for each trivia
 * question. Will return an array of buttons for each question. To update the screen you need to
 * call updateTriviaButton after each question is answered.
 */

public class TriviaButtonBuilder extends Game {

  // The stage is where we place the buttons since they
  // will be actors now rather than just textures.
  private Stage stage;
  // TextButton is the standard actor we will use to display any text on the buttons.
  // These buttons have a their own listeners and states
  TextButton button;
  // TextButtonStyle allows us to change the look of the button based on its state
  private TextButton.TextButtonStyle textButtonStyle;
  // Allows us to place a created font on the buttons
  private BitmapFont font;
  // The Skin allows us to dynamically change how the buttons look.
  private Skin skin;
  // This is how we will use textures as they will be in one file and we use
  // The atlas to get regions of the texture pack to place as single textures
  TextureAtlas buttonAtlas;
  String question;
  protected static final float WORLD_WIDTH = 480;
  protected static final float WORLD_HEIGHT = 800;
  private static final float xButtonPosition = 50;
  private float yButtonPosition = 400;
  private int questionNumber = 0;
  private TriviaButtonListener buttonListener;
  private TextButton questionButton;
  private Array<TextButton> newQuestionBlock;
  private TextButton correctAnswerButton;
  private TextButton incorrectAnswerButton;
  private Array<TextButton> incorrectButtons;
  public Array<TriviaQuestion> newTriviaGame;
  public Array<TextButton> questionSetButtons;
  private Array<TextButton> shuffleButtonBlock;
  private boolean timerIsOn = false;
  private int timing =0;
  public long startTime =0;
  public long timePassed;
  private TriviaScoreManager newGameScore;

  public TriviaButtonBuilder() {
    startTime = System.currentTimeMillis();
    this.create();
  }

  private Array<TriviaQuestion> generateTriviaData(String url) {

    // Get an array of questions from the database.
    TriviaQuestionBuilder newTriviaGame = new TriviaQuestionBuilder();
    url = "https://www.opentdb.com/api.php?amount=3&type=multiple";

    return newTriviaGame.getTriviaQuestions(url);
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
    newQuestionBlock = new Array<TextButton>();
    questionButton = new TextButton(currentQuestion.question, textButtonStyle);
    questionButton.setPosition(xButtonPosition, yButtonPosition);
      questionButton.getLabelCell().width(300);
      questionButton.getLabelCell().left();
      questionButton.getLabel().setWrap(true);
      questionButton.invalidate();
    yButtonPosition -= questionButton.getHeight()+50;
    correctAnswerButton = new TextButton(currentQuestion.correctAnswer, textButtonStyle);
    //correctAnswerButton.setPosition(xButtonPosition, yButtonPosition);
   // yButtonPosition -= questionButton.getHeight();

    // Push the question and correctAnswerButton onto the array.
    newQuestionBlock.add(questionButton);
    newQuestionBlock.add(correctAnswerButton);

    // Create a button for each incorrect answer.
    incorrectButtons = new Array<TextButton>();
    for (int i = 0; i < currentQuestion.incorrectAnswers.length; i++) {
      incorrectAnswerButton = new TextButton(currentQuestion.incorrectAnswers[i], textButtonStyle);
      //incorrectAnswerButton.setPosition(xButtonPosition, yButtonPosition);
      //yButtonPosition -= incorrectAnswerButton.getHeight();
      newQuestionBlock.add(incorrectAnswerButton);
    }
    // Shuffle all of the answers to each question.
    shuffleButtonBlock = new Array<TextButton>();
    shuffleButtonBlock.add(newQuestionBlock.first());
    newQuestionBlock.removeValue(newQuestionBlock.first(),false);
    int size = newQuestionBlock.size;
    for(int i=0; i<size; i++) {
      TextButton temp = newQuestionBlock.random();
      newQuestionBlock.removeValue(temp,false);
      shuffleButtonBlock.add(temp);
    }
    // Place each question below the previous.
    for(int i=1; i<shuffleButtonBlock.size; i++) {
      shuffleButtonBlock.get(i).setPosition(xButtonPosition,yButtonPosition);
      yButtonPosition -= shuffleButtonBlock.get(i).getHeight()+20;
    }

    yButtonPosition = 400;
    //button = new TextButton(question, textButtonStyle);
    //stage.addActor(button);
    return shuffleButtonBlock;
  }

  private void addListener () {
    // Add the custom buttonListener to each button.
    for (int i = 1; i < shuffleButtonBlock.size; i++) {
      buttonListener = new TriviaButtonListener(this, questionNumber, i, newGameScore);
      shuffleButtonBlock.get(i).addListener(buttonListener);
    }
  }

  private void updateTriviaButtons() {
    if(questionNumber < newTriviaGame.size-1) {
      ++questionNumber;
      stage.clear();
      questionSetButtons = generateButtons(newTriviaGame.get(questionNumber));
      addListener();
      for (int i = 0; i < questionSetButtons.size; i++) {
        stage.addActor(questionSetButtons.get(i));
      }
      Gdx.input.setInputProcessor(stage);
    } else {
      System.out.println("Games is over! Your score is: " + newGameScore.getPlayerScore());
    }

  }

  @Override
  public void create() {
    newGameScore = new TriviaScoreManager();
    stage = new Stage();
    // On the creation of the class build the trivia buttons and add them to the stage.
    newTriviaGame = generateTriviaData("");
    questionSetButtons = generateButtons(newTriviaGame.get(questionNumber));
    addListener();
    stage.clear();
    for (int i = 0; i < questionSetButtons.size; i++) {
      stage.addActor(questionSetButtons.get(i));
    }
    Gdx.input.setInputProcessor(stage);


  }

  @Override
  public void resize(int width, int height) {
  }

  @Override
  public void render() {
    // Count the time between each question that is presented.
    timePassed = (System.currentTimeMillis() - startTime) / 1000;
    //System.out.println("Time elapsed in seconds = " + timePassed);
    if(timePassed > 15 ) {
      startTime = System.currentTimeMillis();
      updateTriviaButtons();
    }
    super.render();
    stage.draw();
  }

  @Override
  public void dispose() {
    stage.dispose();
  }
}
