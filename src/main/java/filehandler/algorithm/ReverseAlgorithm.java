package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;

/**
 * Created by mzeus on 01/06/16.
 */
public class ReverseAlgorithm extends Algorithm {

    public ReverseAlgorithm() {

    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        return algorithms.get(0).encryptionOperation(raw, key);
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return algorithms.get(0).decryptionOperation(raw, key);
    }


    @Override
    public String getDescription() {
        return "perform a reverse algorithm";
    }

}
