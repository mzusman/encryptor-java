package filehandler.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 30/05/16.
 */
public class MultiplicationAlgorithmTest {
    CipherAlgorithmTest test = new CipherAlgorithmTest();
    MultiplicationAlgorithm algorithm = new MultiplicationAlgorithm();

    @Test
    public void encrypt() throws Exception {
        test.encrypt(algorithm, 98);
    }

    @Test
    public void decrypt() throws Exception {
        test.decrypt(algorithm, 96);
    }

}