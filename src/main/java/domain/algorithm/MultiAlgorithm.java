package domain.algorithm;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Optional;

/**
 * The type Multi algorithm - contains 1 or more algorithms
 *
 * @param <T> the type parameter
 */
@XmlRootElement
@NoArgsConstructor
@EqualsAndHashCode
public abstract class MultiAlgorithm<T> implements Algorithm<T> {
    private ArrayList<Algorithm<T>> algorithms = new ArrayList<>();

    /**
     * Gets algorithms.
     *
     * @return the algorithms
     */
    @XmlElementWrapper
    @XmlAnyElement(lax = true)
    public ArrayList<Algorithm<T>> getAlgorithms() {
        return algorithms;
    }

    @Override
    public int numberOfKeys() {
        return numberOfAlgorithms();
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
        for (Algorithm<T> algorithm : algorithms) {
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

}
