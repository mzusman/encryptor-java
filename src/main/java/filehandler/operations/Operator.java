package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.Cleanup;
import utils.DisplayMessage;

import java.io.*;
import java.util.List;

/**
 * Created by mzeus on 01/06/16.
 */
public class Operator extends Operation {
    private Operation operation;
    private long startTime = 0;
    private long endTime = 0;
    DisplayMessage displayMessage;

    public Operator(Operation operation) {
        this.operation = operation;
    }

    @Override
    public File act(DisplayMessage displayMessage, File file, Algorithm algorithm) throws IOException, KeyException {


//        for (CipherAlgorithm cipherAlgorithm :
//                algorithm.getAlgorithms()) {
//            displayMessage.display();
//        }

        this.displayMessage = displayMessage;
        startTime = System.currentTimeMillis();
        displayMessage.display("Action started!");


        File operationFile = operation.act(displayMessage, file, algorithm);

        displayMessage.display("Action ended!");
        endTime = System.currentTimeMillis();
        displayMessage.display(String.format("Action took : %d ms", getElapsedTime()));
        return operationFile;
    }

    @Override
    void run(InputStream in, OutputStream out, int key, CipherAlgorithm cipherAlgorithm) throws IOException, KeyException {
        operation.run(in, out, key, cipherAlgorithm);
    }

    @Override
    public int getKey(CipherAlgorithm cipherAlgorithm) throws IOException {
        return operation.getKey(cipherAlgorithm);
    }

    @Override
    public String getDescription() {
        return operation.getDescription();
    }

    /**
     * @return time took for the operation , 0 if the operation was'nt started
     */
    private long getElapsedTime() {
        return endTime - startTime;
    }

    @Override
    public File createNewFile(File file) throws IOException {
        return operation.createNewFile(file);
    }
}
