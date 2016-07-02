package filehandler.algorithm;

import java.util.ArrayList;

/**
 * Created by mzeus on 01/06/16.
 */
public class SplitAlgorithms implements Algorithm<Integer> {


    @Override
    public byte decryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        int dec = raw;
        if (index % 2 == i)
            dec = algorithmKey.getSingleAlgorithm().decryptionOperation(raw, index, algorithmKey.getKey());
        return (byte) dec;

    }

    @Override
    public byte encryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        int dec = raw;
        if (index % 2 == i)
            dec = algorithmKey.getSingleAlgorithm().decryptionOperation(raw, index, algorithmKey.getKey());
        return (byte) dec;
    }

    @Override
    public int numberOfAlgorithms() {
        return 2;
    }

    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        return null;
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        return null;
    }

    @Override
    public void pushAlgorithm(Algorithm algorithm) {

    }

    @Override
    public Integer getKey(Algorithm algorithm, int index) {
        return null;
    }

    @Override
    public boolean generateEncryptKeys() {
        return false;
    }

    @Override
    public void setDecryptionKey(Integer key, Algorithm algorithm) {

    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return false;
    }
}
