package exceptions;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 28/07/16.
 */
public class FileErrorException extends IOException {
    @Getter
    File file;

    public FileErrorException(Exception e, File file, String s) {
        super(s, e);
        this.file = file;
    }
}
