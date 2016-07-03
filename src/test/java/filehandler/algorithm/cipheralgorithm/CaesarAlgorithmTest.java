package filehandler.algorithm.cipheralgorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class CaesarAlgorithmTest {
    CaesarAlgorithm caesarAlgorithm = new CaesarAlgorithm();

    @Test
    public void encryptEqualsDecrypt() {
        caesarAlgorithm.generateEncryptKeys();
        assertEquals(caesarAlgorithm.encrypt(caesarAlgorithm.decrypt(20, 0, 0), 0, 0), new Integer(20));
    }

    @Test
    public void decrypt() throws Exception {
        caesarAlgorithm.generateEncryptKeys();
        assertNotEquals(caesarAlgorithm.decrypt(20, 20, 0), new Integer(20));

    }

    @Test
    public void encrypt() throws Exception {
        caesarAlgorithm.generateEncryptKeys();
        assertNotEquals(caesarAlgorithm.decrypt(20, 20, 0), new Integer(20));
    }

    @Test
    public void checkIfKeyIsValid() throws Exception {
        assertFalse(caesarAlgorithm.checkIfKeyIsValid(0));
        assertTrue(caesarAlgorithm.checkIfKeyIsValid(1));
    }

}