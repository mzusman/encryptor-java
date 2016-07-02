package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.CipherAlgorithm;
import filehandler.algorithm.ListOfAlgorithms;

import java.io.*;
import java.util.Random;

/**
 * Created by Mor on 5/19/2016.
 */
public class EncryptionOperation extends AbstractOperation {

    private static final String encrypted = ".encrypted";

    public EncryptionOperation() {

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
    public byte operate(ListOfAlgorithms algorithm, int raw, int index) {
        return algorithm.encryptionOperation(raw, index);
    }

    @Override
    public void findKey(ListOfAlgorithms algorithms) {
//        Random random = new Random(255);
//        int key = random.nextInt() + 1;
//        while (!algorithms.checkIfKeyIsValid(key))
//            key = random.nextInt() + 1;
        algorithms.createEncryptionKeys();
    }


}
