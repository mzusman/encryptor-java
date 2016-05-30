package filehandler;

import commandline.CliHandler;
import filehandler.algorithm.CaesarAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Cleanup;

import java.io.*;
import java.util.Random;

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

    public void handleFile() {
        try {
            file = operation.act(file, new CaesarAlgorithm());
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
