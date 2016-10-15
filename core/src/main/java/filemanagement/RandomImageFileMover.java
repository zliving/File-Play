package filemanagement;

/**
 * Interface for moving images between the user image gallery and a hidden folder within the
 * gallery.
 */

public interface RandomImageFileMover {
  /*
   * Returns true if the move operation is successful, returns false otherwise.
   * Moves a random file from the image gallery to the hidden folder.
   */
  boolean hideRandom();

  /*
   * Returns true if the move operation is successful, returns false otherwise.
   * Moves a random file from the hidden folder to the image gallery.
   */
  boolean restoreRandom();
}
