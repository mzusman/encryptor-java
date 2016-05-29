package filehandler.algorithm;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 29/05/16.
 */
public interface CipherAlgorithm {
    void encrypt(InputStream in, OutputStream out, int key);

    void decrypt(InputStream in, OutputStream out, int key );
}
