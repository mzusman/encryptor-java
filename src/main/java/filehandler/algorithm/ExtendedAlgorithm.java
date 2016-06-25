package filehandler.algorithm;

import exceptions.KeyException;
import lombok.AllArgsConstructor;

import java.util.Random;

/**
 * Created by mzeus on 30/05/16.
 */
@AllArgsConstructor
public class ExtendedAlgorithm implements CipherAlgorithm {
    private AlgorithmKey algorithmKey;

    public byte decryptionOperation(int raw, int index) {
        return algorithmKey.getCipherAlgorithm().decryptionOperation(raw, index, algorithmKey.getKey());
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return algorithmKey.getCipherAlgorithm().checkIfKeyIsValid(key);
    }

    @Override
    public byte decryptionOperation(int raw, int index, int key) {
        return algorithmKey.getCipherAlgorithm().decryptionOperation(raw, index, key);
    }

    @Override
    public byte encryptionOperation(int raw, int index, int key) {
        return algorithmKey.getCipherAlgorithm().encryptionOperation(raw, index, key);
    }

    @Override
    public String getDescription() {
        return algorithmKey.getCipherAlgorithm().getDescription();
    }

}
