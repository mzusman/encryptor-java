package filehandler.algorithm;

import exceptions.KeyException;
import exceptions.UnsupportedKeyNumberException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;


/**
 * Created by mzeus on 30/05/16.
 */
public class AlgorithmOnce extends Algorithm {

    public AlgorithmOnce(CipherAlgorithm algorithm) {
        super(algorithm);
    }

    @Override
    public String getDescription() {
        return algorithm.getDescription();
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        return algorithm.decryptionOperation(raw, key);
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return algorithm.encryptionOperation(raw, key);
    }


    @Override
    public void checkKey(int key) throws KeyException {
        algorithm.checkKey(key);
    }
}
