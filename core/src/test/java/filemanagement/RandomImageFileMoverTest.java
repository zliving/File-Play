package filemanagement;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Random;

/**
 * Test for the RandomImageFileMover.
 */

public class RandomImageFileMoverTest {
  @Mock Random randomNumberGenerator;
  @Mock FileWrapperFactory fileFactory;
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  RandomImageFileMoverImpl randomImageFileMover;

  public RandomImageFileMoverTest() {
    randomNumberGenerator = new Random();
    fileFactory = Mockito.mock(FileWrapperFactory.class);
    randomImageFileMover = new RandomImageFileMoverImpl(fileFactory, randomNumberGenerator);
  }

  @Test
  public void hideRandom_dontHide() {
    // Return a number that won't trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(3);
    assertEquals(randomImageFileMover.hideRandom(), false);
  }
}
