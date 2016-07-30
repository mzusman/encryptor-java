package exceptions;

import java.io.IOError;
import java.io.IOException;

/**
 * Created by mzeus on 25/07/16.
 */
public class FileAlreadyExistsException extends IOException {
    /**
     * Instantiates a new File already exists exception.
     *
     * @param fileName the file name
     */
    public FileAlreadyExistsException(String fileName) {
        super(fileName);
    }

}
