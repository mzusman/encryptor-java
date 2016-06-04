package filehandler;

import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.operations.Encryption;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.mockito.Mockito.verify;

/**
 * Created by Mor on 5/19/2016.
 */
public class EncryptionTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private Encryption encryption = new Encryption();

//    @Test
//    public void act() throws Exception {
//        File file = temporaryFolder.newFile();
//        Assert.assertNotEquals(file, encryption.act(file, new NormalAlgorithm(new CaesarAlgorithm())));
//    }

    @Test
    public void getDescription() throws Exception {
        Assert.assertNotNull(encryption.getDescription());
    }


}