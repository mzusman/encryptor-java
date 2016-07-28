package utils.files;

import filehandler.algorithm.Algorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
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
public class KeyFilesManagerTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    public KeyFilesManager manager;

    @Before
    public void warmUp() throws IOException {
        manager = new KeyFilesManager(folder.newFile());
    }

    @Test
    public void getOutFile() throws Exception {
        File file = manager.getOutFile();
        assertNotNull(file);
        System.out.println(file.getName());
    }

    @Test
    public void writeAlgorithmsToFile() throws Exception {
        Algorithm algorithm = new XorAlgorithm();
        manager.writeAlgorithmsToFile(algorithm);
        assertNotNull(manager.readAlgorithmsFromFile());
        assertEquals(algorithm, manager.readAlgorithmsFromFile());
    }

    @Test
    public void readAlgorithmsFromFile() throws Exception {
        //tested in #writeAlgorithmsToFile()
        System.out.println(3%5);
    }

}