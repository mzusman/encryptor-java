package exceptions;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 28/07/16.
 */
public class FileErrorException extends IOException {
    File file;

    public FileErrorException(Exception e ,File file, String s) {
        super(s,e);
    }
}
