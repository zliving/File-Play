package filemanagement;

import java.util.Random;

/**
 * Created by James on 10/14/2016.
 */

public class RandomImageFileMoverImpl implements RandomImageFileMover {
  // Used to decide a random file to move.
  Random randomNumberGenerator;

  // Used to create FileWrappers from pathnames.
  FileWrapperFactory fileFactory;

  // A file will be moved if the 'randomNumberGenerator' generates this number. This number should
  // be between 0 and 'maxRandomMoveNumber', inclusive.
  final int moveNumber = 0;
  // The maximum number that should be generated when deciding whether a file will be moved.  The
  // probability of a file being moved is 1 / (maxRandomMoveNumber + 1).
  final int maxRandomMoveNumber = 5;

  public RandomImageFileMoverImpl(FileWrapperFactory fileFactory, Random randomNumberGenerator) {
    this.fileFactory = fileFactory;
    this.randomNumberGenerator = randomNumberGenerator;
  }

  @Override
  public boolean hideRandom() {
    if (randomNumberGenerator.nextInt(maxRandomMoveNumber) == moveNumber) {
      // The RNG returned the magic 'moveNumber', so move a file from the gallery to the hidden
      // folder.
      FileWrapper imageGallery = fileFactory.getGalleryFile();
      FileWrapper hiddenFolder = fileFactory.createFile(imageGallery.getFilePath() + "/.file-play");
      FileWrapper[] galleryFiles = imageGallery.getFileList();

      // Get a random number to determining the index of the file to be moved.
      int sourceFileIndex = randomNumberGenerator.nextInt(galleryFiles.length);

      FileWrapper sourceFile = galleryFiles[sourceFileIndex];

      // Get the file pathname for the destination, and get a FileWrapper for that pathname.
      String destinationPath = imageGallery.getFilePath() + "/.file-play/"
          + sourceFile.getFileName();

      FileWrapper destinationFile = fileFactory.createFile(destinationPath);

      return sourceFile.move(destinationFile);
    } else {
      // The RNG returned a number other than the magic 'moveNumber', so just return here.
      return false;
    }
  }

  @Override
  public boolean restoreRandom() {
    return true;
  }
}
