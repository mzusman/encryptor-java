package filehandler.algorithm;

/**
 * Created by mzeus on 7/2/16.
 */
public interface Algorithm<T> {
    int numberOfAlgorithms();

    T decrypt(T raw, T key, int streamIndex);

    T encrypt(T raw, T key, int streamIndex);

    void pushAlgorithm(Algorithm algorithm);

    T getKey(Algorithm algorithm, int index);

    boolean generateEncryptKeys();

    void setDecryptionKey(T key, Algorithm algorithm);

    boolean checkIfKeyIsValid(T key);
}
