package filehandler.algorithm;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by mzeus on 7/2/16.
 */
public interface Algorithm<T> extends Serializable {
    T getKey();

    T getKey(Algorithm algorithm, int index);

    T getKey(int index);

    int numberOfAlgorithms();

    int numberOfKeys();

    T decrypt(T raw, T key, int streamIndex);

    T encrypt(T raw, T key, int streamIndex);

    void pushAlgorithm(Algorithm algorithm);


    boolean generateEncryptKeys();

    void setDecryptionKey(T key, int index, Algorithm algorithm);

    boolean checkIfKeyIsValid(T key);
}
