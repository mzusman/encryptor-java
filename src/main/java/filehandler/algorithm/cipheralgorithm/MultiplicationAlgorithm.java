package filehandler.algorithm.cipheralgorithm;

/**
 * Created by mzeus on 30/05/16.
 */
public class MultiplicationAlgorithm implements CipherAlgorithm {


    private byte procedureMwo(int raw, int key) {
        return (byte) (raw * key);
    }

    @Override
    public String getDescription() {
        return "Multiplication Algorithm";
    }

    @Override
    public byte decryptionOperation(int raw, int key) {
        byte decKey = 0;
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {

            if (((byte) (i * key)) == 1) {
                System.out.println(i);
                decKey = (byte) i;
                break;
            }
        }

        return procedureMwo(raw, decKey);
    }


    @Override
    public byte encryptionOperation(int raw, int key) {
        return procedureMwo(raw, key);
    }
}
