package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;

/**
 * Created by mzeus on 01/06/16.
 */
public class SplitAlgorithm extends Algorithm {

    private int evenKey = 0;
    private int counter = 0;

    public SplitAlgorithm() {

    }

    public SplitAlgorithm(CipherAlgorithm algorithm, int key) {
        super(algorithm);
        this.evenKey = key;
    }

    @Override
    public byte decryptionOperation(int raw, int oddKey) {
        byte dec;
        if (counter % 2 == 0) {
            dec = algorithm.decryptionOperation(raw, evenKey);
        }
        dec = algorithm.decryptionOperation(raw, oddKey);
        counter++;
        return dec;
    }

    @Override
    public byte encryptionOperation(int raw, int oddKey) {
        byte enc;
        if (counter % 2 == 0)
            enc = algorithm.encryptionOperation(raw, evenKey);
        enc = algorithm.encryptionOperation(raw, oddKey);
        counter++;
        return enc;
    }


    @Override
    public void checkKey(int key) throws KeyException {
        algorithm.checkKey(key);
    }

    @Override
    public int createKey() {
        return algorithm.createKey();
    }

    @Override
    public String getDescription() {
        return "perform split algorithm";
    }
}
