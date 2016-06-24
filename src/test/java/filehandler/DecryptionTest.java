package filehandler;

import filehandler.algorithm.ManipulatedAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.operations.DecryptionOperation;
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
    private DecryptionOperation decryptionOperation = new DecryptionOperation();

    @Test
    public void getDescription() throws Exception {

        Assert.assertNotNull(decryptionOperation.getDescription());
    }

    @Test
    public void act() throws Exception {
        File file = temporaryFolder.newFile("test.encrypted");
        InputStream inputStream = new ByteArrayInputStream("42".getBytes());
        System.setIn(inputStream);
        ManipulatedAlgorithm normalManipulatedAlgorithm = new ManipulatedAlgorithm().addAlgorithm(new CaesarAlgorithm());
        Assert.assertNotEquals(decryptionOperation.init(System.out::println, file, normalManipulatedAlgorithm), file);
    }

}