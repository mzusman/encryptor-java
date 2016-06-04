package filehandler.algorithm;

import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.*;

/**
 * Created by mzeus on 31/05/16.
 */
public class AlgorithmTest {

//    @Test
//    public void encrypt() throws Exception {
//        CipherAlgorithm cipherAlgorithm = Mockito.mock(CipherAlgorithm.class);
//        Algorithm algorithm = new NormalAlgorithm(cipherAlgorithm);
//        for (int i = 255; i > 0; i--) {
//            PipedOutputStream pipedIn = new PipedOutputStream();
//            InputStream in = new PipedInputStream(pipedIn);
//            pipedIn.write(i);
//            pipedIn.close();
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            algorithm.encrypt(in, out, 100);
//            Mockito.verify(cipherAlgorithm, Mockito.times(1)).encryptionOperation(i, 100);
//            Mockito.verify(cipherAlgorithm, Mockito.times(0)).decryptionOperation(i, 100);
//        }
//    }

//    @Test
//    public void decrypt() throws Exception {
//        CipherAlgorithm cipherAlgorithm = Mockito.mock(CipherAlgorithm.class);
//        Algorithm algorithm = new NormalAlgorithm(cipherAlgorithm);
//        for (int i = 255; i > 0; i--) {
//            PipedOutputStream pipedIn = new PipedOutputStream();
//            InputStream in = new PipedInputStream(pipedIn);
//            pipedIn.write(i);
//            pipedIn.close();
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            algorithm.decrypt(in, out, 100);
//            Mockito.verify(cipherAlgorithm, Mockito.times(1)).decryptionOperation(i, 100);
//            Mockito.verify(cipherAlgorithm, Mockito.times(0)).encryptionOperation(i, 100);
//        }
//
//    }

}