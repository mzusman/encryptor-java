package filehandler.algorithm;

import exceptions.KeyException;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mzeus on 6/25/16.
 */
@NoArgsConstructor
@Data
public abstract class ListOfAlgorithms implements SingleAlgorithm<Integer>, Serializable {

    private ArrayList<AlgorithmKey> list;

    public abstract byte decryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i);

    public abstract int createEncryptionKeys();

    public abstract byte encryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i);

    public abstract int wantedSize();

    public byte decryptionOperation(Integer raw, int index) {
        return decryptionOperation(raw, index, 0);
    }

    public byte encryptionOperation(Integer raw, int index) {
        return encryptionOperation(raw, index, 0);
    }

    @Override
    public byte decryptionOperation(Integer raw, int index, Integer key) {

    }

    public ListOfAlgorithms addAlgorithm(SingleAlgorithm algorithm) {
        if (algorithm != null && list.size() < wantedSize())
            list.add(new AlgorithmKey(algorithm, -1));
        return this;
    }


    @Override
    public byte encryptionOperation(Integer raw, int index, Integer key) {

    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return list.stream().allMatch((a) -> a.getSingleAlgorithm().checkIfKeyIsValid(a.getKey()));
    }

    public void setDecryptionKey(int key, int algoIndex) {
        list.get(algoIndex).setKey(key);
    }

}

