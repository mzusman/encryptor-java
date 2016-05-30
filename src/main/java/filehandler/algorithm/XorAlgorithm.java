package filehandler.algorithm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 30/05/16.
 */
public class XorAlgorithm implements CipherAlgorithm {

    @Override
    public void encrypt(InputStream in, OutputStream out, int key) {
        XorOperation(in, out, key);
    }

    @Override
    public void decrypt(InputStream in, OutputStream out, int key) {
        XorOperation(in, out, key);
    }

    private void XorOperation(InputStream in, OutputStream out, int key) {
        int raw;
        byte enc;
        try {
            while ((raw = in.read()) != -1) {
                enc = (byte) ((byte) (raw ^ key) & 0xff);
                out.write(enc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
