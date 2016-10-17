package filemanagement;

/**
 * Interface for moving images between the user image gallery and a hidden folder within the
 * gallery.
 */
interface RandomImageFileMover {
  /*
   * Returns true if a file is moved, returns false otherwise.
   * Moves a random file from the image gallery to the hidden folder.
   */
  boolean hideRandom();

  /*
   * Returns true if a file is moved, returns false otherwise.
   * Moves a random file from the hidden folder to the image gallery.
   */
  boolean restoreRandom();
}
