package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import utils.DisplayMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 04/06/16.
 */
public interface Operation {
    File init(DisplayMessage event, File file, Algorithm algorithm) throws IOException, KeyException;

    void run(DisplayMessage event, InputStream in, OutputStream out, int key, CipherAlgorithm cipherAlgorithm) throws IOException, KeyException;

    int getKey(CipherAlgorithm algorithm) throws IOException;
}
