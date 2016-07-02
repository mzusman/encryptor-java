package filehandler.algorithm;

import java.util.ArrayList;

/**
 * Created by mzeus on 01/06/16.
 */
public class SplitAlgorithms extends ListOfAlgorithms {


    @Override
    public byte decryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        int dec = raw;
        if (index % 2 == i)
            dec = algorithmKey.getCipherAlgorithm().decryptionOperation(raw, index, algorithmKey.getKey());
        return (byte) dec;

    }

    @Override
    public byte encryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        int dec = raw;
        if (index % 2 == i)
            dec = algorithmKey.getCipherAlgorithm().decryptionOperation(raw, index, algorithmKey.getKey());
        return (byte) dec;
    }

    @Override
    public int wantedSize() {
        return 2;
    }

}
