package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzeus on 30/05/16.
 */
@NoArgsConstructor
public class Algorithm implements CipherAlgorithm {
    @Getter
    @Setter
    List<CipherAlgorithm> algorithms = new ArrayList<>();


    public Algorithm addAlgorithm(CipherAlgorithm cipherAlgorithm) {
        algorithms.add(cipherAlgorithm);
        return this;
    }

    public int exceptedSize() {
        return 1;
    }


    @Override
    public byte decryptionOperation(int raw, int key) {
        return algorithms.get(0).decryptionOperation(raw, key);
    }

    @Override
    public byte encryptionOperation(int raw, int key) {
        return algorithms.get(0).encryptionOperation(raw, key);
    }

    @Override
    public void checkKey(int key) throws KeyException {
        algorithms.get(0).checkKey(key);
    }

    @Override
    public int createKey() {
        return algorithms.get(0).createKey();
    }

    @Override
    public String getDescription() {
        return algorithms.get(0).getDescription();
    }
}
