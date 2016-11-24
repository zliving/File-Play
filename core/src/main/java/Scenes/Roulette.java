package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.FilePlayMain;

/**
 * Created by Chris on 11/19/2016.
 */

public class Roulette extends BaseScreen{
  private TextureAtlas ringsAtlas;
  private Animation animation;
  private float elapsedTime = 0.0f;

  public Roulette(FilePlayMain mainGame){
    super(mainGame);
    bannerTextGlyphLayout = new GlyphLayout(bannerText, "Roulette");
    // Generate a new animation for the roulette wheel and import the ring atlas.
    ringsAtlas = new TextureAtlas(Gdx.files.internal("ring spritesheet.atlas"));
    animation = new Animation(1/15f, ringsAtlas.getRegions());
  }

  @Override
  public void render(float delta) {
    super.render(delta);
    stage.act(delta);
    stage.draw();
    // Keeps count of the time for the animation to use.
    elapsedTime += delta;
    spriteBatch.begin();
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
  }

  @Override
  protected void addAllListeners() {
  }

  @Override
  protected void createButtons() {
  }
}
