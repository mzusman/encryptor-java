package domain.algorithm;


import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Created by mzeus on 30/05/16.
 */
@XmlRootElement
@NoArgsConstructor
public class MultiplicationAlgorithm extends AbstractAlgorithm {

    private byte procedureMwo(int raw, int key) {
        return (byte) (raw * key);
    }


    @Override
    public Byte decrypt(Byte raw, Byte key, int streamIndex) {
        byte decKey = 0;
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            if (((byte) (i * getKey())) == 1) {
                decKey = (byte) i;
                break;
            }
        }
        return procedureMwo(raw, decKey);
    }

    @Override
    public Byte encrypt(Byte raw, Byte key, int streamIndex) {
        return procedureMwo(raw, getKey());
    }

    @Override
    public boolean checkIfKeyIsValid(Byte key) {
        return !((byte) key.intValue() == 0 || key % 2 == 0);
    }
}
