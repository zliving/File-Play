package GameObjects;

/**
 * Created by zach on 11/14/16.
 * TriviaScoreManager keeps track of the players score while playing a trivia round.
 * It also will call the end game logic after scores are tallied.
 */

public class TriviaScoreManager {
  private int playerScore;


  public void setPlayerScore(int addedScore) {
    playerScore += addedScore;
  }

  public int getPlayerScore() {
    return playerScore;
  }

}