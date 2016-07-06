package utils.files;

import filehandler.algorithm.Algorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class DecryptionFilesManagerTest {
    DecryptionFilesManager manager;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void warmUp() throws IOException {
        manager = new DecryptionFilesManager(folder.newFile());
    }

    @Test
    public void getOutFile() throws Exception {
        File file  = manager.getOutFile();
        assertNotNull(file);
        System.out.println(file.getName());
    }

}