package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.ManipulatedAlgorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import utils.DisplayMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 04/06/16.
 */
interface Operation {
    File init(File file, CipherAlgorithm algorithm) throws IOException, KeyException;

    void run(InputStream in, OutputStream out, int key, CipherAlgorithm cipherAlgorithm) throws IOException, KeyException;

    int findKey(CipherAlgorithm algorithm) throws IOException;

    byte operate(CipherAlgorithm algorithm, int raw, int key);
}
