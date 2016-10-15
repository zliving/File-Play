package filemanagement;

import java.util.Random;

/**
 * The real implementation of RandomWrapper.
 * Note: This implementation should never be used in unit tests, as this implementation would
 * introduce nondeterministic properties and result in extremely flaky tests.
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
