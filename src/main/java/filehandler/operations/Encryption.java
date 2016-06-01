package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.Cleanup;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class Encryption implements Operation {

    private static final String encrypted = ".encrypted";
    private int key = 0;

    @Override
    public String getDescription() {
        return "encrypt a file";
    }

    @Override
    public File act(File file, Algorithm algorithm) throws KeyException, IOException {
        key = getKey(algorithm);
        File outputFile = new File(file.getPath() + encrypted);
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new IOException("cannot create a new file for encryption");
        }

        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        algorithm.encrypt(fis, fos, key);


        return outputFile;
    }

    @Override
    public int getKey(Algorithm algorithm) {
        if (key == 0)
            key = algorithm.createKey();
        return key;
    }


}
