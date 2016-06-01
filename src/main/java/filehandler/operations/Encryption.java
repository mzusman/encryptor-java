package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.AlgorithmOnce;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.Cleanup;
import utils.DisplayMessage;

import java.io.*;
import java.sql.Time;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.function.BiFunction;

/**
 * Created by Mor on 5/19/2016.
 */
public class Encryption implements Operation {

    private static final String encrypted = ".encrypted";
    private long startTime = 0;
    private long endTime = 0;

    @Override
    public String getDescription() {
        return "encrypt a file";
    }

    @Override
    public File act(File file, Algorithm algorithm, DisplayMessage displayMessage) throws KeyException, IOException {
        int key = algorithm.getAlgorithm().createKey();
        displayMessage.display(String.format("Key for encryption is : %d , Save it for future use!", key));
        File outputFile = new File(file.getPath() + encrypted);
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new IOException("cannot create a new file for encryption");
        }

        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        startTime = new Date().getTime();
        algorithm.encrypt(fis, fos, key);
        endTime = new Date().getTime();
        displayMessage.display(String.format("action took : %d ms", endTime - startTime));


        return outputFile;
    }


}
