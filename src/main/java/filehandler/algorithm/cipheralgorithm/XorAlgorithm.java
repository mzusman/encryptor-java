package filehandler.algorithm.cipheralgorithm;

import exceptions.KeyException;
import exceptions.UnsupportedKeyNumberException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.SingleAlgorithm;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

/**
 * Created by mzeus on 30/05/16.
 */
@NoArgsConstructor
public class XorAlgorithm extends SingleAlgorithm {

    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        return (Integer) ((byte) (raw ^ key) & 0xff);
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        return (Integer) ((byte) (raw ^ key) & 0xff);
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return (byte) key.intValue() != 0;
    }

}
