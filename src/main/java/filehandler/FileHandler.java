package filehandler;

import commandline.CliHandler;
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

    public void handleFile() {
        operation.act(file);
    }

    public void showFile() {
        FileInputStream fis = null;
        try {
            @Cleanup BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            bufferedReader.lines().forEach(System.out::println);
        } catch (FileNotFoundException e) {
            do {
                String path = CliHandler.getInstance().handleNotFoundFile(file.getPath());
                file = new File(path);
            } while (!file.exists() || !file.isFile());
        }
    }
}
