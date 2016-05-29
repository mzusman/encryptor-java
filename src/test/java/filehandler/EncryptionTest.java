package filehandler;

import filehandler.algorithm.CaesarAlgorithm;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Random;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Mor on 5/19/2016.
 */
public class EncryptionTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private Encryption encryption = new Encryption();

    @Test
    public void act() throws Exception {
        File file = temporaryFolder.newFile();
        Assert.assertNotEquals(file, encryption.act(file,new CaesarAlgorithm()));
    }

    @Test
    public void getDescription() throws Exception {
        Assert.assertNotNull(encryption.getDescription());
    }


}