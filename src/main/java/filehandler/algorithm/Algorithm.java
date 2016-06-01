package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 30/05/16.
 */
@AllArgsConstructor
public abstract class Algorithm implements CipherAlgorithm {
    @Getter
    @Setter
    CipherAlgorithm algorithm;

    public Algorithm() {

    }

    public void encrypt(InputStream in, OutputStream out, int key) throws KeyException, IOException {
        int raw;
        byte enc;
        try {
            while ((raw = in.read()) != -1) {
                enc = encryptionOperation(raw, key);
                out.write(enc);
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file");
        }
    }

    public void decrypt(InputStream in, OutputStream out, int key) throws KeyException, IOException {
        checkKey(key);
        int raw;
        byte dec;
        try {
            while ((raw = in.read()) != -1) {
                dec = decryptionOperation(raw, key);
                out.write(dec);
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file");
        }

    }


}
