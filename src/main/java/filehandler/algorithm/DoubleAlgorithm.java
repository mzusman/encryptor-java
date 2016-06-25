package filehandler.algorithm;

import exceptions.KeyException;

import java.util.ArrayList;

/**
 * Created by mzeus on 6/25/16.
 */
public class DoubleAlgorithm extends ListOfAlgorithm {
    public DoubleAlgorithm(ArrayList<AlgorithmKey> list) {
        super(list);
    }


    @Override
    public String getDescription() {
        return "double algorithm";
    }

    @Override
    public byte decryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        return algorithmKey.getCipherAlgorithm().decryptionOperation(raw, index, algorithmKey.getKey());
    }

    @Override
    public byte encryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        return algorithmKey.getCipherAlgorithm().encryptionOperation(raw, index, algorithmKey.getKey());
    }

}
