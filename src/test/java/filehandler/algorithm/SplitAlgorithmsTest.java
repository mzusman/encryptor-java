package filehandler.algorithm;

import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class SplitAlgorithmsTest {
    SplitAlgorithms<Integer> algorithm = new SplitAlgorithms();

    @Before
    public void warmUp() {
        algorithm.pushAlgorithm(new XorAlgorithm());
        algorithm.generateEncryptKeys();
    }

    @Test
    public void decrypt() throws Exception {
        assertNotEquals(algorithm.decrypt(20, 0, 0), new Integer(20));
        assertNotEquals(algorithm.decrypt(20, 0, 1), new Integer(20));
        assertNotEquals(algorithm.decrypt(20, 0, 1), algorithm.decrypt(20, 0, 0));
    }

    @Test
    public void encryptEqualsDecrypt() {
        assertEquals(algorithm.encrypt(algorithm.decrypt(20, 0, 0), 0, 0).byteValue(), new Integer(20).byteValue());
    }


    @Test
    public void encrypt() throws Exception {
        assertNotEquals(algorithm.encrypt(20, 0, 0), new Integer(20));
        assertNotEquals(algorithm.encrypt(20, 0, 1), new Integer(20));
        assertNotEquals(algorithm.encrypt(20, 0, 1), algorithm.encrypt(20, 0, 0));
    }


}