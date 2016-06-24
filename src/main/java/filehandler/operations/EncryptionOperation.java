package filehandler.operations;

import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import utils.DisplayMessage;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class EncryptionOperation extends AbstractOperation {

    private static final String encrypted = ".encrypted";

    public EncryptionOperation() {

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
    public byte operate(CipherAlgorithm algorithm, int raw, int key) {
        return algorithm.encryptionOperation(raw, key);
    }

    @Override
    public int findKey(CipherAlgorithm algorithm) {
        return algorithm.createKey();
    }


}
