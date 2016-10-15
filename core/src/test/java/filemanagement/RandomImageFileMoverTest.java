package filemanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

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
    FileWrapper mockGalleryFolder = Mockito.mock(FileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    FileWrapper mockHiddenFolder = Mockito.mock(FileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    FileWrapper[] FileWrappers = new FileWrapper[3];
    FileWrappers[0] = mockHiddenFolder;
    FileWrappers[1] = Mockito.mock(FileWrapper.class);
    FileWrappers[2] = Mockito.mock(FileWrapper.class);

    when(FileWrappers[1].getFileName()).thenReturn("file1");
    when(FileWrappers[1].getFilePath()).thenReturn("gallery/file1");
    when(FileWrappers[2].getFileName()).thenReturn("file2");
    when(FileWrappers[2].getFilePath()).thenReturn("gallery/file2");

    FileWrapper moveDestinationFile = Mockito.mock(FileWrapper.class);
    when(moveDestinationFile.getFileName()).thenReturn("file1");
    when(moveDestinationFile.getFilePath()).thenReturn("gallery/.file-play/file1");

    when(FileWrappers[1].move(Mockito.any(FileWrapper.class))).thenReturn(true);
    when(fileFactory.createFile("gallery/.file-play/file1")).thenReturn(moveDestinationFile);
    when(randomNumberGenerator.nextInt(3)).thenReturn(1);

    when(mockGalleryFolder.getFileList()).thenReturn(FileWrappers);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);

    assertTrue(randomImageFileMover.hideRandom());
  }

  @Test
  public void hideRandom_emptyGallery() {
    FileWrapper mockGalleryFolder = Mockito.mock(FileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    FileWrapper mockHiddenFolder = Mockito.mock(FileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    FileWrapper[] FileWrappers = new FileWrapper[0];

    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(mockGalleryFolder.getFileList()).thenReturn(FileWrappers);

    assertFalse(randomImageFileMover.hideRandom());
  }

  @Test
  public void hideRandom_hiddenFolderSelected() {
    FileWrapper mockGalleryFolder = Mockito.mock(FileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    FileWrapper mockHiddenFolder = Mockito.mock(FileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    FileWrapper[] FileWrappers = new FileWrapper[3];
    FileWrappers[0] = mockHiddenFolder;
    FileWrappers[1] = Mockito.mock(FileWrapper.class);
    FileWrappers[2] = Mockito.mock(FileWrapper.class);

    when(FileWrappers[1].getFileName()).thenReturn("file1");
    when(FileWrappers[1].getFilePath()).thenReturn("gallery/file1");
    when(FileWrappers[2].getFileName()).thenReturn("file2");
    when(FileWrappers[2].getFilePath()).thenReturn("gallery/file2");

    FileWrapper moveDestinationFile = Mockito.mock(FileWrapper.class);
    when(moveDestinationFile.getFileName()).thenReturn("file1");
    when(moveDestinationFile.getFilePath()).thenReturn("gallery/.file-play/file1");

    when(FileWrappers[1].move(Mockito.any(FileWrapper.class))).thenReturn(true);
    when(fileFactory.createFile("gallery/.file-play/file1")).thenReturn(moveDestinationFile);
    when(randomNumberGenerator.nextInt(3)).thenReturn(0).thenReturn(1);

    when(mockGalleryFolder.getFileList()).thenReturn(FileWrappers);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);

    assertTrue(randomImageFileMover.hideRandom());
  }

  @Test
  public void hideRandom_onlyHiddenFolder() {
    FileWrapper mockGalleryFolder = Mockito.mock(FileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    FileWrapper mockHiddenFolder = Mockito.mock(FileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    FileWrapper[] FileWrappers = new FileWrapper[1];
    FileWrappers[0] = mockHiddenFolder;

    when(randomNumberGenerator.nextInt(0)).thenReturn(0);

    when(mockGalleryFolder.getFileList()).thenReturn(FileWrappers);
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
    FileWrapper mockGalleryFolder = Mockito.mock(FileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    FileWrapper mockHiddenFolder = Mockito.mock(FileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    FileWrapper[] FileWrappers = new FileWrapper[0];

    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(mockHiddenFolder.getFileList()).thenReturn(FileWrappers);

    assertFalse(randomImageFileMover.restoreRandom());
  }

  @Test
  public void restoreRandom_doMove() {
    FileWrapper mockGalleryFolder = Mockito.mock(FileWrapper.class);
    when(mockGalleryFolder.getFileName()).thenReturn("gallery");
    when(mockGalleryFolder.getFilePath()).thenReturn("gallery");

    FileWrapper mockHiddenFolder = Mockito.mock(FileWrapper.class);
    when(mockHiddenFolder.getFileName()).thenReturn(".file-play");
    when(mockHiddenFolder.getFilePath()).thenReturn("gallery/.file-play");

    FileWrapper[] FileWrappers = new FileWrapper[2];
    FileWrappers[0] = Mockito.mock(FileWrapper.class);
    FileWrappers[1] = Mockito.mock(FileWrapper.class);

    when(FileWrappers[0].getFileName()).thenReturn("file1");
    when(FileWrappers[0].getFilePath()).thenReturn("gallery/.file-play/file1");
    when(FileWrappers[1].getFileName()).thenReturn("file2");
    when(FileWrappers[1].getFilePath()).thenReturn("gallery/.file-play/file2");

    FileWrapper moveDestinationFile = Mockito.mock(FileWrapper.class);
    when(moveDestinationFile.getFileName()).thenReturn("file1");
    when(moveDestinationFile.getFilePath()).thenReturn("gallery/file1");

    when(FileWrappers[0].move(Mockito.any(FileWrapper.class))).thenReturn(true);
    when(fileFactory.createFile("gallery/file1")).thenReturn(moveDestinationFile);
    when(randomNumberGenerator.nextInt(3)).thenReturn(0);

    when(mockHiddenFolder.getFileList()).thenReturn(FileWrappers);
    when(fileFactory.createFile(mockHiddenFolder.getFilePath())).thenReturn(mockHiddenFolder);
    when(fileFactory.getGalleryFile()).thenReturn(mockGalleryFolder);
    // Return the number that will trigger a move.
    when(randomNumberGenerator.nextInt(5)).thenReturn(0);

    assertTrue(randomImageFileMover.restoreRandom());
  }
}
