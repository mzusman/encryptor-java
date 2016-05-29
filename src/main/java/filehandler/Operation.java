package filehandler;

import filehandler.algorithm.CipherAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by mzeus on 29/05/16.
 */
public interface Operation {
    String getDescription();
    File act(File file, CipherAlgorithm algorithm) throws IOException;

}
