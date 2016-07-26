package filehandler.algorithm.cipheralgorithm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class MultiplicationAlgorithmTest {
    MultiplicationAlgorithm algorithm = new MultiplicationAlgorithm();

    @Test
    public void encryptEqualsDecrypt() {
        algorithm.generateEncryptKeys();
        assertEquals(algorithm.encrypt(algorithm.decrypt(20, 0, 0), 0, 0).byteValue(), new Integer(20).byteValue());
    }

    @Test
    public void decrypt() throws Exception {
        algorithm.generateEncryptKeys();
        assertNotEquals(algorithm.decrypt(20, 20, 0).byteValue(), new Integer(20).byteValue());
    }

    @Test
    public void encrypt() throws Exception {
        algorithm.generateEncryptKeys();
        assertNotEquals(algorithm.encrypt(20, 20, 0).byteValue(), new Integer(20).byteValue());
    }

    @Test
    public void checkIfKeyIsValid() throws Exception {
        assertFalse(algorithm.checkIfKeyIsValid(20));
        assertTrue(algorithm.checkIfKeyIsValid(1));

    }

}