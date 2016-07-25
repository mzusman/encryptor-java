package exceptions;

import java.io.IOError;
import java.io.IOException;

/**
 * Created by mzeus on 25/07/16.
 */
public class FileAlreadyExistsException extends IOException {
    public FileAlreadyExistsException(String fileName) {
        super(fileName);
    }

}
