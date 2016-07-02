package filehandler.algorithm;

import java.util.ArrayList;

/**
 * Created by mzeus on 7/2/16.
 */
public class NormalAlgorithm extends ListOfAlgorithms {


    @Override
    public byte decryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        return algorithmKey.getCipherAlgorithm().decryptionOperation(raw, index, algorithmKey.getKey());
    }

    @Override
    public byte encryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        return algorithmKey.getCipherAlgorithm().encryptionOperation(raw, index, algorithmKey.getKey());
    }

    @Override
    public int wantedSize() {
        return 1;
    }
}
