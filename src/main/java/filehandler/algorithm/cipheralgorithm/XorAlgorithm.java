package filehandler.algorithm.cipheralgorithm;

import exceptions.KeyException;
import exceptions.UnsupportedKeyNumberException;

import java.util.Random;

/**
 * Created by mzeus on 30/05/16.
 */
public class XorAlgorithm implements CipherAlgorithm {


    @Override
    public String getDescription() {
        return "Xor NormalAlgorithm";
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        return (byte) ((byte) (raw ^ key) & 0xff);
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return (byte) ((byte) (raw ^ key) & 0xff);
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
