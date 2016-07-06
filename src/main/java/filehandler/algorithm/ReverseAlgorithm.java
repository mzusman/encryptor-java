package filehandler.algorithm;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by mzeus on 01/06/16.
 */
@NoArgsConstructor
public class ReverseAlgorithm<T> implements Algorithm<T> {

    AlgorithmKey<T> algorithmKey;

    @Override
    public int numberOfAlgorithms() {
        return 1;
    }

    @Override
    public int numberOfKeys() {
        return 0;
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
        return algorithmKey.getSingleAlgorithm().encrypt(raw, key, streamIndex);
    }


    /**
     * This method is similar to {@link #decryptionOperation(int, int)}
     */
    @Override
    public T encrypt(T raw, T key, int streamIndex) {
        return algorithmKey.getSingleAlgorithm().decrypt(raw, key, streamIndex);
    }

    @Override
    public void pushAlgorithm(Algorithm algorithm) {
        algorithmKey = new AlgorithmKey<T>(algorithm, null);
    }

    @Override
    public T getKey() {
        return algorithmKey.getKey();
    }

    @Override
    public T getKey(Algorithm algorithm, int index) {
        return algorithmKey.getKey();
    }

    @Override
    public T getKey(int index) {
        return algorithmKey.getKey();
    }

    @Override
    public boolean generateEncryptKeys() {
        return algorithmKey.getSingleAlgorithm().generateEncryptKeys();
    }

    @Override
    public void setDecryptionKey(T key, int index, Algorithm algorithm) {
        algorithmKey.setKey(key);
    }

    @Override
    public boolean checkIfKeyIsValid(T key) {
        return algorithmKey.getSingleAlgorithm().checkIfKeyIsValid(key);
    }

    @Override
    public String toString() {
        return "run a " + getClass().getSimpleName();
    }

}
