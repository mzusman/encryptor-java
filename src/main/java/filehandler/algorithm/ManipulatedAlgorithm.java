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
@AllArgsConstructor
public abstract class ManipulatedAlgorithm implements CipherAlgorithm {
    @Getter
    @Setter
    CipherAlgorithm cipherAlgorithm;


    @Override
    public void checkKey(int key) throws KeyException {
        cipherAlgorithm.checkKey(key);
    }

    @Override
    public int createKey() {
        return cipherAlgorithm.createKey();
    }

    @Override
    public String getDescription() {
        return cipherAlgorithm.getDescription();
    }

}
