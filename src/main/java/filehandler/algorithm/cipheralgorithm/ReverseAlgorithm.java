package filehandler.algorithm.cipheralgorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;

/**
 * Created by mzeus on 01/06/16.
 */
@AllArgsConstructor
public class ReverseAlgorithm implements CipherAlgorithm {
    CipherAlgorithm algorithm;

    public ReverseAlgorithm() {

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
    public String getDescription() {
        return "perform a reverse algorithm";
    }

    @Override
    public void checkKey(int key) throws KeyException {
        algorithm.checkKey(key);
    }

    @Override
    public int createKey() {
        return algorithm.createKey();
    }
}
