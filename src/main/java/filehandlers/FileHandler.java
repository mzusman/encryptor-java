package filehandlers;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Mor on 5/19/2016.
 */
public interface FileHandler {

    String getDescription();

    void handleFile(File file) ;
}
