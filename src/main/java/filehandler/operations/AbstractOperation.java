package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.AlgorithmKey;
import filehandler.algorithm.CipherAlgorithm;
import lombok.Cleanup;

import java.io.*;
import java.util.Observable;

/**
 * Created by mzeus on 29/05/16.
 */
public abstract class AbstractOperation extends Observable implements Operation, FileHandler {

    @Override
    public void run(StreamManager streamManager, CipherAlgorithm algorithm) throws IOException {
        int key = findKey(algorithm);
        AlgorithmKey algorithmKey = new AlgorithmKey(algorithm, key);
        InputStream in = streamManager.getInputStream();
        OutputStream out = streamManager.getOutputStream();
        int raw;
        byte enc;
        int index = 0;
        try {
            while ((raw = in.read()) != -1) {
                enc = operate(algorithmKey.getCipherAlgorithm(), raw, index, algorithmKey.getKey());
                index++;
                out.write(enc);
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file");
        }

    }


}
