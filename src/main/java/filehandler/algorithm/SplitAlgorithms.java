package filehandler.algorithm;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzeus on 01/06/16.
 */
@XmlRootElement
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

    @XmlElement(type = Object.class)
    public Algorithm<Integer> getAlgorithm() {
        return algorithm;
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
            algorithm.setDecryptionKey(keys.get(0), 0, algorithm);
        else
            algorithm.setDecryptionKey(keys.get(1), 0, algorithm);
        return algorithm.decrypt(raw, keys.get(0), streamIndex);
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        if (streamIndex % 2 == 1)
            algorithm.setDecryptionKey(keys.get(0), 0, algorithm);
        else
            algorithm.setDecryptionKey(keys.get(1), 0, algorithm);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SplitAlgorithms)) return false;

        SplitAlgorithms that = (SplitAlgorithms) o;

        if (algorithm != null ? !algorithm.equals(that.algorithm) : that.algorithm != null) return false;
        if (keys != null ? !keys.equals(that.keys) : that.keys != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = algorithm != null ? algorithm.hashCode() : 0;
        result = 31 * result + (keys != null ? keys.hashCode() : 0);
        return result;
    }
}
