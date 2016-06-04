package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import utils.Selectable;

/**
 * Created by mzeus on 01/06/16.
 */
public class SplitAlgorithm extends Algorithm {


    public SplitAlgorithm() {

    }

    @Override
    public int exceptedSize() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "perform split algorithm";
    }

}
