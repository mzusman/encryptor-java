package filehandler.algorithm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 30/05/16.
 */
public class MultiplicationAlgorithm implements CipherAlgorithm {

    @Override
    public void encrypt(InputStream in, OutputStream out, int key) {
        procedureMwo(in, out, key);
    }

    @Override
    public void decrypt(InputStream in, OutputStream out, int key) {
        int raw;
        byte dec;
        byte decKey = 0;
        //finding the decryption key
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            if (((byte) (i * key)) == 1) {
                System.out.println(i);
                decKey = (byte) i;
                break;
            }

        }
        procedureMwo(in, out, decKey);

    }


    private void procedureMwo(InputStream in, OutputStream out, int key) {

        int raw;
        byte enc;
        try {
            while ((raw = in.read()) != -1) {
                enc = (byte) (raw * key);
                out.write(enc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
