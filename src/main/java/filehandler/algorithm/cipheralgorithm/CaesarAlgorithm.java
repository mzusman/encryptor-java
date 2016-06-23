package filehandler.algorithm.cipheralgorithm;

import exceptions.KeyException;
import exceptions.UnsupportedKeyNumberException;

import java.util.Random;

/**
 * Created by mzeus on 29/05/16.
 */
public class CaesarAlgorithm implements CipherAlgorithm {


    @Override
    public String getDescription() {
        return "Caesar NormalAlgorithm";
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        return (byte) (raw - Integer.valueOf(key).byteValue());
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return (byte) (raw + Integer.valueOf(key).byteValue());
    }

    @Override
    public void checkKey(int key) throws KeyException {
        if ((byte) key == 0)
            throw new UnsupportedKeyNumberException(key);
    }

    @Override
    public int createKey() {
        return new Random().nextInt(255) + 1;
    }
}
