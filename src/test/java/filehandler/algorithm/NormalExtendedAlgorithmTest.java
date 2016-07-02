package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by mzeus on 31/05/16.
 */
public class NormalExtendedAlgorithmTest {
    @Test
    public void decryptionOperation() throws Exception {

        SingleAlgorithm singleAlgorithm = mock(SingleAlgorithm.class);
        ExtendedAlgorithm extendedAlgorithm = new ExtendedAlgorithm(singleAlgorithm);
        extendedAlgorithm.decryptionOperation(anyInt(), anyInt());
        verify(singleAlgorithm, times(1)).decryptionOperation(anyInt(), anyInt());

    }

    @Test
    public void encryptionOperation() throws Exception {

        SingleAlgorithm singleAlgorithm = mock(SingleAlgorithm.class);
        ExtendedAlgorithm extendedAlgorithm = new ExtendedAlgorithm(singleAlgorithm);
        extendedAlgorithm.encryptionOperation(anyInt(), anyInt());
        verify(singleAlgorithm, times(1)).encryptionOperation(anyInt(), anyInt());

    }

    @Test(expected = KeyException.class)
    public void checkKey() throws Exception {
        new ExtendedAlgorithm(new MultiplicationAlgorithm()).checkIfKeyIsValid(256);
        new ExtendedAlgorithm(new XorAlgorithm()).checkIfKeyIsValid(256);
        new ExtendedAlgorithm(new CaesarAlgorithm()).checkIfKeyIsValid(256);
    }

}