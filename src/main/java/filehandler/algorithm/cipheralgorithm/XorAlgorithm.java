package filehandler.algorithm.cipheralgorithm;

/**
 * Created by mzeus on 30/05/16.
 */
public class XorAlgorithm implements CipherAlgorithm {


    @Override
    public String getDescription() {
        return "Xor Algorithm";
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        return (byte) ((byte) (raw ^ key) & 0xff);
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return (byte) ((byte) (raw ^ key) & 0xff);
    }
}
