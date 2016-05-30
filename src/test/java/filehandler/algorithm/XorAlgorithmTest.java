package filehandler.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 30/05/16.
 */
public class XorAlgorithmTest {
    XorAlgorithm xorAlgorithm = new XorAlgorithm();
    CipherAlgorithmTest algorithm = new CipherAlgorithmTest();

    @Test
    public void encrypt() throws Exception {
        for (int i = 1; i < 256; i++) {
            System.out.println(i);
            algorithm.encrypt(xorAlgorithm, i);
        }
    }

    @Test
    public void decrypt() throws Exception {
        for (int i = 1; i < 256; i++) {
            algorithm.decrypt(xorAlgorithm,i);
        }
    }

}