package filehandler;

import commandline.CliHandler;
import filehandler.algorithm.CaesarAlgorithm;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
        InputStream inputStream = new ByteArrayInputStream("42\n".getBytes());
        System.setIn(inputStream);
        Assert.assertNotEquals(decryption.act(file, new CaesarAlgorithm()), file);
    }

}