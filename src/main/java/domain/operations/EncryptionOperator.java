package domain.operations;

import com.google.inject.Inject;
import domain.algorithm.Algorithm;
import utils.files.StreamManager;
import utils.files.KeyFilesManager;

import java.io.IOException;

/**
 * Created by Mor on 5/19/2016.
 */
public class EncryptionOperator extends AbstractOperation {


    @Inject
    public EncryptionOperator(StreamManager streamManager, KeyFilesManager keyFilesManager) {
        super(streamManager, keyFilesManager);
    }


    @Override
    public Byte operate(Algorithm<Byte> algorithm, Byte raw, int index) {
        return algorithm.encrypt(raw, (byte) 0, index);
    }

    @Override
    public Algorithm<Byte> fillKeys(Algorithm<Byte> algorithm) throws IOException {
        algorithm.generateEncryptKeys();
        getKeyFilesManager().writeAlgorithmsToFile(algorithm);
        return algorithm;
    }

    @Override
    public String toString() {
        return "Encrypt a file/folder";
    }
}
