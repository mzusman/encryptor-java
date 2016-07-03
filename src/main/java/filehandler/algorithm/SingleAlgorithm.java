package filehandler.algorithm;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by mzeus on 29/05/16.
 */
@NoArgsConstructor
public class SingleAlgorithm implements Algorithm<Integer> {
    private int key;

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
    public Integer getKey(Algorithm algorithm, int index) {
        return key;
    }

    @Override
    public boolean generateEncryptKeys() {
        Random random = new Random(255);
        int key = random.nextInt() + 1;
        while (!checkIfKeyIsValid(key))
            key = random.nextInt() + 1;
        return true;

    }

    @Override
    public void setDecryptionKey(Integer key, int index, Algorithm algorithm) {
        if (algorithm.equals(algorithm))
            this.key = key;
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return false;
    }
}
