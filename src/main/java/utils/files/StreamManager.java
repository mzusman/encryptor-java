package utils.files;

import exceptions.EmptyDirectoryException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 6/25/16.
 */
public interface StreamManager {
    OutputStream getOutputStream() throws IOException;

    InputStream getInputStream() throws IOException;
}
