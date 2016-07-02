package filehandler.algorithm.cipheralgorithm;

import filehandler.algorithm.SingleAlgorithm;

/**
 * Created by mzeus on 29/05/16.
 */
public class CaesarAlgorithm extends SingleAlgorithm {


    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        return (Integer) (raw - key.byteValue());
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        return (Integer) (raw + key.byteValue());
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return (byte) key.intValue() != 0;
    }

}
