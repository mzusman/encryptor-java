package filehandler.algorithm;

import exceptions.KeyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * Created by mzeus on 6/25/16.
 */
@AllArgsConstructor
public class AlgorithmKey {
    @Getter
    private CipherAlgorithm cipherAlgorithm;
    @Setter @Getter
    private int key;


}
