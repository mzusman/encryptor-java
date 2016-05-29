package filehandler;

import filehandler.algorithm.CipherAlgorithm;
import lombok.Cleanup;

import java.io.*;
import java.util.Random;

/**
 * Created by Mor on 5/19/2016.
 */
public class Encryption implements Operation {

    private static final String encrypted = ".encrypted";
    private final int key = new Random().nextInt(100);

    public String getDescription() {
        return "encrypt a file";
    }

    @Override
    public File act(File file, CipherAlgorithm algorithm) throws IOException {
        System.out.printf("key for encryption is : %d\n", key);
        File outputFile = new File(file.getPath() + encrypted);
        outputFile.createNewFile();
        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        algorithm.encrypt(fis, fos, key);
        return outputFile;
    }
}
