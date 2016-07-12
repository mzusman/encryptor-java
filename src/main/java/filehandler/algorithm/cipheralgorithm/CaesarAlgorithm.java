package filehandler.algorithm.cipheralgorithm;

import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mzeus on 29/05/16.
 */
@EqualsAndHashCode
@XmlRootElement
public class CaesarAlgorithm extends SingleAlgorithm {

    public CaesarAlgorithm() {

    }


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

    @Override
    public String toString() {
        return super.toString();
    }
}
