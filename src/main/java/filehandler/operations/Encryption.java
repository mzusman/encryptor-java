package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.AlgorithmOnce;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.Cleanup;
import utils.DisplayMessage;

import java.io.*;
import java.util.Random;
import java.util.function.BiFunction;

/**
 * Created by Mor on 5/19/2016.
 */
public class Encryption implements Operation {

    private static final String encrypted = ".encrypted";

    @Override
    public String getDescription() {
        return "encrypt a file";
    }

    @Override
    public File act(File file, CipherAlgorithm cipherAlgorithm, DisplayMessage message) throws IOException, KeyException {
        int key = cipherAlgorithm.createKey();
        message.display(String.format("Key for encryption is : %d , Save it for future use!", key));
        File outputFile = new File(file.getPath() + encrypted);
        outputFile.createNewFile();
        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        Algorithm algorithm = new AlgorithmOnce(cipherAlgorithm);
        algorithm.encrypt(fis, fos, key);
        return outputFile;
    }
}
