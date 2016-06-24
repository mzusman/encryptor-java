package filehandler.algorithm;

import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;

/**
 * Created by mzeus on 01/06/16.
 */
public class ReverseManipulatedAlgorithm extends ManipulatedAlgorithm {


    public ReverseManipulatedAlgorithm(CipherAlgorithm cipherAlgorithm) {
        super(cipherAlgorithm);
    }

    /**
     * This method override the {@link ManipulatedAlgorithm#decryptionOperation(int, int)}
     * implementation to make it run in reverse - decryption operation is now encryption
     * and vice versa.
     *
     * @param raw raw byte to encode
     * @param key key used for encode
     * @return the encoded bye
     */
    @Override
    public byte decryptionOperation(int raw, int key) {
        return cipherAlgorithm.encryptionOperation(raw, key);
    }

    /**
     * This method is similar to {@link #decryptionOperation(int, int)}
     */
    @Override
    public byte encryptionOperation(int raw, int key) {
        return cipherAlgorithm.decryptionOperation(raw, key);
    }


    @Override
    public String getDescription() {
        return "perform a reverse algorithm on : " + cipherAlgorithm.getDescription();
    }

}
