package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;

/**
 * Created by mzeus on 01/06/16.
 */
public class DoubleAlgorithm implements CipherAlgorithm {
    CipherAlgorithm cipherAlgorithm;

    @Override
    public String getDescription() {
        return "preform a double algorithm";
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        return 0;
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return 0;
    }

    @Override
    public void checkKey(int key) throws KeyException {

    }

    @Override
    public int createKey() {
        return 0;
    }
}
