package filehandler.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzeus on 01/06/16.
 */
public class SplitAlgorithms implements Algorithm<Integer> {

    private Algorithm<Integer> algorithm;
    private List<Integer> keys;

    @Override
    public String toString() {
        return "run a " + getClass().getSimpleName();
    }


    public SplitAlgorithms() {
        keys = new ArrayList<>();
    }

    @Override
    public int numberOfAlgorithms() {
        return 1;
    }

    @Override
    public int numberOfKeys() {
        return 2;
    }

    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        if (streamIndex % 2 == 1)
            algorithm.setDecryptionKey(keys.get(0), 0, null);
        else
            algorithm.setDecryptionKey(keys.get(1), 0, null);
        return algorithm.decrypt(raw, keys.get(0), streamIndex);
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        if (streamIndex % 2 == 1)
            algorithm.setDecryptionKey(keys.get(0), 0, null);
        else
            algorithm.setDecryptionKey(keys.get(1), 0, null);
        return algorithm.encrypt(raw, keys.get(0), streamIndex);
    }

    @Override
    public void pushAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Integer getKey() {
        return keys.get(0);
    }

    @Override
    public Integer getKey(Algorithm algorithm, int index) {
        return keys.get(index);
    }

    @Override
    public Integer getKey(int index) {
        return keys.get(index);
    }

    @Override
    public boolean generateEncryptKeys() {
        for (int i = 0; i < numberOfKeys(); i++) {
            algorithm.generateEncryptKeys();
            keys.add(algorithm.getKey());
            System.out.println(keys);
        }
        return true;

    }

    @Override
    public void setDecryptionKey(Integer key, int index, Algorithm algorithm) {
        keys.add(index, key);
    }


    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return keys.stream().allMatch((k) -> algorithm.checkIfKeyIsValid(k));
    }
}
