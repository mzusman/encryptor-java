package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.AllArgsConstructor;
import utils.DisplayMessage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by mzeus on 01/06/16.
 */
public class OperatorWithMsg extends Operator {

    DisplayMessage displayMessage;
    long startTime = 0;
    long endTime = 0;

    public OperatorWithMsg(Operation operation, DisplayMessage message) {
        super(operation);
        this.displayMessage = message;
    }

    @Override
    public File act(File file, Algorithm algorithm) throws IOException, KeyException {
        displayMessage.display(String.format("Key for encryption is : %d , Save it for future use!", getKey(algorithm)));

        startTime = new Date().getTime();
        displayMessage.display("Action started!");
        File operationFile = operation.act(file, algorithm);
        displayMessage.display("Action ended!");
        endTime = new Date().getTime();
        displayMessage.display(String.format("Action took : %d ms", getElapsedTime()));
        return operationFile;
    }

    @Override
    public int getKey(Algorithm algorithm) throws IOException {
        return operation.getKey(algorithm);
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
