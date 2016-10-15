package filemanagement;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Random;

/**
 * Test for the RandomImageFileMover.
 */
@RunWith(MockitoJUnitRunner.class)
public class RandomImageFileMoverTest {
  @Mock
  RandomWrapper randomNumberGenerator;
  @Mock
  FileWrapperFactory fileFactory;

  @InjectMocks
  RandomImageFileMoverImpl randomImageFileMover;

  private class MockFileWrapper implements FileWrapper {
    public String name;
    public String path;

    @Override
    public String getFileName() {
      return name;
    }

    @Override
    public String getFilePath() {
      return path;
    }

    public MockFileWrapper[] getFileList() {
      return new MockFileWrapper[0];
    }

    public Boolean move(FileWrapper dst) {
      return true;
    }
  }

  private void setUp() {
    randomNumberGenerator = Mockito.mock(RandomWrapper.class);
    fileFactory = Mockito.mock(FileWrapperFactory.class);
    randomImageFileMover = new RandomImageFileMoverImpl(fileFactory, randomNumberGenerator);
  }

  @Test
  public void hideRandom_dontMove() {
    // Return a number that won't trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(3);
    assertEquals(randomImageFileMover.hideRandom(), false);
  }

  @Test
  public void hideRandom_doMove() {
    MockFileWrapper mockGalleryFolder = Mockito.mock(MockFileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    MockFileWrapper mockHiddenFolder = Mockito.mock(MockFileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    MockFileWrapper[] mockFileWrappers = new MockFileWrapper[3];
    mockFileWrappers[0] = mockHiddenFolder;
    mockFileWrappers[1] = Mockito.mock(MockFileWrapper.class);
    mockFileWrappers[2] = Mockito.mock(MockFileWrapper.class);

    when(mockFileWrappers[1].getFileName()).thenReturn("file1");
    when(mockFileWrappers[1].getFilePath()).thenReturn("gallery/file1");
    when(mockFileWrappers[2].getFileName()).thenReturn("file2");
    when(mockFileWrappers[2].getFilePath()).thenReturn("gallery/file2");

    MockFileWrapper moveDestinationFile = Mockito.mock(MockFileWrapper.class);
    when(moveDestinationFile.getFileName()).thenReturn("file1");
    when(moveDestinationFile.getFilePath()).thenReturn("gallery/.file-play/file1");
    moveDestinationFile.name = "file1";
    moveDestinationFile.path = "gallery/.file-play/file1";

    when(mockFileWrappers[1].move(Mockito.any(FileWrapper.class))).thenReturn(true);
    when(fileFactory.createFile("gallery/.file-play/file1")).thenReturn(moveDestinationFile);
    when(randomNumberGenerator.nextInt(3)).thenReturn(1);

    when(mockGalleryFolder.getFileList()).thenReturn(mockFileWrappers);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);

    assertTrue(randomImageFileMover.hideRandom());
  }

  @Test
  public void hideRandom_emptyGallery() {
    MockFileWrapper mockGalleryFolder = Mockito.mock(MockFileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    MockFileWrapper mockHiddenFolder = Mockito.mock(MockFileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    MockFileWrapper[] mockFileWrappers = new MockFileWrapper[0];

    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    when(fileFactory.createFile(mockHiddenFolder.path)).thenReturn(mockHiddenFolder);
    when(mockGalleryFolder.getFileList()).thenReturn(mockFileWrappers);

    assertFalse(randomImageFileMover.hideRandom());
  }

  @Test
  public void hideRandom_hiddenFolderSelected() {
    MockFileWrapper mockGalleryFolder = Mockito.mock(MockFileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    MockFileWrapper mockHiddenFolder = Mockito.mock(MockFileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    MockFileWrapper[] mockFileWrappers = new MockFileWrapper[3];
    mockFileWrappers[0] = mockHiddenFolder;
    mockFileWrappers[1] = Mockito.mock(MockFileWrapper.class);
    mockFileWrappers[2] = Mockito.mock(MockFileWrapper.class);

    when(mockFileWrappers[1].getFileName()).thenReturn("file1");
    when(mockFileWrappers[1].getFilePath()).thenReturn("gallery/file1");
    when(mockFileWrappers[2].getFileName()).thenReturn("file2");
    when(mockFileWrappers[2].getFilePath()).thenReturn("gallery/file2");

    MockFileWrapper moveDestinationFile = Mockito.mock(MockFileWrapper.class);
    when(moveDestinationFile.getFileName()).thenReturn("file1");
    when(moveDestinationFile.getFilePath()).thenReturn("gallery/.file-play/file1");
    moveDestinationFile.name = "file1";
    moveDestinationFile.path = "gallery/.file-play/file1";

    when(mockFileWrappers[1].move(Mockito.any(FileWrapper.class))).thenReturn(true);
    when(fileFactory.createFile("gallery/.file-play/file1")).thenReturn(moveDestinationFile);
    when(randomNumberGenerator.nextInt(3)).thenReturn(0).thenReturn(1);

    when(mockGalleryFolder.getFileList()).thenReturn(mockFileWrappers);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);

    assertTrue(randomImageFileMover.hideRandom());
  }

  @Test
  public void hideRandom_onlyHiddenFolder() {
    MockFileWrapper mockGalleryFolder = Mockito.mock(MockFileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    MockFileWrapper mockHiddenFolder = Mockito.mock(MockFileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    MockFileWrapper[] mockFileWrappers = new MockFileWrapper[1];
    mockFileWrappers[0] = mockHiddenFolder;

    when(randomNumberGenerator.nextInt(0)).thenReturn(0);

    when(mockGalleryFolder.getFileList()).thenReturn(mockFileWrappers);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);

    assertFalse(randomImageFileMover.hideRandom());
  }

  @Test
  public void restoreRandom_dontMove() {
    // Return a number that won't trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(3);
    assertEquals(randomImageFileMover.restoreRandom(), false);
  }

  @Test
  public void restoreRandom_emptyHiddenFolder() {
    MockFileWrapper mockGalleryFolder = Mockito.mock(MockFileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    MockFileWrapper mockHiddenFolder = Mockito.mock(MockFileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    MockFileWrapper[] mockFileWrappers = new MockFileWrapper[0];

    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(mockHiddenFolder.getFileList()).thenReturn(mockFileWrappers);

    assertFalse(randomImageFileMover.restoreRandom());
  }

  @Test
  public void restoreRandom_doMove() {
    MockFileWrapper mockGalleryFolder = Mockito.mock(MockFileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    MockFileWrapper mockHiddenFolder = Mockito.mock(MockFileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    MockFileWrapper[] mockFileWrappers = new MockFileWrapper[2];
    mockFileWrappers[0] = Mockito.mock(MockFileWrapper.class);
    mockFileWrappers[1] = Mockito.mock(MockFileWrapper.class);

    when(mockFileWrappers[0].getFileName()).thenReturn("file1");
    when(mockFileWrappers[0].getFilePath()).thenReturn("gallery/.file-play/file1");
    when(mockFileWrappers[1].getFileName()).thenReturn("file2");
    when(mockFileWrappers[1].getFilePath()).thenReturn("gallery/.file-play/file2");

    MockFileWrapper moveDestinationFile = Mockito.mock(MockFileWrapper.class);
    when(moveDestinationFile.getFileName()).thenReturn("file1");
    when(moveDestinationFile.getFilePath()).thenReturn("gallery/file1");
    moveDestinationFile.name = "file1";
    moveDestinationFile.path = "gallery/file1";

    when(mockFileWrappers[0].move(Mockito.any(FileWrapper.class))).thenReturn(true);
    when(fileFactory.createFile("gallery/file1")).thenReturn(moveDestinationFile);
    when(randomNumberGenerator.nextInt(3)).thenReturn(0);

    when(mockHiddenFolder.getFileList()).thenReturn(mockFileWrappers);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);

    assertTrue(randomImageFileMover.restoreRandom());
  }

  // TODO(jmtaber129): Add tests for restoreRandom().

}
