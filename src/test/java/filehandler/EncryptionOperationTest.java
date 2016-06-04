package filehandler;

import filehandler.operations.EncryptionOperation;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.mockito.Mockito.verify;

/**
 * Created by Mor on 5/19/2016.
 */
public class EncryptionOperationTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private EncryptionOperation encryptionOperation = new EncryptionOperation();

//    @Test
//    public void act() throws Exception {
//        File file = temporaryFolder.newFile();
//        Assert.assertNotEquals(file, encryptionOperation.act(file, new NormalAlgorithm(new CaesarAlgorithm())));
//    }

    @Test
    public void getDescription() throws Exception {
        Assert.assertNotNull(encryptionOperation.getDescription());
    }


}