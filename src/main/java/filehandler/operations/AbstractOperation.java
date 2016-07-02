package filehandler.operations;

import commandline.CommandsEnum;
import exceptions.KeyException;
import filehandler.algorithm.AlgorithmKey;
import filehandler.algorithm.SingleAlgorithm;
import filehandler.algorithm.ListOfAlgorithms;
import lombok.Cleanup;

import java.io.*;
import java.util.Observable;

/**
 * Created by mzeus on 29/05/16.
 */
public abstract class AbstractOperation extends Observable implements Operation<ListOfAlgorithms> {
    private StreamManager streamManager;

    public AbstractOperation(StreamManager streamManager) {
        this.streamManager = streamManager;
    }


    abstract byte operate(ListOfAlgorithms algorithm, int raw, int index);

    abstract void findKey(ListOfAlgorithms algorithm) throws IOException;

    @Override
    public void run(ListOfAlgorithms algorithm) throws IOException {
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
