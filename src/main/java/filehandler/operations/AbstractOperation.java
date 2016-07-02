package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.AlgorithmKey;
import filehandler.algorithm.CipherAlgorithm;
import filehandler.algorithm.ListOfAlgorithms;
import lombok.Cleanup;

import java.io.*;
import java.util.Observable;

/**
 * Created by mzeus on 29/05/16.
 */
public abstract class AbstractOperation extends Observable implements Operation<ListOfAlgorithms>, FileHandler {

    @Override
    public void run(StreamManager streamManager, ListOfAlgorithms algorithm) throws IOException {
        findKey(algorithm);
        InputStream in = streamManager.getInputStream();
        OutputStream out = streamManager.getOutputStream();
        int raw;
        byte enc;
        int index = 0;
        try {
            while ((raw = in.read()) != -1) {
                enc = operate(algorithm, raw, index);
                index++;
                out.write(enc);
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file");
        }
    }


}
