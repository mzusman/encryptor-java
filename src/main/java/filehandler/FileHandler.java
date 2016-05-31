package filehandler;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import filehandler.operations.Operation;
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

    public void handleFile(CipherAlgorithm algorithm) throws KeyException, IOException {
        file = operation.act(file, algorithm, displayMessage);
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
