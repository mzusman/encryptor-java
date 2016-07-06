package filehandler.algorithm.cipheralgorithm;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Created by mzeus on 30/05/16.
 */
@NoArgsConstructor
@EqualsAndHashCode
public class MultiplicationAlgorithm extends SingleAlgorithm {


    private byte procedureMwo(int raw, int key) {
        return (byte) (raw * key);
    }


    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        byte decKey = 0;
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            if (((byte) (i * getKey())) == 1) {
                decKey = (byte) i;
                break;
            }
        }
        return (int) procedureMwo(raw, decKey);
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        return (int) procedureMwo(raw, getKey());
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return !((byte) key.intValue() == 0 || key % 2 == 0);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
