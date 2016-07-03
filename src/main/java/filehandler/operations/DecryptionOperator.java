package filehandler.operations;

import filehandler.algorithm.Algorithm;
import utils.files.DecryptionFilesManager;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class DecryptionOperator extends Operator {


    public DecryptionOperator(File inputFile) {
        super(inputFile);
        setStreamManager(new DecryptionFilesManager(inputFile));
    }



    @Override
    public byte operate(Algorithm<Integer> algorithm, int raw, int index) {
        return algorithm.decrypt(raw, 0, index).byteValue();
    }

    @Override
    public void fillKeys(Algorithm<Integer> algorithm) throws IOException, ClassNotFoundException {
        algorithm = getKeyFilesManager().readAlgorithmsFromFile();
    }
}
