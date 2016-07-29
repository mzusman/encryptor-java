package domain.algorithm;


import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by mzeus on 7/2/16.
 */
@XmlRootElement
@NoArgsConstructor
public class DoubleAlgorithm<T> implements Algorithm<T> {

    private ArrayList<Algorithm<T>> algorithms = new ArrayList<>();

    @XmlElementWrapper
    @XmlAnyElement(lax = true)
    public ArrayList<Algorithm<T>> getAlgorithms() {
        return algorithms;
    }

    @Override
    public int numberOfAlgorithms() {
        return 2;
    }

    @Override
    public int numberOfKeys() {
        return 2;
    }

    @Override
    public T decrypt(T raw, T key, int streamIndex) {
        T dec = raw;
        for (int i = algorithms.size() - 1; i >= 0; i--) {
            Algorithm<T> algorithm = algorithms.get(i);
            dec = algorithm.decrypt(dec, key, streamIndex);
        }
        return dec;
    }

    @Override
    public T encrypt(T raw, T key, int streamIndex) {
        T enc = raw;
        for (int i = 0; i < algorithms.size(); i++) {
            Algorithm<T> algorithm = algorithms.get(i);
            enc = algorithm.encrypt(enc, key, streamIndex);
        }
        return enc;
    }

    @Override
    public void pushAlgorithm(Algorithm algorithm) {
        algorithms.add(algorithm);
    }

    @Override
    public T getKey() {
        return algorithms.get(0).getKey();
    }

    @Override
    public T getKey(Algorithm algorithm, int index) {
        Algorithm<T> tmpAlgorithm = algorithms.stream().findAny().filter((a) -> a.equals(algorithm)).get();
        if (tmpAlgorithm != null)
            return tmpAlgorithm.getKey();
        return getKey();
    }

    @Override
    public T getKey(int index) {
        return algorithms.get(index).getKey();
    }

    @Override
    public boolean generateEncryptKeys() {
        algorithms.forEach(Algorithm::generateEncryptKeys);
        return true;
    }

    @Override
    public void setDecryptionKey(T key, int index, Algorithm algorithm) {
        Optional<Algorithm<T>> optional = algorithms.stream().findAny().filter((a) -> a.equals(algorithm));
        if (optional.isPresent())
            optional.get().setDecryptionKey(key, 0, algorithm);
    }

    @Override
    public boolean checkIfKeyIsValid(T key) {
        return algorithms.stream().allMatch((a) -> checkIfKeyIsValid(key));
    }

    @Override
    public String toString() {
        return "run a " + getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleAlgorithm)) return false;

        DoubleAlgorithm that = (DoubleAlgorithm) o;

        return algorithms.equals(that.algorithms);

    }

    @Override
    public int hashCode() {
        return algorithms.hashCode();
    }
}
