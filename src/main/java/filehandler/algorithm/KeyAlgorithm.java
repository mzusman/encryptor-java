package filehandler.algorithm;

import exceptions.KeyException;


/**
 * Created by mzeus on 31/05/16.
 */
public interface KeyAlgorithm {
    void checkKey(int key) throws KeyException;

    int createKey();
}
