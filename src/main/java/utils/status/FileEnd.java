package utils.status;

import java.io.File;

/**
 * Notify observer that a file operation has been ended.
 */
public class FileEnd extends FileStatus {
    /**
     * Instantiates a new File end.
     *
     * @param file the file
     */
    public FileEnd(File file) {
        super(file);
    }
}
