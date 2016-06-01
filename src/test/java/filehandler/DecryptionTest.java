package filehandler;

import filehandler.algorithm.NormalAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.operations.Decryption;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Created by mzeus on 29/05/16.
 */
public class DecryptionTest {


    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private Decryption decryption = new Decryption();

    @Test
    public void getDescription() throws Exception {

        Assert.assertNotNull(decryption.getDescription());
    }

    @Test
    public void act() throws Exception {
        File file = temporaryFolder.newFile("test.encrypted");
        InputStream inputStream = new ByteArrayInputStream("42".getBytes());
        System.setIn(inputStream);
        NormalAlgorithm normalAlgorithm = new NormalAlgorithm(new CaesarAlgorithm());
        Assert.assertNotEquals(decryption.act(file, normalAlgorithm), file);
    }

}