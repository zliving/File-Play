package filemanagement;

import java.io.File;

/**
 * Implementation of the FileWrapper interface for android Files.
 */
public class AndroidFileWrapper implements FileWrapper {
  private final File wrappedFile;

  AndroidFileWrapper(File androidFile) {
    wrappedFile = androidFile;
  }

  @Override
  public String getFilePath() {
    return wrappedFile.getPath();
  }

  @Override
  public String getFileName() {
    return wrappedFile.getName();
  }

  /*
   * Gets the list of files from the wrapped file, then creates a list of wrapped files from this
   * list.
   */
  @Override
  public AndroidFileWrapper[] getFileList() {
    File[] fileList = this.wrappedFile.listFiles();
    AndroidFileWrapper[] returnList = new AndroidFileWrapper[fileList.length];
    // For each file found in 'this', wrap it, and add it to the list to be returned.
    for (int i = 0; i < fileList.length; ++i) {
      returnList[i] = new AndroidFileWrapper(fileList[i]);
    }
    return returnList;
  }

  @Override
  public boolean isDirectory() {
    return wrappedFile.isDirectory();
  }

  /*
   * Uses the renameTo() method, which has to effect of moving a file when the paths point to files
   * in different directories.
   */
  @Override
  public boolean move(FileWrapper destination) {
    return this.wrappedFile.renameTo(new File(destination.getFilePath()));
  }
}
