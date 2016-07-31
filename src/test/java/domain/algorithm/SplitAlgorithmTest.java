package domain.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class SplitAlgorithmTest {
    SplitAlgorithm<Byte> algorithm = new SplitAlgorithm();

    @Before
    public void warmUp() {
        algorithm.pushAlgorithm(new XorAlgorithm());
        algorithm.generateEncryptKeys();
    }

    @Test
    public void decrypt() throws Exception {
        assertNotEquals(algorithm.decrypt((byte) 20, (byte) 0, 0), new Integer(20));
        assertNotEquals(algorithm.decrypt((byte) 20, (byte) 0, 1), new Integer(20));
        assertNotEquals(algorithm.decrypt((byte) 20, (byte) 0, 1), algorithm.decrypt((byte) 20, (byte) 0, 0));
    }

    @Test
    public void encryptEqualsDecrypt() {
        assertEquals(algorithm.encrypt(algorithm.decrypt((byte) 20, (byte) 0, 0), (byte) 0, 0).byteValue(), new Integer(20).byteValue());
    }


    @Test
    public void encrypt() throws Exception {
        assertNotEquals(algorithm.encrypt((byte) 20, (byte) 0, 0), new Integer(20));
        assertNotEquals(algorithm.encrypt((byte) 20, (byte) 0, 1), new Integer(20));
        assertNotEquals(algorithm.encrypt((byte) 20, (byte) 0, 1), algorithm.encrypt((byte) 20, (byte) 0, 0));
    }


}