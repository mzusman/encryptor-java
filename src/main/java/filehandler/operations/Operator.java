package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.ManipulatedAlgorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import utils.DisplayMessage;

import java.io.*;

/**
 * Created by mzeus on 01/06/16.
 */
public class Operator extends AbstractOperation {
    private AbstractOperation abstractOperation;
    private long startTime = 0;
    private long endTime = 0;

    public Operator(AbstractOperation abstractOperation) {
        this.abstractOperation = abstractOperation;
    }

    @Override
    public File init(File file, ManipulatedAlgorithm manipulatedAlgorithm) throws IOException, KeyException {

        startTime = System.currentTimeMillis();
        displayMessage.display("Action started!");


        File operationFile = abstractOperation.init(displayMessage, file, manipulatedAlgorithm);

        displayMessage.display("Action ended!");
        endTime = System.currentTimeMillis();
        displayMessage.display(String.format("Action took : %d ms", getElapsedTime()));
        return operationFile;
    }

    @Override
    public void run(DisplayMessage message, InputStream in, OutputStream out, int key, CipherAlgorithm cipherAlgorithm) throws IOException, KeyException {
        abstractOperation.run(message, in, out, key, cipherAlgorithm);
    }

    @Override
    public int findKey(CipherAlgorithm cipherAlgorithm) throws IOException {
        return abstractOperation.findKey(cipherAlgorithm);
    }

    @Override
    public String getDescription() {
        return abstractOperation.getDescription();
    }

    /**
     * @return time took for the abstractOperation , 0 if the abstractOperation was'nt started
     */
    private long getElapsedTime() {
        return endTime - startTime;
    }

    @Override
    public File createNewFile(File file) throws IOException {
        return abstractOperation.createNewFile(file);
    }
}
