package filehandler.algorithm;

import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Test;

/**
 * Created by mzeus on 30/05/16.
 */
public class XorAlgorithmTest {
    XorAlgorithm xorAlgorithm = new XorAlgorithm();
    CipherAlgorithmTest algorithm = new CipherAlgorithmTest();

    @Test
    public void encrypt() throws Exception {
//        for (int i = 1; i < 256; i++) {
//            System.out.println(i);
//            algorithm.encryptTest(xorAlgorithm, i);
//        }
        System.out.println((byte)256);
    }

    @Test
    public void decrypt() throws Exception {
        for (int i = 1; i < 256; i++) {
            algorithm.decryptTest(xorAlgorithm, i);
        }
    }

}