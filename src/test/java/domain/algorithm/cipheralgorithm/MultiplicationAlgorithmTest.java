package domain.algorithm.cipheralgorithm;

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
        assertEquals(algorithm.encrypt(algorithm.decrypt((byte) 20, (byte) 0, 0), (byte) 0, 0).byteValue(), new Integer(20).byteValue());
    }


    @Test
    public void checkIfKeyIsValid() throws Exception {
        assertFalse(algorithm.checkIfKeyIsValid((byte) 20));
        assertTrue(algorithm.checkIfKeyIsValid((byte) 1));

    }

}