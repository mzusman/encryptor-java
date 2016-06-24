package filehandler.algorithm;

import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;

/**
 * Created by mzeus on 01/06/16.
 */
public class SplitManipulatedAlgorithm extends ManipulatedAlgorithm {


    public SplitManipulatedAlgorithm(CipherAlgorithm cipherAlgorithm) {
        super(cipherAlgorithm);
    }


    @Override
    public String getDescription() {
        return "perform split algorithm";
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        return 0;
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return 0;
    }
}
