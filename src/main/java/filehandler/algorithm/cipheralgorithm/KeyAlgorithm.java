package filehandler.algorithm.cipheralgorithm;

import exceptions.KeyException;


/**
 * Created by mzeus on 31/05/16.
 */
interface KeyAlgorithm {
    void checkKey(int key) throws KeyException;

    int createKey();
}
