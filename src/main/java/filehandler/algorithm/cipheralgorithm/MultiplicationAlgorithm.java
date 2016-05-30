package filehandler.algorithm.cipheralgorithm;

import exceptions.KeyException;
import exceptions.UnsupportedKeyNumberException;


/**
 * Created by mzeus on 30/05/16.
 */
public class MultiplicationAlgorithm implements CipherAlgorithm {


    private byte procedureMwo(int raw, int key) {
        return (byte) (raw * key);
    }

    @Override
    public String getDescription() {
        return "Multiplication Algorithm";
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        byte decKey = 0;
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {

            if (((byte) (i * key)) == 1) {
                System.out.println(i);
                decKey = (byte) i;
                break;
            }
        }

        return procedureMwo(raw, decKey);
    }


    @Override
    public byte encryptionOperation(int raw, int key) {
        return procedureMwo(raw, key);
    }

    @Override
    public void checkKey(int key) throws KeyException {
        if ((byte) key == 0 || key % 2 == 0)
            throw new UnsupportedKeyNumberException(key);
    }
}
