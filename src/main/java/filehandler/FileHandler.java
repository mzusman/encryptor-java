package filehandler;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.AlgorithmOnce;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import filehandler.operations.Operation;
import filehandler.operations.Operator;
import filehandler.operations.OperatorWithMsg;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import utils.DisplayMessage;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */

@AllArgsConstructor
public class FileHandler {
    private final Operation operation;
    private File file;
    private DisplayMessage displayMessage;


    public String getDescription() {
        return operation.getDescription();
    }

    public void handleFile(CipherAlgorithm cipherAlgorithm) throws KeyException, IOException {
        Algorithm algorithm = new AlgorithmOnce(cipherAlgorithm);
        OperatorWithMsg operator = new OperatorWithMsg(operation, displayMessage);
        file = operator.act(file, algorithm);
//        if (file != null)
//            showFile();
    }

    private void showFile() throws IOException {
        @Cleanup BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new IOException("file not found");
        }
        bufferedReader.lines().forEach(System.out::println);
    }
}
