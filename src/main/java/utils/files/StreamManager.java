package utils.files;

import exceptions.EmptyDirectoryException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Manages i\o streams
 */
public interface StreamManager {
    /**
     * Gets output stream.
     *
     * @return the output stream
     * @throws IOException the io exception
     */
    OutputStream getOutputStream() throws IOException;

    /**
     * Gets input stream.
     *
     * @return the input stream
     * @throws IOException the io exception
     */
    InputStream getInputStream() throws IOException;
}
