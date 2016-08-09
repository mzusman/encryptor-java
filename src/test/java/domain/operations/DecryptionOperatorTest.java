package domain.operations;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import utils.files.DecryptionFilesManager;
import utils.files.KeyFilesManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 7/3/16.
 */
public class DecryptionOperatorTest {
    private DecryptionOperator decryptionOperator;
    @Rule
    TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void warmUp() throws IOException {
        File file = folder.newFile("aaa.txt");
        decryptionOperator = new DecryptionOperator(new DecryptionFilesManager(file),new KeyFilesManager(file));
    }


}