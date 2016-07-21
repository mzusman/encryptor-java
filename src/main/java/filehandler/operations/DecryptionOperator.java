package filehandler.operations;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.ToString;
import utils.StreamManager;
import utils.files.DecryptionFilesManager;
import utils.files.DirectoryFilesManager;
import utils.files.KeyFilesManager;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class DecryptionOperator extends Operator {

    @Inject
    public DecryptionOperator(StreamManager streamManager, KeyFilesManager keyFilesManager) {
        super(streamManager, keyFilesManager);
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
