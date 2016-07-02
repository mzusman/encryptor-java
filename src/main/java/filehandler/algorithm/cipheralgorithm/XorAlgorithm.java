package filehandler.algorithm.cipheralgorithm;

import exceptions.KeyException;
import exceptions.UnsupportedKeyNumberException;
import filehandler.algorithm.CipherAlgorithm;

import java.util.List;
import java.util.Random;

/**
 * Created by mzeus on 30/05/16.
 */
public class XorAlgorithm implements CipherAlgorithm<Integer> {


    @Override
    public byte decryptionOperation(Integer raw, int index, Integer key) {
        return (byte) ((byte) (raw ^ key) & 0xff);
    }

    @Override
    public byte encryptionOperation(Integer raw, int index, Integer key) {
        return (byte) ((byte) (raw ^ key) & 0xff);
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return (byte) key.intValue() != 0;
    }

}
