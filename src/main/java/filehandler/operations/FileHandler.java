package filehandler.operations;

import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 03/06/16.
 */
public interface FileHandler {
    File createNewFile(File file) throws IOException;
}
