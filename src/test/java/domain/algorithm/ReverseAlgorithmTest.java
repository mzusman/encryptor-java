package domain.algorithm;

import domain.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class ReverseAlgorithmTest {
    ReverseAlgorithm<Byte> algorithm = new ReverseAlgorithm<>();

    @Before
    public void WarmUp() {
        algorithm.pushAlgorithm(new XorAlgorithm());
        algorithm.generateEncryptKeys();
    }

    @Test
    public void decrypt() throws Exception {
        assertNotEquals(algorithm.decrypt((byte) 20, (byte) 0, 0), new Byte((byte) 20));
    }

    @Test
    public void encrypt() throws Exception {
        assertNotEquals(algorithm.decrypt((byte) 20, (byte) 0, 0), new Byte((byte) 20));
    }

    @Test
    public void encryptEqualsDecrypt() {
        assertEquals(algorithm.encrypt(algorithm.decrypt((byte) 20, (byte) 0, 0), (byte) 0, 0), new Byte((byte) 20));

    }

    @Test
    public void pushAlgorithm() throws Exception {

    }

    @Test
    public void getKey() throws Exception {

    }

    @Test
    public void generateEncryptKeys() throws Exception {

    }

    @Test
    public void setDecryptionKey() throws Exception {

    }

    @Test
    public void checkIfKeyIsValid() throws Exception {

    }

}