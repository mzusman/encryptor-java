package filehandler.algorithm.cipheralgorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class XorAlgorithmTest {
    XorAlgorithm algorithm = new XorAlgorithm();

    @Test
    public void encryptEqualsDecrypt() {
        algorithm.generateEncryptKeys();
        assertEquals(algorithm.encrypt(algorithm.decrypt(20, 0, 0), 0, 0), new Integer(20));
    }

    @Test
    public void decrypt() throws Exception {

        algorithm.generateEncryptKeys();
        assertNotEquals(algorithm.decrypt(20, 20, 0), new Integer(20));
    }

    @Test
    public void encrypt() throws Exception {
        algorithm.generateEncryptKeys();
        assertNotEquals(algorithm.decrypt(20, 20, 0), new Integer(20));

    }

    @Test
    public void checkIfKeyIsValid() throws Exception {
        assertFalse(algorithm.checkIfKeyIsValid(0));
        assertTrue(algorithm.checkIfKeyIsValid(1));

    }

}