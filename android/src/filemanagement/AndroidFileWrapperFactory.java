package filemanagement;

import java.io.File;

/**
 * Implementation of the FileWrapperFactory interface for Android.
 */
public class AndroidFileWrapperFactory implements FileWrapperFactory {
  @Override
  public AndroidFileWrapper getGalleryFile() {
    String galleryPathname = android.os.Environment.DIRECTORY_DCIM;
    return createFile(galleryPathname);
  }

  public AndroidFileWrapper createFile(String pathname) {
    File wrappedFile = new File(pathname);
    return new AndroidFileWrapper(wrappedFile);
  }
}
