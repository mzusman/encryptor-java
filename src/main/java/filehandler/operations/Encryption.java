package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.DoubleAlgorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.Cleanup;
import utils.DisplayMessage;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mor on 5/19/2016.
 */
public class Encryption implements Operation {

    private static final String encrypted = ".encrypted";
    private int key = 0;

    public Encryption() {

    }

    @Override
    public String getDescription() {
        return "encrypt a file";
    }

    @Override
    public File act(DisplayMessage displayMessage, File file, Algorithm algorithm) throws KeyException, IOException {

        File outputFile = createNewFile(file);
        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);

        for (CipherAlgorithm cipherAlgorithm : algorithm.getAlgorithms()) {
            encrypt(fis, fos, getKey(cipherAlgorithm), cipherAlgorithm);
        }
        return outputFile;
    }


    private File createNewFile(File originalFile) throws IOException {
        File outputFile = new File(originalFile.getPath() + encrypted);
        try {
            outputFile.createNewFile();
            return outputFile;
        } catch (IOException e) {
            throw new IOException("cannot create a new file for encryption");
        }
    }

    private void encrypt(InputStream in, OutputStream out, int key, CipherAlgorithm cipherAlgorithm) throws IOException {
        int raw;
        byte enc;
        try {
            while ((raw = in.read()) != -1) {
                enc = cipherAlgorithm.encryptionOperation(raw, key);
                out.write(enc);
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file");
        }

    }


    @Override
    public int getKey(CipherAlgorithm algorithm) {
        if (key == 0)
            key = algorithm.createKey();
        return key;
    }


}
