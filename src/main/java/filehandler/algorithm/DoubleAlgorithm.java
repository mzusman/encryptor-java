package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

/**
 * Created by mzeus on 01/06/16.
 */
public class DoubleAlgorithm extends Algorithm {

    @Override
    public int exceptedSize() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "preform a double algorithm";
    }
}
