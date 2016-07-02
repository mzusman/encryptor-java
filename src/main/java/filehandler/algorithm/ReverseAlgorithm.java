package filehandler.algorithm;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

/**
 * Created by mzeus on 01/06/16.
 */
public class ReverseAlgorithm extends ListOfAlgorithms {

    /**
     * This method override the {@link ExtendedAlgorithm#decryptionOperation(int, int)}
     * implementation to make it run in reverse - decryption operation is now encryption
     * and vice versa.
     *
     * @param raw raw byte to encode
     * @param algorithmKey key used for encode
     * @return the encoded bye
     */

    @Override
    public byte decryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        return algorithmKey.getCipherAlgorithm().encryptionOperation(raw, index, algorithmKey.getKey());
    }


    /**
     * This method is similar to {@link #decryptionOperation(int, int)}
     */
    @Override
    public byte encryptionOperation(int raw, int index, AlgorithmKey algorithmKey, int i) {
        return algorithmKey.getCipherAlgorithm().decryptionOperation(raw, index, algorithmKey.getKey());
    }

    @Override
    public int wantedSize() {
        return 1;
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return false;
    }

}
