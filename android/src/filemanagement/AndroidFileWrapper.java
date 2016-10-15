package filemanagement;

import java.io.File;

/**
 * Implementation of the FileWrapper interface for android Files.
 */
public class AndroidFileWrapper implements FileWrapper {
  private File wrappedFile;

  protected AndroidFileWrapper(File androidFile) {
    wrappedFile = androidFile;
  }

  @Override
  public String getFilePath() {
    return wrappedFile.getPath();
  }

  @Override
  public String getFileName() { return wrappedFile.getName(); }

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
  public Boolean move(FileWrapper destination) {
    return this.wrappedFile.renameTo(new File(destination.getFilePath()));
  }
}
