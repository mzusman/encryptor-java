package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import utils.DisplayMessage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by mzeus on 01/06/16.
 */
public class Operator implements Operation {
    private Operation operation;
    private long startTime = 0;
    private long endTime = 0;

    public Operator(Operation operation) {
        this.operation = operation;
    }

    @Override
    public File act(DisplayMessage displayMessage, File file, Algorithm algorithm) throws IOException, KeyException {

        for (CipherAlgorithm cipherAlgorithm :
                algorithm.getAlgorithms()) {
            displayMessage.display(String.format("%s: using key: %d , save it for future use!",
                    algorithm.getDescription(), getKey(cipherAlgorithm)));
        }

        startTime = System.currentTimeMillis();
        displayMessage.display("Action started!");


        File operationFile = operation.act(displayMessage, file, algorithm);

        displayMessage.display("Action ended!");
        endTime = System.currentTimeMillis();
        displayMessage.display(String.format("Action took : %d ms", getElapsedTime()));
        return operationFile;
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
}
