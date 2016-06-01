package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;

/**
 * Created by mzeus on 01/06/16.
 */
public class ReverseAlgorithm extends Algorithm {
    public ReverseAlgorithm() {

    }

    public ReverseAlgorithm(CipherAlgorithm algorithm) {
        super(algorithm);
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        return algorithm.encryptionOperation(raw, key);
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return algorithm.decryptionOperation(raw, key);
    }

    @Override
    public void checkKey(int key) throws KeyException {
        algorithm.checkKey(key);
    }

    @Override
    public int createKey() {
        return algorithm.createKey();
    }

    @Override
    public String getDescription() {
        return "perform a reverse algorithm";
    }
}
