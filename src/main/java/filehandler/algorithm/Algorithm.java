package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 30/05/16.
 */
@AllArgsConstructor
public abstract class Algorithm implements CipherAlgorithm {
    CipherAlgorithm algorithm;


    public void encrypt(InputStream in, OutputStream out, int key) throws KeyException, IOException {
        checkKey(key);
        int raw;
        byte enc;
        while ((raw = in.read()) != -1) {
            enc = encryptionOperation(raw, key);
            out.write(enc);
        }
    }

    public void decrypt(InputStream in, OutputStream out, int key) throws KeyException, IOException {
        checkKey(key);
        int raw;
        byte dec;
        while ((raw = in.read()) != -1) {
            dec = decryptionOperation(raw, key);
            out.write(dec);
        }

    }


}
