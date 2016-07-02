package filehandler.algorithm;

/**
 * Created by mzeus on 29/05/16.
 */
public interface CipherAlgorithm<T> extends KeyAlgorithm {

    byte decryptionOperation(T raw, int index, T key);

    byte encryptionOperation(T raw, int index, T key);

}
