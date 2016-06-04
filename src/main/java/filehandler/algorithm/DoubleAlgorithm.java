package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

/**
 * Created by mzeus on 01/06/16.
 */
public class DoubleAlgorithm extends Algorithm {

    @Override
    public byte encryptionOperation(int raw, int key) {
        byte newValue = 0;
        for (CipherAlgorithm cipherAlgorithm :
                algorithms) {
            newValue = cipherAlgorithm.encryptionOperation(raw,key);
        }
        return newValue;
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        byte newValue = 0;
        for (CipherAlgorithm cipherAlgorithm :
                algorithms) {
            newValue = cipherAlgorithm.decryptionOperation(raw,key);
        }
        return newValue;
    }

}
