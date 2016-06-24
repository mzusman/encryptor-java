package filehandler.algorithm.cipheralgorithm;

import filehandler.algorithm.KeyAlgorithm;
import utils.Selectable;

/**
 * Created by mzeus on 29/05/16.
 */
public interface CipherAlgorithm extends Selectable, KeyAlgorithm {

    byte decryptionOperation(int raw, int key);

    byte encryptionOperation(int raw, int key);

}
