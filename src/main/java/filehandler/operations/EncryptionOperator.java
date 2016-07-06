package filehandler.operations;

import filehandler.algorithm.Algorithm;
import utils.files.EncryptionFilesManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mor on 5/19/2016.
 */
public class EncryptionOperator extends Operator {


    public EncryptionOperator(File inputFile) {
        super(inputFile);
        setStreamManager(new EncryptionFilesManager(inputFile));
    }

    @Override
    public byte operate(Algorithm<Integer> algorithm, int raw, int index) {
        return algorithm.encrypt(raw, 0, index).byteValue();
    }

    @Override
    public Algorithm<Integer> fillKeys(Algorithm<Integer> algorithm) throws IOException {
        algorithm.generateEncryptKeys();
        getKeyFilesManager().writeAlgorithmsToFile(algorithm);
        return algorithm;
    }

    @Override
    public String toString() {
        return "Encrypt a file/folder";
    }
}
