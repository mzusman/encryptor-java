package filehandler.algorithm;

import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import org.junit.Test;

/**
 * Created by mzeus on 30/05/16.
 */
public class MultiplicationNormalAlgorithmTest {
    CipherAlgorithmTest test = new CipherAlgorithmTest();
    MultiplicationAlgorithm algorithm = new MultiplicationAlgorithm();

    @Test
    public void encrypt() throws Exception {
        test.encryptTest(algorithm, 99);
    }

    @Test
    public void decrypt() throws Exception {
        test.decryptTest(algorithm, 99);
    }

}