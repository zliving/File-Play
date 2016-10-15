package filemanagement;

import java.util.Random;

/**
 * Created by James on 10/14/2016.
 */

public class RandomWrapperImpl implements RandomWrapper {
  Random random;

  public RandomWrapperImpl() {
    random = new Random();
  }

  public int nextInt(int i) {
    return random.nextInt(i);
  }
}
