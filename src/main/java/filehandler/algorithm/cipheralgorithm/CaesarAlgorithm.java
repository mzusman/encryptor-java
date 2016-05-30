package filehandler.algorithm.cipheralgorithm;

/**
 * Created by mzeus on 29/05/16.
 */
public class CaesarAlgorithm implements CipherAlgorithm {



    @Override
    public String getDescription() {
        return "Caesar Algorithm";
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        return (byte) (raw - Integer.valueOf(key).byteValue());
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return (byte) (raw + Integer.valueOf(key).byteValue());
    }
}
