package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;

/**
 * Created by mzeus on 01/06/16.
 */
public class DoubleAlgorithm extends Algorithm {
    Algorithm algorithm2;

    public DoubleAlgorithm() {

    }

    public DoubleAlgorithm(CipherAlgorithm cipherAlgorithm, Algorithm algorithm) {
        super(cipherAlgorithm);
        this.algorithm2 = algorithm;
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        algorithm2.decryptionOperation(raw, algorithm2.createKey());
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

    @Override
    public int createKey() {
        return algorithm.createKey();
    }

    @Override
    public String getDescription() {
        return "perform a double algorithm";
    }
}
