package filehandler.algorithm;

/**
 * Created by mzeus on 29/05/16.
 */
public interface CipherAlgorithm extends KeyAlgorithm {

    byte decryptionOperation(int raw, int index, int key);

    byte encryptionOperation(int raw, int index, int key);

}
