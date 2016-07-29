package domain.algorithm.cipheralgorithm;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class CaesarAlgorithmTest {
    CaesarAlgorithm caesarAlgorithm = new CaesarAlgorithm();

    @Test
    public void encryptEqualsDecrypt() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> System.out.println("hello"));
        caesarAlgorithm.generateEncryptKeys();
        assertEquals(caesarAlgorithm.encrypt(caesarAlgorithm.decrypt((byte) 20, (byte) 0, 0), (byte) 0, 0), new Byte((byte) 20));
    }

    @Test
    public void decrypt() throws Exception {
        caesarAlgorithm.generateEncryptKeys();
        assertNotEquals(caesarAlgorithm.decrypt((byte) 20, (byte) 20, 0), new Byte((byte) 20));

    }

    @Test
    public void encrypt() throws Exception {
        caesarAlgorithm.generateEncryptKeys();
        assertNotEquals(caesarAlgorithm.decrypt((byte) 20, (byte) 20, 0), new Byte((byte) 20));
    }

    @Test
    public void checkIfKeyIsValid() throws Exception {
        assertFalse(caesarAlgorithm.checkIfKeyIsValid((byte) 0));
        assertTrue(caesarAlgorithm.checkIfKeyIsValid((byte) 1));
    }

}