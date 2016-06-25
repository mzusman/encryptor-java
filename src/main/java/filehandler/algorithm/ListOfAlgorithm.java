package filehandler.algorithm;

import exceptions.KeyException;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

/**
 * Created by mzeus on 6/25/16.
 */
@AllArgsConstructor
public abstract class ListOfAlgorithm implements CipherAlgorithm {
    ArrayList<AlgorithmKey> list;

    public abstract byte decryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i);


    public abstract byte encryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i);


    @Override
    public byte decryptionOperation(int raw, int index, int key) {
        byte dec = (byte) raw;
        for (AlgorithmKey algorithmKey :
                list) {
            dec = decryptionOperation(dec, index, algorithmKey, list.indexOf(algorithmKey));
        }
        return dec;
    }


    @Override
    public byte encryptionOperation(int raw, int index, int key) {
        byte enc = (byte) raw;
        for (AlgorithmKey algorithmKey :
                list) {
            enc = encryptionOperation(enc, index, algorithmKey, list.indexOf(algorithmKey));
        }
        return enc;
    }

    @Override
    public void checkIfKeyIsValid(Integer key) throws KeyException {
        for (AlgorithmKey algorithmKey :
                list) {
            algorithmKey.getCipherAlgorithm().checkIfKeyIsValid(algorithmKey.getKey());
        }
    }
}
