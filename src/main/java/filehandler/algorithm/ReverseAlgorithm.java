package filehandler.algorithm;

import lombok.AllArgsConstructor;

/**
 * Created by mzeus on 01/06/16.
 */
@AllArgsConstructor
public class ReverseAlgorithm implements CipherAlgorithm {
    AlgorithmKey algorithmKey;
    /**
     * This method override the {@link ExtendedAlgorithm#decryptionOperation(int, int)}
     * implementation to make it run in reverse - decryption operation is now encryption
     * and vice versa.
     *
     * @param raw raw byte to encode
     * @param key key used for encode
     * @return the encoded bye
     */
    @Override
    public byte decryptionOperation(int raw, int index, int key) {
        return algorithmKey.getCipherAlgorithm().encryptionOperation(raw, index, key);
    }

    /**
     * This method is similar to {@link #decryptionOperation(int, int)}
     */
    @Override
    public byte encryptionOperation(int raw, int index, int key) {
        return algorithmKey.getCipherAlgorithm().decryptionOperation(raw, index, key);
    }


    @Override
    public String getDescription() {
        return "perform a reverse algorithm on : " + algorithmKey.getCipherAlgorithm().getDescription();
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return false;
    }
}
