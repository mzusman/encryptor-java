package filehandler.algorithm;

import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import org.junit.Test;

/**
 * Created by mzeus on 29/05/16.
 */
public class CaesarExtendedAlgorithmTest {
    CaesarAlgorithm caesarAlgorithm = new CaesarAlgorithm();
    CipherAlgorithmTest test = new CipherAlgorithmTest();

    @Test
    public void encrypt() throws Exception {
        for (int i = 1; i < 256; i++) {
            System.out.println(i);
            test.encryptTest(caesarAlgorithm, i);
        }
    }

    @Test
    public void decrypt() throws Exception {
        for (int i = 1; i < 256; i++) {
            System.out.println(i);
            test.decryptTest(caesarAlgorithm, i);

        }
    }

}