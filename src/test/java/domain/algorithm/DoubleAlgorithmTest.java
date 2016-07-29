package domain.algorithm;

import domain.algorithm.cipheralgorithm.CaesarAlgorithm;
import domain.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by mzeus on 7/3/16.
 */
public class DoubleAlgorithmTest {

    DoubleAlgorithm<Byte> algorithm = new DoubleAlgorithm<>();

    @Before
    public void WarmUp() {
        algorithm.pushAlgorithm(new XorAlgorithm());
        algorithm.pushAlgorithm(new CaesarAlgorithm());
        algorithm.generateEncryptKeys();
    }

    @Test
    public void decrypt() throws Exception {
        assertNotEquals(algorithm.decrypt((byte) 20, (byte) 0, 0).byteValue(), new Integer(20).byteValue());

    }

    @Test
    public void encryptEqualsDecrypt() {
        assertEquals(algorithm.encrypt(algorithm.decrypt((byte) 20, (byte) 0, 0), (byte) 0, 0).byteValue(), new Integer(20).byteValue());
    }

    @Test
    public void encrypt() throws Exception {
        assertNotEquals(algorithm.encrypt((byte) 20, (byte) 0, 0), new Integer(20));
    }

    @Test
    public void pushAlgorithm() throws Exception {

    }

    @Test
    public void getKey() throws Exception {

    }

    @Test
    public void generateEncryptKeys() throws Exception {
        DoubleAlgorithm doubleAlgorithm = new DoubleAlgorithm();
        XorAlgorithm xorAlgorithm = Mockito.mock(XorAlgorithm.class);
        CaesarAlgorithm caesarAlgorithm = Mockito.mock(CaesarAlgorithm.class);
        doubleAlgorithm.pushAlgorithm(xorAlgorithm);
        doubleAlgorithm.pushAlgorithm(caesarAlgorithm);
        assertTrue(doubleAlgorithm.generateEncryptKeys());
        Mockito.verify(xorAlgorithm, times(1)).generateEncryptKeys();
        Mockito.verify(caesarAlgorithm, times(1)).generateEncryptKeys();

    }

}