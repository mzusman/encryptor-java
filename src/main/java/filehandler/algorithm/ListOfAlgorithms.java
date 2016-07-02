package filehandler.algorithm;

import exceptions.KeyException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by mzeus on 6/25/16.
 */
@NoArgsConstructor
public abstract class ListOfAlgorithms implements CipherAlgorithm<Integer> {
    @Getter
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
        byte dec = (byte) raw.intValue();
        for (AlgorithmKey algorithmKey :
                list) {
            dec = decryptionOperation(dec, index, algorithmKey, list.indexOf(algorithmKey));
        }
        return dec;
    }

    public ListOfAlgorithms addAlgorithm(CipherAlgorithm algorithm) {
        if (algorithm != null && list.size() < wantedSize())
            list.add(new AlgorithmKey(algorithm, -1));
        return this;
    }


    @Override
    public byte encryptionOperation(Integer raw, int index, Integer key) {
        byte enc = (byte) raw.intValue();
        for (AlgorithmKey algorithmKey :
                list) {
            enc = encryptionOperation(enc, index, algorithmKey, list.indexOf(algorithmKey));
        }
        return enc;
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return list.stream().allMatch((a) -> a.getCipherAlgorithm().checkIfKeyIsValid(a.getKey()));
    }

    public void setDecryptionKey(int key, int algoIndex) {
        list.get(algoIndex).setKey(key);
    }

}

