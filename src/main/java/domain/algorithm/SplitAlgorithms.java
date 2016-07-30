package domain.algorithm;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzeus on 01/06/16.
 *
 * @param <T> the type parameter
 */
@EqualsAndHashCode
@XmlRootElement
@NoArgsConstructor
public class SplitAlgorithms<T> implements Algorithm<T> {

    private Algorithm<T> algorithm;

    private List<T> keys = new ArrayList<T>();

    @Override
    public String toString() {
        return "run a " + getClass().getSimpleName();
    }


    /**
     * Gets algorithm.
     *
     * @return the algorithm
     */
    @XmlElement(type = Object.class)
    public Algorithm<T> getAlgorithm() {
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
    public T decrypt(T raw, T key, int streamIndex) {
        if (streamIndex % 2 == 1)
            algorithm.setDecryptionKey(keys.get(0), 0, algorithm);
        else
            algorithm.setDecryptionKey(keys.get(1), 0, algorithm);
        return algorithm.decrypt(raw, keys.get(0), streamIndex);
    }

    @Override
    public T encrypt(T raw, T key, int streamIndex) {
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
    public T getKey() {
        return keys.get(0);
    }

    @Override
    public T getKey(Algorithm algorithm, int index) {
        return keys.get(index);
    }

    @Override
    public T getKey(int index) {
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
    public void setDecryptionKey(T key, int index, Algorithm algorithm) {
        keys.add(index, key);
    }


    @Override
    public boolean checkIfKeyIsValid(T key) {
        return keys.stream().allMatch((k) -> algorithm.checkIfKeyIsValid(k));
    }

}
