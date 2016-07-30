package domain.algorithm;


import java.io.Serializable;

/**
 * Interface to the algorithm classes
 *
 * @param <T> the type parameter
 */
public interface Algorithm<T> extends Serializable {
    /**
     * Gets key that the algorithm is using.
     *
     * @return the key
     */
    T getKey();

    /**
     * Gets key that @{@link Algorithm} hold (in case there's more than 1 algorithm in
     * this object).
     *
     * @param algorithm the algorithm
     * @param index     the index - index of the key - in case if there's more than 1 key.
     * @return the key
     */
    T getKey(Algorithm algorithm, int index);

    /**
     * Gets key.
     *
     * @param index the index - index of the key - in case if there's more than 1 key.
     * @return the key
     */
    T getKey(int index);

    /**
     * Number of algorithms in the object.
     *
     * @return the int
     */
    int numberOfAlgorithms();

    /**
     * Number of keys in the object.
     *
     * @return the int
     */
    int numberOfKeys();

    /**
     * Decrypt operation.
     *
     * @param raw         the raw - raw element
     * @param key         the key - using the key
     * @param streamIndex the stream index
     * @return the t
     */
    T decrypt(T raw, T key, int streamIndex);

    /**
     * Encrypt .
     *
     * @param raw         the raw - raw element
     * @param key         the key
     * @param streamIndex the stream index
     * @return the t
     */
    T encrypt(T raw, T key, int streamIndex);

    /**
     * Push algorithm - in case this algorithm hold some other
     * algorithms - pushes the given algorithm into the list of
     * algorithms.
     *
     * @param algorithm the algorithm
     */
    void pushAlgorithm(Algorithm algorithm);

    /**
     * Generate encrypt keys boolean.
     *
     * @return the boolean
     */
    boolean generateEncryptKeys();

    /**
     * Sets decryption key.
     *
     * @param key       the key
     * @param index     the index
     * @param algorithm the algorithm
     */
    void setDecryptionKey(T key, int index, Algorithm algorithm);

    /**
     * Check if key is valid boolean.
     *
     * @param key the key
     * @return the boolean
     */
    boolean checkIfKeyIsValid(T key);

}
