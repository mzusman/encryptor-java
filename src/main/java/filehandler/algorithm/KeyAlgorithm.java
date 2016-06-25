package filehandler.algorithm;

import exceptions.KeyException;

import java.util.List;


/**
 * Created by mzeus on 31/05/16.
 */
public interface KeyAlgorithm<S extends Integer> {
    boolean checkIfKeyIsValid(S key) ;
}
