package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.FilePlayMain;

import GameObjects.GameSettings;
import GameObjects.UrlBuilder;
import UIElements.ButtonActor;

/**
 * The lobby screen will be where users set their preferences for a game prior to searching for
 * one.
 */
public class Lobby extends BaseScreen {
  // This is for the back button on the banner to go back a screen.
  private ButtonActor backButton;
  // This is for the play button to move from the lobby to the play screen.
  private TextButton playButton;
  // Buttons for each of the categories.
  private TextButton generalButton;
  private TextButton geographyButton;
  private TextButton musicButton;
  private TextButton videoGamesButton;
  private TextButton scienceNatureButton;
  private TextButton historyButton;
  // Buttons for each of the difficulties.
  private TextButton easyButton;
  private TextButton mediumDifficultyButton;
  private TextButton hardButton;
  // Buttons for the game length.
  private TextButton shortButton;
  private TextButton mediumLengthButton;
  private TextButton longButton;
  // Font for the labels "Category", "Difficulty", and "Game Length"
  private BitmapFont labelFont;
  // Spacing and offsets to draw with respect to text and buttons.
  private static final int OFFSET = 10;
  private static final int LABEL_X = 20;
  // References to the buttons that are currently pressed.
  private TextButton pressedCategory;
  private TextButton pressedDifficulty;
  private TextButton pressedLength;
  // Text for each of the buttons.
  public static final String GENERAL_TEXT = "General";
  public static final String GEOGRAPHY_TEXT = "Geography";
  public static final String MUSIC_TEXT = "Music";
  public static final String VIDEOGAMES_TEXT = "Video Games";
  public static final String SCIENCE_NATURE_TEXT = "Science and Nature";
  public static final String HISTORY_TEXT = "History";
  public static final String EASY_TEXT = "Easy";
  public static final String NORMAL_TEXT = "Normal";
  public static final String HARD_TEXT = "Hard";
  public static final String SHORT_TEXT = "Short (5)";
  public static final String MEDIUM_TEXT = "Medium (7)";
  public static final String LONG_TEXT = "Long (9)";
  // This will store the preferences so that the Url can be built properly.
  private GameSettings settings;

  /**
   * Refer to MainMenu.java for comments regarding each section. Lobby should operate in the same
   * way with changes to the textures/sprites that must be drawn to the screen.
   */
  public Lobby(FilePlayMain mainGame) {
    super(mainGame);
    // Creates GlyphLayout to get width for centering text in the banner.
    bannerTextGlyphLayout = new GlyphLayout(bannerText, "Lobby");
    // Calculate the center for the text to be drawn in the banner.
    glyphCenterX = ((int) WORLD_WIDTH - (int) bannerTextGlyphLayout.width) / 2;
    labelFont = generateNewFont("BROADSolid.ttf", 36, Color.WHITE);
    createButtons();
    addAllListeners();
    addAllActors();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    stage.act(delta);
    stage.draw();
    spriteBatch.begin();
    // Draws the text "Lobby" in the center of the banner.
    bannerText.draw(spriteBatch, bannerTextGlyphLayout, glyphCenterX, 770);
    // Draws all the labels for each section.
    labelFont.draw(spriteBatch, "Categories", LABEL_X, 690);
    labelFont.draw(spriteBatch, "Difficulty", LABEL_X, 470);
    labelFont.draw(spriteBatch, "Game Length", LABEL_X, 300);
    spriteBatch.end();
  }

  @Override
  public void dispose(){
    labelFont.dispose();
  }

  /**
   * Creates all of the buttons that will be drawn to the screen.
   */
  @Override
  protected void createButtons() {
    backButton = new ButtonActor(new Texture(Gdx.files.internal("black-back-arrow.png")), 0, 735);

    playButton = new TextButton("Play", createStyle("nano yellow", "nano yellow"));
    playButton.setPosition(220, 20);
    playButton.setHeight(50);
    playButton.setWidth(250);

    generalButton = new TextButton(GENERAL_TEXT, createStyle("nano blue", "nano blue pressed"));
    generalButton.setPosition(20, 600);

    videoGamesButton = new TextButton(VIDEOGAMES_TEXT,
                                      createStyle("nano pink", "nano pink pressed"));
    videoGamesButton.setPosition(getButtonXOffset(generalButton, OFFSET), generalButton.getY());

    scienceNatureButton = new TextButton(SCIENCE_NATURE_TEXT,
                                         createStyle("nano indigo", "nano indigo pressed"));
    scienceNatureButton.setPosition(generalButton.getX(), getButtonYOffset(generalButton, OFFSET));

    historyButton = new TextButton(HISTORY_TEXT, createStyle("nano cyan", "nano cyan pressed"));
    historyButton.setPosition(getButtonXOffset(scienceNatureButton, OFFSET),
                              scienceNatureButton.getY());

    musicButton = new TextButton(MUSIC_TEXT, createStyle("nano orange", "nano orange pressed"));
    musicButton.setPosition(generalButton.getX(), getButtonYOffset(scienceNatureButton, OFFSET));

    geographyButton = new TextButton(GEOGRAPHY_TEXT,
                                     createStyle("nano green", "nano green pressed"));
    geographyButton.setPosition(getButtonXOffset(musicButton, OFFSET), musicButton.getY());

    easyButton = new TextButton(EASY_TEXT, createStyle("nano green", "nano green pressed"));
    easyButton.setPosition(generalButton.getX(), 380);

    mediumDifficultyButton = new TextButton(NORMAL_TEXT,
                                            createStyle("nano yellow", "nano yellow pressed"));
    mediumDifficultyButton.setPosition(getButtonXOffset(easyButton, OFFSET), easyButton.getY());

    hardButton = new TextButton(HARD_TEXT, createStyle("nano red", "nano red pressed"));
    hardButton.setPosition(getButtonXOffset(mediumDifficultyButton, OFFSET), easyButton.getY());

    shortButton = new TextButton(SHORT_TEXT, createStyle("nano cyan", "nano cyan pressed"));
    shortButton.setPosition(generalButton.getX(), 210);

    mediumLengthButton = new TextButton(MEDIUM_TEXT,
                                        createStyle("nano indigo", "nano indigo pressed"));
    mediumLengthButton.setPosition(getButtonXOffset(shortButton, OFFSET), shortButton.getY());

    longButton = new TextButton(LONG_TEXT, createStyle("nano orange", "nano orange pressed"));
    longButton.setPosition(getButtonXOffset(mediumLengthButton, OFFSET), shortButton.getY());
  }

  /**
   * This adds listeners to each of the buttons along with what to do upon being touched. A new
   * InputListener is added inline overriding the touchDown method to determine what to do when
   * touched.
   */
  @Override
  protected void addAllListeners() {
    backButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change back to main menu.
        mainGame.setScreen(new MainMenu(mainGame));
        return true;
      }
    });
    playButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change to Play screen.
        if (pressedCategory == null || pressedDifficulty == null || pressedLength == null) {
          System.out.println("One of the preferences has not been selected");
        } else {
          settings = new GameSettings(pressedLength.getText().toString(),
                                      pressedCategory.getText().toString(),
                                      pressedDifficulty.getText().toString());
          mainGame.setScreen(new Play(mainGame));
        }
        return true;
      }
    });
    generalButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = generalButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = generalButton;
        }
        return true;
      }
    });
    historyButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = historyButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = historyButton;
        }
        return true;
      }
    });
    scienceNatureButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = scienceNatureButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = scienceNatureButton;
        }
        return true;
      }
    });
    geographyButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = geographyButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = geographyButton;
        }
        return true;
      }
    });
    videoGamesButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = videoGamesButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = videoGamesButton;
        }
        return true;
      }
    });
    musicButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = musicButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = musicButton;
        }
        return true;
      }
    });
    easyButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedDifficulty == null) {
          pressedDifficulty = easyButton;
        } else {
          pressedDifficulty.setChecked(false);
          pressedDifficulty = easyButton;
        }
        return true;
      }
    });
    mediumDifficultyButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedDifficulty == null) {
          pressedDifficulty = mediumDifficultyButton;
        } else {
          pressedDifficulty.setChecked(false);
          pressedDifficulty = mediumDifficultyButton;
        }
        return true;
      }
    });
    hardButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedDifficulty == null) {
          pressedDifficulty = hardButton;
        } else {
          pressedDifficulty.setChecked(false);
          pressedDifficulty = hardButton;
        }
        return true;
      }
    });
    shortButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedLength == null) {
          pressedLength = shortButton;
        } else {
          pressedLength.setChecked(false);
          pressedLength = shortButton;
        }
        return true;
      }
    });
    mediumLengthButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedLength == null) {
          pressedLength = mediumLengthButton;
        } else {
          pressedLength.setChecked(false);
          pressedLength = mediumLengthButton;
        }
        return true;
      }
    });
    longButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedLength == null) {
          pressedLength = longButton;
        } else {
          pressedLength.setChecked(false);
          pressedLength = longButton;
        }
        return true;
      }
    });
  }

  /**
   * Adds all of the buttons to the stage so that they are drawn to the screen.
   */
  @Override
  protected void addAllActors() {
    stage.addActor(backButton);
    stage.addActor(playButton);
    stage.addActor(generalButton);
    stage.addActor(geographyButton);
    stage.addActor(historyButton);
    stage.addActor(scienceNatureButton);
    stage.addActor(musicButton);
    stage.addActor(videoGamesButton);
    stage.addActor(easyButton);
    stage.addActor(mediumDifficultyButton);
    stage.addActor(hardButton);
    stage.addActor(shortButton);
    stage.addActor(mediumLengthButton);
    stage.addActor(longButton);
  }

  /**
   * Sets the style for when the button is not pressed and when it is using the atlas to find which
   * textures to use corresponding to the strings 'up' and 'down'. The style font is the font used
   * for text within buttons and can be changed to whatever is preffered.
   *
   * @param up   string corresponding to the atlas texture to use for when the button is not
   *             pressed.
   * @param down string corresponding to the atlas texture to use for when the button is pressed.
   * @return a style with the corresponding 'up' and 'down' textures used for the button and the
   * font that is set within the method.
   */
  private TextButtonStyle createStyle(String up, String down) {
    TextButtonStyle style = new TextButtonStyle();
    // Sets the skin for when the button is not pressed and when it is. The argument that is passed
    // is searched for in the atlas within the buttonSkin object.
    style.up = buttonSkin.getDrawable(up);
    style.down = buttonSkin.getDrawable(down);
    style.checked = buttonSkin.getDrawable(down);
    style.font = generateNewFont("Rampung.ttf", 30, Color.BLACK);
    return style;
  }
}
