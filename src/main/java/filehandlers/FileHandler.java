package filehandlers;

import java.io.File;

/**
 * Created by Mor on 5/19/2016.
 */
public interface FileHandler {

    String getDescription();

    void handleFile(File file);
}
