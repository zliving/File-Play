package filemanagement;

/**
 * Interface for creating FileWrapper objects.
 */

public interface FileWrapperFactory {
  /*
   *  Returns a FileWrapper describing the directory of the local image gallery.
   */
  FileWrapper getGalleryFile();

  /*
   *  Returns a FileWrapper describing the file at 'pathname'.
   */
  FileWrapper createFile(String pathname);
}
