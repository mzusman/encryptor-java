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
    Operation operation;
    private long startTime = 0;
    private long endTime = 0;

    public Operator(Operation operation) {

    }

    @Override
    public File act(DisplayMessage displayMessage, File file, List<CipherAlgorithm> algorithms) throws IOException, KeyException {

        for (CipherAlgorithm algorithm :
                algorithms) {
            displayMessage.display(String.format("%s: using key: %d , save it for future use!",
                    algorithm.getDescription(), getKey(new NormalAlgorithm(algorithm))));
        }

        startTime = System.currentTimeMillis();
        displayMessage.display("Action started!");


        File operationFile = operation.act(file, algorithms);

        displayMessage.display("Action ended!");
        endTime = System.currentTimeMillis();
        displayMessage.display(String.format("Action took : %d ms", getElapsedTime()));
        return operationFile;
    }

    @Override
    public int getKey(Algorithm algorithm) throws IOException {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }

    /**
     * @return time took for the operation , 0 if the operation was'nt started
     */
    public long getElapsedTime() {
        return endTime - startTime;
    }
}
