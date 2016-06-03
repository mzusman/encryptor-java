package filehandler.algorithm.cipheralgorithm;

import exceptions.KeyException;
import exceptions.UnsupportedKeyNumberException;
import utils.Selectable;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 29/05/16.
 */
public interface CipherAlgorithm extends Selectable, KeyAlgorithm {

    byte decryptionOperation(int raw, int key);

    byte encryptionOperation(int raw, int key);

}
