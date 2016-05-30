package filehandler.algorithm;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 30/05/16.
 */
public class CipherAlgorithmTest {

    byte[] bytes;


    public void encryptTest(CipherAlgorithm algorithm, int key) throws IOException, KeyException {

        PipedOutputStream pout = new PipedOutputStream();
        InputStream in = new PipedInputStream(pout);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        pout.write("test".getBytes());
        System.out.println("test".getBytes());
        pout.flush();
        pout.close();
        Algorithm algorithm1 = new AlgorithmOnce(algorithm);
        algorithm1.encrypt(in, out, key);
        System.out.println(baos.toByteArray());
        bytes = baos.toByteArray();
        assertNotEquals("test", (baos.toString()));

    }

    /**
     * decryption test - encrypt the information and then decrypt it to check if
     * the information is still the same
     */

    public void decryptTest(CipherAlgorithm algorithm, int key) throws IOException, KeyException {

        encryptTest(algorithm, key);
        PipedOutputStream pout = new PipedOutputStream();
        InputStream in = new PipedInputStream(pout);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        pout.write(bytes);
        pout.flush();
        pout.close();
        Algorithm algorithm1 = new AlgorithmOnce(algorithm);
        algorithm1.decrypt(in, out, key);
        System.out.println(baos.toString());
        assertEquals("test", (baos.toString()));

    }
}