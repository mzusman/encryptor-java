package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzeus on 30/05/16.
 */
@AllArgsConstructor
public class Algorithm implements CipherAlgorithm{
    @Getter
    @Setter
    CipherAlgorithm algorithm;

    public Algorithm() {

    }


    @Override
    public byte decryptionOperation(int raw, int key) {
        return algorithm.decryptionOperation(raw,key);
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return algorithm.encryptionOperation(raw,key);
    }

    @Override
    public void checkKey(int key) throws KeyException {
        algorithm.checkKey(key);
    }

    @Override
    public int createKey() {
        return algorithm.createKey();
    }

    @Override
    public String getDescription() {
        return algorithm.getDescription();
    }
}
