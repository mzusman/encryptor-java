package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by mzeus on 31/05/16.
 */
public class AlgorithmOnceTest {
    @Test
    public void decryptionOperation() throws Exception {

        CipherAlgorithm cipherAlgorithm = mock(CipherAlgorithm.class);
        AlgorithmOnce algorithmOnce = new AlgorithmOnce(cipherAlgorithm);
        algorithmOnce.decryptionOperation(anyInt(), anyInt());
        verify(cipherAlgorithm, times(1)).decryptionOperation(anyInt(), anyInt());

    }

    @Test
    public void encryptionOperation() throws Exception {

        CipherAlgorithm cipherAlgorithm = mock(CipherAlgorithm.class);
        AlgorithmOnce algorithmOnce = new AlgorithmOnce(cipherAlgorithm);
        algorithmOnce.encryptionOperation(anyInt(), anyInt());
        verify(cipherAlgorithm, times(1)).encryptionOperation(anyInt(), anyInt());

    }

    @Test(expected = KeyException.class)
    public void checkKey() throws Exception {
        new AlgorithmOnce(new MultiplicationAlgorithm()).checkKey(256);
        new AlgorithmOnce(new XorAlgorithm()).checkKey(256);
        new AlgorithmOnce(new CaesarAlgorithm()).checkKey(256);
    }

}