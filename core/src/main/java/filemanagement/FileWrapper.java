package filemanagement;

/**
 * Interface for a wrapper of the File class.
 */
public interface FileWrapper {
  /*
   *  Returns a string describing the file path.
   */
  String getFilePath();

  /*
   *  Returns a string describing the name of the file (i.e. the last name in the file path).
   */
  String getFileName();

  /*
   *  Returns a list of files contained in the directory described by 'this'.
   *  Returns an empty list if 'this' is an empty directory or a file.
   */
  FileWrapper[] getFileList();

  /*
   *  Returns true if 'this' is moved to 'destination' successfully, returns false otherwise.
   *  Usage:
   *  FileWrapper src;
   *  FileWrapper dst;
   *
   *  // Initialize src and dst.
   *
   *  // Move src to dst.
   *  src.move(dst.getFilePath());
   */
  boolean move(FileWrapper destination);
}
