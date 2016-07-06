package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.ToString;
import utils.files.DecryptionFilesManager;
import utils.files.DirectoryFilesManager;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class DecryptionOperator extends Operator {


    public DecryptionOperator(File inputFile) {
        super(inputFile);
        if (inputFile.isDirectory())
            setStreamManager(new DirectoryFilesManager(new DecryptionFilesManager(inputFile)));
        else setStreamManager(new DecryptionFilesManager(inputFile));
    }


    @Override
    public byte operate(Algorithm<Integer> algorithm, int raw, int index) {
        return algorithm.decrypt(raw, 0, index).byteValue();
    }

    @Override
    public Algorithm<Integer> fillKeys(Algorithm<Integer> algorithm) throws IOException, ClassNotFoundException, KeyException {
        Algorithm<Integer> tmpAlgorithm = getKeyFilesManager().readAlgorithmsFromFile();
        if (tmpAlgorithm.getClass().equals(algorithm.getClass()))
            return tmpAlgorithm;
        else throw new KeyException("file was not encrypted with selected algorithm");
    }

    @Override
    public String toString() {
        return "Decrypt a file/folder";
    }
}
