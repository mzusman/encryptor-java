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
    CipherAlgorithmTest test = new CipherAlgorithmTest();

    @Test
    public void encrypt() throws Exception {
        for (int i = 1; i < 256; i++) {
            System.out.println(i);
            test.encrypt(caesarAlgorithm, i);
        }
    }

    @Test
    public void decrypt() throws Exception {
        for (int i = 1; i < 256; i++) {
            System.out.println(i);
            test.decrypt(caesarAlgorithm, i);

        }
    }

}