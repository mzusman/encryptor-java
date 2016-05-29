package filehandler.algorithm;

import lombok.Cleanup;
import org.junit.Test;
import org.mockito.Mock;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 29/05/16.
 */
public class CaesarAlgorithmTest {
    CaesarAlgorithm caesarAlgorithm = new CaesarAlgorithm();
    byte[] bytes;

    @Test
    public void encrypt() throws Exception {
        PipedOutputStream pout = new PipedOutputStream();
        InputStream in = new PipedInputStream(pout);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        pout.write("test".getBytes());
        System.out.println("test".getBytes());
        pout.flush();
        pout.close();
        caesarAlgorithm.encrypt(in, out,129);
        System.out.println(baos.toByteArray());
        bytes = baos.toByteArray();
        assertNotEquals("test", (baos.toString()));

    }

    @Test
    public void decrypt() throws Exception {
        encrypt();
        PipedOutputStream pout = new PipedOutputStream();
        InputStream in = new PipedInputStream(pout);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        pout.write(bytes);
        pout.flush();
        pout.close();
        caesarAlgorithm.decrypt(in, out,129);
        System.out.println(baos.toString());
        assertEquals("test", (baos.toString()));
    }

}