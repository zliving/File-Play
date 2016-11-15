package filemanagement;

/**
 * Implementation of RandomImageFileMover.
 * TODO(jmtaber129): Add better error handling for this class's methods.
 * TODO(jmtaber129): Refactor this class's methods.
 */
public class RandomImageFileMoverImpl implements RandomImageFileMover {
  // Used to decide a random file to move.
  private final RandomWrapper randomNumberGenerator;

  // Used to create FileWrappers from pathnames.
  private final FileWrapperFactory fileFactory;

  // A file will be moved if the 'randomNumberGenerator' generates this number. This number should
  // be between 0 and 'maxRandomMoveNumber', inclusive.
  private static final int moveNumber = 0;
  // The maximum number that should be generated when deciding whether a file will be moved.  The
  // probability of a file being moved is 1 / (maxRandomMoveNumber + 1).
  private static final int maxRandomMoveNumber = 5;
  // The name of the hidden folder in the image gallery.
  private static final String hiddenFolderName = ".file-play";

  public RandomImageFileMoverImpl(FileWrapperFactory fileFactory,
                                  RandomWrapper randomNumberGenerator) {
    this.fileFactory = fileFactory;
    this.randomNumberGenerator = randomNumberGenerator;
  }

  @Override
  public boolean hideRandom() {
    if (randomNumberGenerator.nextInt(maxRandomMoveNumber) == moveNumber) {
      // The RNG returned the magic 'moveNumber', so move a file from the gallery to the hidden
      // folder.
      FileWrapper imageGallery = fileFactory.getGalleryFile();
      FileWrapper hiddenFolder = fileFactory.createFile(imageGallery.getFilePath() + "/" +
                                                            hiddenFolderName);
      FileWrapper[] galleryFiles = imageGallery.getFileList();

      if (galleryFiles.length == 0 || (galleryFiles.length == 1
          && galleryFiles[0].getFilePath().equals(hiddenFolder.getFilePath()))) {
        // The gallery is empty.
        // TODO(jmtaber129): Consider adding better handling of this case.
        return false;
      }

      // Get a random number to determining the index of the file to be moved.  Make sure the index
      // doesn't point to the hidden folder.
      FileWrapper sourceFile;
      do {
        int sourceFileIndex = randomNumberGenerator.nextInt(galleryFiles.length);
        sourceFile = galleryFiles[sourceFileIndex];
      } while (sourceFile.isDirectory());
      // Get the file pathname for the destination, and get a FileWrapper for that pathname.
      String destinationPath = hiddenFolder.getFilePath() + "/" + sourceFile.getFileName();

      FileWrapper destinationFile = fileFactory.createFile(destinationPath);
      // TODO(jmtaber129): If this returns false, an error occurred when trying to move the file.
      // Change this to have better error handling.
      return sourceFile.move(destinationFile);
    } else {
      // The RNG returned a number other than the magic 'moveNumber', so just return here.
      return false;
    }
  }

  @Override
  public boolean restoreRandom() {
    if (randomNumberGenerator.nextInt(maxRandomMoveNumber) == moveNumber) {
      // The RNG returned the magic 'moveNumber', so move a file from the gallery to the hidden
      // folder.
      FileWrapper imageGallery = fileFactory.getGalleryFile();
      FileWrapper hiddenFolder = fileFactory.createFile(imageGallery.getFilePath() + "/" +
                                                            hiddenFolderName);
      FileWrapper[] hiddenFiles = hiddenFolder.getFileList();

      int hiddenFolderSize = hiddenFiles.length;
      if (hiddenFolderSize == 0) {
        // The hidden folder is empty.
        // TODO(jmtaber129): Consider adding better handling of this case.
        return false;
      }

      // Get a random number to determining the index of the file to be moved.
      int sourceFileIndex = randomNumberGenerator.nextInt(hiddenFiles.length);

      FileWrapper sourceFile = hiddenFiles[sourceFileIndex];

      // Get the file pathname for the destination, and get a FileWrapper for that pathname.
      String destinationPath = imageGallery.getFilePath() + "/" + sourceFile.getFileName();

      FileWrapper destinationFile = fileFactory.createFile(destinationPath);

      // TODO(jmtaber129): If this returns false, an error occurred when trying to move the file.
      // Change this to have better error handling.
      return sourceFile.move(destinationFile);
    } else {
      // The RNG returned a number other than the magic 'moveNumber', so just return here.
      return false;
    }
  }

  @Override
  public boolean restoreAll() {
    FileWrapper imageGallery = fileFactory.getGalleryFile();
    FileWrapper hiddenFolder =
        fileFactory.createFile(imageGallery.getFilePath() + "/" + hiddenFolderName);

    FileWrapper[] hiddenFiles = hiddenFolder.getFileList();

    for (FileWrapper file : hiddenFiles) {
      String destinationPath = imageGallery.getFilePath() + "/" + file.getFileName();

      FileWrapper destinationFile = fileFactory.createFile(destinationPath);

      file.move(destinationFile);
    }

    return hiddenFiles.length != 0;
  }
}
