package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.FilePlayMain;

import UIElements.ButtonActor;

/**
 * Created by Chris on 11/19/2016.
 */

public class Roulette extends BaseScreen {
  private ButtonActor backButton;
  private ImageButtonStyle imageButtonStyle;
  private ImageButton skullButton;
  private TextureRegionDrawable skullDrawable;
  private TextButton safeButton;
  private TextButtonStyle safeButtonStyle;
  private TextureAtlas ringsAtlas;
  private Animation animation;
  private float elapsedTime = 0.0f;
  private static float BUTTON_HEIGHT = 87.0f;
  private static float BUTTON_WIDTH = 180.0f;
  private static float BUTTON_OFFSET = 50.0f;

  public Roulette(FilePlayMain mainGame) {
    super(mainGame);
    bannerTextGlyphLayout = new GlyphLayout(bannerText, "Roulette");
    // Calculate the center for the text to be drawn in the banner.
    glyphCenterX = ((int) WORLD_WIDTH - (int) bannerTextGlyphLayout.width) / 2;
    // Generate a new animation for the roulette wheel and import the ring atlas.
    ringsAtlas = new TextureAtlas(Gdx.files.internal("ring spritesheet.atlas"));
    animation = new Animation(1 / 15f, ringsAtlas.getRegions());
    createButtons();
    addAllListeners();
    addAllActors();
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    stage.act(delta);
    stage.draw();
    // Keeps count of the time for the animation to use.
    elapsedTime += delta;
    spriteBatch.begin();
    // Draws the text "Roulette" in the center of the banner.
    bannerText.draw(spriteBatch, bannerTextGlyphLayout, glyphCenterX, 770);
    // Draws and cycles each sprite in the atlas at the center-ish of the screen.
    spriteBatch.draw(animation.getKeyFrame(elapsedTime, true), 140, WORLD_HEIGHT / 2);
    spriteBatch.end();
  }

  @Override
  public void dispose() {
    super.dispose();
    ringsAtlas.dispose();
  }

  @Override
  protected void addAllActors() {
    stage.addActor(backButton);
    stage.addActor(skullButton);
    stage.addActor(safeButton);
  }

  @Override
  protected void addAllListeners() {
    backButton.addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        // Change back to main menu.
        mainGame.setScreen(new MainMenu(mainGame));
        return true;
      }
    });
  }

  @Override
  protected void createButtons() {
    backButton = new ButtonActor(new Texture(Gdx.files.internal("black-back-arrow.png")), 0, 735);

    // Creates style for the safe button
    safeButtonStyle = new TextButtonStyle();
    safeButtonStyle.up = buttonSkin.getDrawable("nano green");
    safeButtonStyle.down = buttonSkin.getDrawable("nano green pressed");
    safeButtonStyle.checked = buttonSkin.getDrawable("nano green pressed");
    safeButtonStyle.font = generateNewFont("Rampung.ttf", 30, Color.BLACK);
    safeButton = new TextButton("Safe", safeButtonStyle);
    safeButton.setWidth(BUTTON_WIDTH);
    safeButton.setHeight(BUTTON_HEIGHT);
    safeButton.setPosition(50, 250);

    // Create a drawable from the skull image.
    skullDrawable = new TextureRegionDrawable(buttonAtlas.findRegion("skull"));
    // Create a style for when the image button is pressed and not pressed.
    imageButtonStyle = new ImageButtonStyle(buttonSkin.getDrawable("nano red"),
                                            buttonSkin.getDrawable("nano red pressed"),
                                            buttonSkin.getDrawable("nano red pressed"),
                                            skullDrawable, skullDrawable, skullDrawable);
    skullButton = new ImageButton(imageButtonStyle);
    skullButton.setWidth(BUTTON_WIDTH);
    skullButton.setHeight(BUTTON_HEIGHT);
    skullButton.setPosition(getButtonXOffset(safeButton, BUTTON_OFFSET), safeButton.getY());
  }
}
