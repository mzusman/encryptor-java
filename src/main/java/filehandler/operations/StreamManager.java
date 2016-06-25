package filehandler.operations;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 6/25/16.
 */
public interface StreamManager {
    OutputStream getOutputStream();

    InputStream getInputStream();
}
