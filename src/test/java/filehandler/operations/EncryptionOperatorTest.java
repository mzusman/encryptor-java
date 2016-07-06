package filehandler.operations;

import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;
import utils.files.FilesManager;

import java.io.File;
import java.io.IOException;
import java.io.NotSerializableException;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class EncryptionOperatorTest {
    EncryptionOperator operator;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void warmUp() throws IOException {
        File file = folder.newFile("aaa.txt");
        operator = new EncryptionOperator(file);
    }

    @Test(expected = NotSerializableException.class)
    public void fillKeys() throws Exception {
        XorAlgorithm xorAlgorithm = Mockito.mock(XorAlgorithm.class);
        operator.fillKeys(xorAlgorithm);
        Mockito.verify(xorAlgorithm, Mockito.times(1)).generateEncryptKeys();
    }

    @Test
    public void run() throws Exception {
        XorAlgorithm xorAlgorithm = Mockito.mock(XorAlgorithm.class);
        operator.run(xorAlgorithm);
    }

}