package domain.algorithm;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

/**
 * runs a given algorithm in reverse - encrypt instead of decrypt
 * and decrypt instead of encrypt
 *
 * @param <T> the type parameter
 */
@EqualsAndHashCode
@XmlRootElement
@NoArgsConstructor
public class ReverseAlgorithm<T> implements Algorithm<T> {

    private Algorithm<T> algorithm;

    @Override
    public int numberOfAlgorithms() {
        return 1;
    }

    @Override
    public int numberOfKeys() {
        return 0;
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

    /**
     * This method override the {@link ExtendedAlgorithm#decryptionOperation(int, int)}
     * implementation to make it run in reverse - decryption operation is now encryption
     * and vice versa.
     *
     * @param raw          raw byte to encode
     * @param algorithmKey key used for encode
     * @return the encoded bye
     */


    @Override
    public T decrypt(T raw, T key, int streamIndex) {
        return algorithm.encrypt(raw, key, streamIndex);
    }


    /**
     * This method is similar to {@link #decryptionOperation(int, int)}
     */
    @Override
    public T encrypt(T raw, T key, int streamIndex) {
        return algorithm.decrypt(raw, key, streamIndex);
    }

    @Override
    public void pushAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public T getKey() {
        return algorithm.getKey();
    }

    @Override
    public T getKey(Algorithm algorithm, int index) {
        return this.algorithm.getKey(algorithm, index);
    }

    @Override
    public T getKey(int index) {
        return algorithm.getKey(index);
    }

    @Override
    public boolean generateEncryptKeys() {
        return algorithm.generateEncryptKeys();
    }

    @Override
    public void setDecryptionKey(T key, int index, Algorithm algorithm) {
        this.algorithm.setDecryptionKey(key, index, algorithm);
    }

    @Override
    public boolean checkIfKeyIsValid(T key) {
        return algorithm.checkIfKeyIsValid(key);
    }

    @Override
    public String toString() {
        return "run a " + getClass().getSimpleName();
    }

}
