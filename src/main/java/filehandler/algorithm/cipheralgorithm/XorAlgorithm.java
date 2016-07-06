package filehandler.algorithm.cipheralgorithm;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by mzeus on 30/05/16.
 */
@NoArgsConstructor
@EqualsAndHashCode
public class XorAlgorithm extends SingleAlgorithm {

    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        return (Integer) ((byte) (raw ^ getKey()) & 0xff);
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        return (Integer) ((byte) (raw ^ getKey()) & 0xff);
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return (byte) key.intValue() != 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
