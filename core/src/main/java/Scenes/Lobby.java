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
  private TextButton booksButton;
  private TextButton filmButton;
  private TextButton musicButton;
  private TextButton televisionButton;
  private TextButton videoGamesButton;
  private TextButton sportsButton;
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
  private static final int LABELX = 20;
  // References to the buttons that are currently pressed.
  private TextButton pressedCategory;
  private TextButton pressedDifficulty;
  private TextButton pressedLength;

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
    labelFont.draw(spriteBatch, "Categories", LABELX, 690);
    labelFont.draw(spriteBatch, "Difficulty", LABELX, 470);
    labelFont.draw(spriteBatch, "Game Length", LABELX, 300);
    spriteBatch.end();
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

    generalButton = new TextButton("General", createStyle("nano blue", "nano blue pressed"));
    generalButton.setPosition(20, 600);

    booksButton = new TextButton("Books", createStyle("nano cyan", "nano cyan pressed"));
    booksButton.setPosition(getButtonXOffset(generalButton, OFFSET), generalButton.getY());

    filmButton = new TextButton("Film", createStyle("nano green", "nano green pressed"));
    filmButton.setPosition(getButtonXOffset(booksButton, OFFSET), generalButton.getY());

    sportsButton = new TextButton("Sports", createStyle("nano red", "nano red pressed"));
    sportsButton.setPosition(getButtonXOffset(filmButton, OFFSET),
                             generalButton.getY());
    televisionButton = new TextButton("Television", createStyle("nano orange", "nano orange pressed"));
    televisionButton.setPosition(generalButton.getX(), getButtonYOffset(generalButton, OFFSET));

    videoGamesButton = new TextButton("Video Games", createStyle("nano pink", "nano pink pressed"));
    videoGamesButton.setPosition(getButtonXOffset(televisionButton, OFFSET),
                                 getButtonYOffset(generalButton, OFFSET));
    musicButton = new TextButton("Music", createStyle("nano indigo", "nano indigo pressed"));
    musicButton.setPosition(getButtonXOffset(videoGamesButton, OFFSET),
                            getButtonYOffset(generalButton, OFFSET));

    easyButton = new TextButton("Easy", createStyle("nano green", "nano green pressed"));
    easyButton.setPosition(generalButton.getX(), 380);

    mediumDifficultyButton = new TextButton("Medium", createStyle("nano yellow", "nano yellow pressed"));
    mediumDifficultyButton.setPosition(getButtonXOffset(easyButton, OFFSET), easyButton.getY());

    hardButton = new TextButton("Hard", createStyle("nano red", "nano red pressed"));
    hardButton.setPosition(getButtonXOffset(mediumDifficultyButton, OFFSET), easyButton.getY());

    shortButton = new TextButton("Short (5)", createStyle("nano cyan", "nano cyan pressed"));
    shortButton.setPosition(generalButton.getX(), 210);

    mediumLengthButton = new TextButton("Medium (7)", createStyle("nano indigo", "nano indigo pressed"));
    mediumLengthButton.setPosition(getButtonXOffset(shortButton, OFFSET), shortButton.getY());

    longButton = new TextButton("Long (9)", createStyle("nano orange", "nano orange pressed"));
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
    booksButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = booksButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = booksButton;
        }
        return true;
      }
    });
    filmButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = filmButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = filmButton;
        }
        return true;
      }
    });
    sportsButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = sportsButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = sportsButton;
        }
        return true;
      }
    });
    televisionButton.addListener(new InputListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pressedCategory == null) {
          pressedCategory = televisionButton;
        } else {
          pressedCategory.setChecked(false);
          pressedCategory = televisionButton;
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
    stage.addActor(booksButton);
    stage.addActor(filmButton);
    stage.addActor(musicButton);
    stage.addActor(televisionButton);
    stage.addActor(videoGamesButton);
    stage.addActor(sportsButton);
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
