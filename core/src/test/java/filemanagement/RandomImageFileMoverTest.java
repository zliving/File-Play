package filemanagement;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

  private class MockFileWrapper implements FileWrapper{
    public String name;
    public String path;

    @Override
    public String getFileName() { return name; }

    @Override
    public String getFilePath() { return path; }

    public MockFileWrapper[] getFileList() { return new MockFileWrapper[0]; }

    public Boolean move(FileWrapper dst) { return true; }
  }

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

  @Test
  public void hideRandom_emptyGallery() {
    MockFileWrapper mockGalleryFolder = Mockito.mock(MockFileWrapper.class);
    mockGalleryFolder.name = "gallery";
    mockGalleryFolder.path = "gallery";

    MockFileWrapper mockHiddenFolder = Mockito.mock(MockFileWrapper.class);
    mockHiddenFolder.name = ".file-play";
    mockHiddenFolder.path = "gallery/.file-play";

    MockFileWrapper[] mockFileWrappers = new MockFileWrapper[0];

    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    when(fileFactory.createFile(mockHiddenFolder.path)).thenReturn(mockHiddenFolder);
    when(mockGalleryFolder.getFileList()).thenReturn(mockFileWrappers);

    assertFalse(randomImageFileMover.hideRandom());
  }
/*
  @Test
  public void hideRandom_hiddenFolderSelected() {
    MockFileWrapper mockGalleryFolder = Mockito.mock(MockFileWrapper.class);
    mockGalleryFolder.name = "gallery";
    mockGalleryFolder.path = "gallery";

    MockFileWrapper mockHiddenFolder = Mockito.mock(MockFileWrapper.class);
    mockHiddenFolder.name = ".file-play";
    mockHiddenFolder.path = "gallery/.file-play";

    MockFileWrapper[] mockFileWrappers = new MockFileWrapper[3];
    mockFileWrappers[0] = mockHiddenFolder;
    mockFileWrappers[1] = Mockito.mock(MockFileWrapper.class);
    mockFileWrappers[2] = Mockito.mock(MockFileWrapper.class);

    mockFileWrappers[1].name = "file1";
    mockFileWrappers[1].path = "gallery/file1";
    mockFileWrappers[2].name = "file2";
    mockFileWrappers[2].path = "gallery/file2";

    MockFileWrapper moveDestinationFile = Mockito.mock(MockFileWrapper.class);
    moveDestinationFile.name = "file1";
    moveDestinationFile.path = "gallery/.file-play/file1";

    when(mockFileWrappers[1].move(moveDestinationFile)).thenReturn(true);
    when(fileFactory.createFile("gallery/.file-play/file1")).thenReturn(moveDestinationFile);
    when(randomNumberGenerator.nextInt(3)).thenReturn(1);  // Select a different file.
    when(randomNumberGenerator.nextInt(3)).thenReturn(0);  // Select the hidden folder.
    when(mockGalleryFolder.getFileList()).thenReturn(mockFileWrappers);
    when(fileFactory.createFile(mockHiddenFolder.path)).thenReturn(mockHiddenFolder);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);

    assertTrue(randomImageFileMover.hideRandom());
  }
*/
}
