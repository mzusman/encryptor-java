package filehandler.algorithm.cipheralgorithm;

import filehandler.algorithm.CipherAlgorithm;

/**
 * Created by mzeus on 29/05/16.
 */
public class CaesarAlgorithm implements CipherAlgorithm {


    @Override
    public byte decryptionOperation(int raw, int index, int key) {
        return (byte) (raw - Integer.valueOf(key).byteValue());
    }

    @Override
    public byte encryptionOperation(int raw, int index, int key) {
        return (byte) (raw + Integer.valueOf(key).byteValue());
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return (byte) key.intValue() != 0;
    }

}
