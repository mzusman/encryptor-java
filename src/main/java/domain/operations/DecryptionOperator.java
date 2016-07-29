package domain.operations;

import com.google.inject.Inject;
import exceptions.KeyException;
import domain.algorithm.Algorithm;
import utils.StreamManager;
import utils.files.KeyFilesManager;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class DecryptionOperator extends AbstractOperation {

    @Inject
    public DecryptionOperator(StreamManager streamManager, KeyFilesManager keyFilesManager) {
        super(streamManager, keyFilesManager);
    }


    @Override
    public Byte operate(Algorithm<Byte> algorithm, Byte raw, int index) {
        return algorithm.decrypt(raw, (byte) 0, index);
    }

    @Override
    public Algorithm<Byte> fillKeys(Algorithm<Byte> algorithm) throws IOException, ClassNotFoundException, KeyException {
        Algorithm tmpAlgorithm = getKeyFilesManager().readAlgorithmsFromFile();
        if (tmpAlgorithm.getClass().equals(algorithm.getClass()))
            return tmpAlgorithm;
        else throw new KeyException("file was not encrypted with selected algorithm");
    }

    @Override
    public String toString() {
        return "Decrypt a file/folder";
    }
}
