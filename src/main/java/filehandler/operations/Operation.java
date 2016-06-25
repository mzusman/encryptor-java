package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.AlgorithmKey;
import filehandler.algorithm.CipherAlgorithm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 04/06/16.
 */
public interface Operation {
    void run(StreamManager streamManager, CipherAlgorithm algorithm) throws IOException, KeyException;

    int findKey(CipherAlgorithm algorithm) throws IOException;

    byte operate(CipherAlgorithm algorithm, int raw, int index, int key);

}
