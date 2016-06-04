package filehandler.operations;

import commandline.CliHandler;
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
public class Encryption extends Operation {

    private static final String encrypted = ".encrypted";

    public Encryption() {

    }

    @Override
    public String getDescription() {
        return "encrypt a file";
    }


    public File createNewFile(File originalFile) throws IOException {
        String exception = "cannot create a new file for encryption";
        File outputFile = new File(originalFile.getPath() + encrypted);
        try {
            if (!outputFile.createNewFile())
                throw new IOException(exception);
            return outputFile;
        } catch (IOException e) {
            throw new IOException(exception);
        }
    }

    @Override
    void run(InputStream in, OutputStream out, int key, CipherAlgorithm cipherAlgorithm) throws IOException {
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
        displayMessage.display("using key");
        return algorithm.createKey();
    }


}
