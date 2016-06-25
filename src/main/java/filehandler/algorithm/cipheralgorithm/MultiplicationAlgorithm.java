package filehandler.algorithm.cipheralgorithm;

import filehandler.algorithm.CipherAlgorithm;


/**
 * Created by mzeus on 30/05/16.
 */
public class MultiplicationAlgorithm implements CipherAlgorithm{


    private byte procedureMwo(int raw, int key) {
        return (byte) (raw * key);
    }


    @Override
    public byte decryptionOperation(int raw, int index, int key) {
        byte decKey = 0;
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            if (((byte) (i * key)) == 1) {
                decKey = (byte) i;
                break;
            }
        }
        return procedureMwo(raw, decKey);
    }


    @Override
    public byte encryptionOperation(int raw, int index, int key) {
        return procedureMwo(raw, key);
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return !((byte) key.intValue() == 0 || key % 2 == 0);
    }


}
