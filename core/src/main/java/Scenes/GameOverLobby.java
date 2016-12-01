package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.FilePlayMain;

/**
 * Created by zach on 11/14/16.
 */

public class GameOverLobby extends BaseScreen {

  public GameOverLobby(FilePlayMain mainGame, int score) {
    super(mainGame);
    // Creates GlyphLayout to get width for centering text in the banner.
    bannerTextGlyphLayout = new GlyphLayout(bannerText, "Game Over");
    // Calculate the center for the text to be drawn in the banner.
    glyphCenterX = ((int) WORLD_WIDTH - (int) bannerTextGlyphLayout.width) / 2;
    createButtons();
    addAllListeners();
    addAllActors();
  }
  @Override
  protected void addAllActors() {

  }

  @Override
  protected void addAllListeners() {

  }

  @Override
  protected void createButtons() {

  }
}
