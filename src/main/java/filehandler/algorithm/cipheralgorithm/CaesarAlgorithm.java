package filehandler.algorithm.cipheralgorithm;

import filehandler.algorithm.CipherAlgorithm;

/**
 * Created by mzeus on 29/05/16.
 */
public class CaesarAlgorithm implements CipherAlgorithm<Integer> {


    @Override
    public byte decryptionOperation(Integer raw, int index, Integer key) {
        return (byte) (raw - key.byteValue());
    }

    @Override
    public byte encryptionOperation(Integer raw, int index, Integer key) {
        return (byte) (raw + key.byteValue());
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return (byte) key.intValue() != 0;
    }

}
