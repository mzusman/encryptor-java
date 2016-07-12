package filehandler.algorithm.cipheralgorithm;

import filehandler.algorithm.Algorithm;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by mzeus on 29/05/16.
 */
@EqualsAndHashCode
@XmlRootElement
public class SingleAlgorithm implements Algorithm<Integer>, Serializable {
    private int key = 0;

    public SingleAlgorithm() {

    }

    @Override
    public int numberOfKeys() {
        return 1;
    }

    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        return 0;
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        return 0;
    }

    @Override
    public int numberOfAlgorithms() {
        return 0;
    }


    @Override
    public void pushAlgorithm(Algorithm algorithm) {
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public Integer getKey(Algorithm algorithm, int index) {
        return key;
    }

    @Override
    public Integer getKey(int index) {
        return key;
    }

    @Override
    public boolean generateEncryptKeys() {
        Random random = new Random();
        key = random.nextInt(255) + 1;
        while (!checkIfKeyIsValid(key))
            key = random.nextInt(255) + 1;
        return true;
    }

    @Override
    public void setDecryptionKey(Integer key, int index, Algorithm algorithm) {
        this.key = key;
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return false;
    }

    @Override
    public String toString() {
        return "run a " + getClass().getSimpleName();
    }
}
