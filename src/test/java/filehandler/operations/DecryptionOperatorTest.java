package filehandler.operations;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class DecryptionOperatorTest {
    DecryptionOperator decryptionOperator;
    @Rule
    TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void warmUp() throws IOException {
        File file = folder.newFile("aaa.txt");
        decryptionOperator = new DecryptionOperator(file);
    }


}