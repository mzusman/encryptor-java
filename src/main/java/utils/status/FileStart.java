package utils.status;

import java.io.File;

/**
 * notify observers that a file operation has been started
 */
public class FileStart extends FileStatus {
    /**
     * Instantiates a new File start.
     *
     * @param file the file
     */
    public FileStart(File file) {
        super(file);
    }
}
