package filehandler.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzeus on 01/06/16.
 */
public class SplitAlgorithms implements Algorithm<Integer> {

    Algorithm<Integer> algorithm;
    private List<Integer> keys;

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
        int dec;
        if (streamIndex % 2 == 0)
            dec = algorithm.decrypt(raw, keys.get(0), streamIndex);
        else
            dec = algorithm.decrypt(raw, keys.get(1), streamIndex);
        return dec;
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        int enc;
        if (streamIndex % 2 == 0)
            enc = algorithm.encrypt(raw, keys.get(0), streamIndex);
        else
            enc = algorithm.encrypt(raw, keys.get(1), streamIndex);
        return enc;
    }

    @Override
    public void pushAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Integer getKey(Algorithm algorithm, int index) {
        return keys.get(index);
    }

    @Override
    public boolean generateEncryptKeys() {
        algorithm.generateEncryptKeys();
        keys.add(0, algorithm.getKey(null, 0));
        algorithm.generateEncryptKeys();
        keys.add(1, algorithm.getKey(null, 0));
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
