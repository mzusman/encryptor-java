package filehandler;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import filehandler.operations.Operation;
import lombok.AllArgsConstructor;
import lombok.Cleanup;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */

@AllArgsConstructor
public class FileHandler {
    private final Operation operation;
    private File file;


    public String getDescription() {
        return operation.getDescription();
    }

    public void handleFile(CipherAlgorithm algorithm) throws KeyException {
        try {
            file = operation.act(file, algorithm);
            if (file != null)
                showFile();
        } catch (IOException e) {
            String path = CliHandler.getInstance().handleNotFoundFile(file.getPath());
            file = new File(path);
        }
    }

    public void showFile() {
        try {
            @Cleanup BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            bufferedReader.lines().forEach(System.out::println);
        } catch (IOException e) {
            String path = CliHandler.getInstance().handleNotFoundFile(file.getPath());
            file = new File(path);
        }
    }
}
