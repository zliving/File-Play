package filemanagement;

import java.util.Random;

/**
 * The real implementation of RandomWrapper.
 * Note: This implementation should never be used in unit tests, as this implementation would
 * introduce nondeterministic elements and result in extremely flaky tests.
 */
public class RandomWrapperImpl implements RandomWrapper {
  private final Random random;

  public RandomWrapperImpl() {
    random = new Random();
  }

  @Override
  public int nextInt(int i) {
    return random.nextInt(i);
  }
}
