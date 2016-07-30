package exceptions;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 28/07/16.
 */
public class FileErrorException extends IOException {
    /**
     * The File.
     */
    @Getter
    File file;

    /**
     * Instantiates a new File error exception.
     *
     * @param e    the e
     * @param file the file
     * @param s    the s
     */
    public FileErrorException(Exception e, File file, String s) {
        super(s, e);
        this.file = file;
    }
}
