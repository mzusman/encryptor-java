package filehandler.algorithm;

import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class ReverseAlgorithmTest {
    ReverseAlgorithm<Integer> algorithm = new ReverseAlgorithm<>();
     @Before
    public void WarmUp(){
        algorithm.pushAlgorithm(new XorAlgorithm());
        algorithm.generateEncryptKeys();
    }
    @Test
    public void decrypt() throws Exception {
        assertNotEquals(algorithm.decrypt(20,0,0),new Integer(20));
    }

    @Test
    public void encrypt() throws Exception {
        assertNotEquals(algorithm.decrypt(20,0,0),new Integer(20));
    }

    @Test
    public void encryptEqualsDecrypt(){
        assertEquals(algorithm.encrypt(algorithm.decrypt(20,0,0),0,0),new Integer(20));

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