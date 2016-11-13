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
  private TextButton mediumButton;
  private TextButton hardButton;
  // Font for the labels "Category", "Difficulty", and "Number of Questions"
  private BitmapFont labelFont;
  // Spacing and offsets to draw with respect to text and buttons.
  private final int OFFSET = 10;
  private final int LABELX = 20;

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
    // Draws the text "Lobby" in the center of the banner.
    stage.act(delta);
    stage.draw();
    spriteBatch.begin();
    bannerText.draw(spriteBatch, bannerTextGlyphLayout, glyphCenterX, 770);
    labelFont.draw(spriteBatch, "Categories", LABELX, 690);
    labelFont.draw(spriteBatch, "Difficulty", LABELX, 470);
    spriteBatch.end();
  }

  /**
   * Creates all of the buttons that will be drawn to the screen.
   */
  @Override
  protected void createButtons() {
    backButton = new ButtonActor(new Texture(Gdx.files.internal("black-back-arrow.png")), 0, 735);
    TextButtonStyle style;
    style = setStyle("nano yellow", "nano yellow");
    playButton = new TextButton("Play", style);
    playButton.setPosition(220, 20);
    playButton.setHeight(50);
    playButton.setWidth(250);
    style = setStyle("nano blue", "nano blue pressed");
    generalButton = new TextButton("General", style);
    generalButton.setPosition(20, 600);
    style = setStyle("nano cyan", "nano cyan pressed");
    booksButton = new TextButton("Books", style);
    booksButton.setPosition(getButtonXOffset(generalButton, OFFSET), generalButton.getY());
    style = setStyle("nano green", "nano green pressed");
    filmButton = new TextButton("Film", style);
    filmButton.setPosition(getButtonXOffset(booksButton, OFFSET), generalButton.getY());
    style = setStyle("nano red", "nano red pressed");
    sportsButton = new TextButton("Sports", style);
    sportsButton.setPosition(getButtonXOffset(filmButton, OFFSET),
            generalButton.getY());
    style = setStyle("nano orange", "nano orange pressed");
    televisionButton = new TextButton("Television", style);
    televisionButton.setPosition(generalButton.getX(), getButtonYOffset(generalButton, OFFSET));
    style = setStyle("nano pink", "nano pink pressed");
    videoGamesButton = new TextButton("Video Games", style);
    videoGamesButton.setPosition(getButtonXOffset(televisionButton, OFFSET),
            getButtonYOffset(generalButton, OFFSET));
    style = setStyle("nano indigo", "nano indigo pressed");
    musicButton = new TextButton("Music", style);
    musicButton.setPosition(getButtonXOffset(videoGamesButton, OFFSET),
            getButtonYOffset(generalButton, OFFSET));

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
        mainGame.setScreen(FilePlayMain.ScreenType.MAINMENU);
        return true;
      }
    });
    playButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change to Play screen.
        mainGame.setScreen(FilePlayMain.ScreenType.PLAY);
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
  private TextButtonStyle setStyle(String up, String down) {
    TextButtonStyle style = new TextButtonStyle();
    // Sets the skin for when the button is not pressed and when it is. The argument that is passed
    // is searched for in the atlas within the buttonSkin object.
    style.up = buttonSkin.getDrawable(up);
    style.down = buttonSkin.getDrawable(down);
    style.font = generateNewFont("Rampung.ttf", 30, Color.BLACK);
    return style;
  }
}
