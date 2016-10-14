package filemanagement;

/**
 * Interface for a wrapper of the android File class.
 */
public interface FileWrapper {
    /*
     *  Returns a list of files contained in the directory described by 'this'.
     *  Returns an empty list if 'this' is an empty directory or a file.
     */
    public FileWrapper[] getFileList();

    /*
     *  Returns true if 'this' is moved to 'destination' successfully, returns false otherwise.
     *  Usage:
     *  FileWrapper src;
     *  FileWrapper dst;
     *
     *  // Initialize src and dst.
     *
     *  // Move src to dst.
     *  src.Move(dst);
     */
    Boolean Move(FileWrapper destination);
}
