package Scenes;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.FilePlayMain;

/**
 * Created by Chris on 11/19/2016.
 */

public class Roulette extends BaseScreen{
  public Roulette(FilePlayMain mainGame){
    super(mainGame);
    bannerTextGlyphLayout = new GlyphLayout(bannerText, "Roulette");

  }

  @Override
  public void render(float delta) {
    super.render(delta);
  }

  @Override
  public void dispose() {
    super.dispose();
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
