package filehandler;

import filehandler.algorithm.CaesarAlgorithm;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Random;

import static org.junit.Assert.*;

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
        Assert.assertNotEquals(decryption.act(file,new CaesarAlgorithm()), file);
    }

}