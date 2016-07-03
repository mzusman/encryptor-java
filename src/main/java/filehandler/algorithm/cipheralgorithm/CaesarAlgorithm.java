package filehandler.algorithm.cipheralgorithm;

import lombok.NoArgsConstructor;

/**
 * Created by mzeus on 29/05/16.
 */
@NoArgsConstructor
public class CaesarAlgorithm extends SingleAlgorithm {


    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        return raw - getKey().byteValue();
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        return raw + getKey().byteValue();
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return (byte) key.intValue() != 0;
    }

}
