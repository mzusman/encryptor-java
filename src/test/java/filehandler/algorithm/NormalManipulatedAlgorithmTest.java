package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by mzeus on 31/05/16.
 */
public class NormalManipulatedAlgorithmTest {
    @Test
    public void decryptionOperation() throws Exception {

        CipherAlgorithm cipherAlgorithm = mock(CipherAlgorithm.class);
        ManipulatedAlgorithm manipulatedAlgorithm = new ManipulatedAlgorithm(cipherAlgorithm);
        manipulatedAlgorithm.decryptionOperation(anyInt(), anyInt());
        verify(cipherAlgorithm, times(1)).decryptionOperation(anyInt(), anyInt());

    }

    @Test
    public void encryptionOperation() throws Exception {

        CipherAlgorithm cipherAlgorithm = mock(CipherAlgorithm.class);
        ManipulatedAlgorithm manipulatedAlgorithm = new ManipulatedAlgorithm(cipherAlgorithm);
        manipulatedAlgorithm.encryptionOperation(anyInt(), anyInt());
        verify(cipherAlgorithm, times(1)).encryptionOperation(anyInt(), anyInt());

    }

    @Test(expected = KeyException.class)
    public void checkKey() throws Exception {
        new ManipulatedAlgorithm(new MultiplicationAlgorithm()).checkKey(256);
        new ManipulatedAlgorithm(new XorAlgorithm()).checkKey(256);
        new ManipulatedAlgorithm(new CaesarAlgorithm()).checkKey(256);
    }

}