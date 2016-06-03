package filehandler;

import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import filehandler.operations.Encryption;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.hamcrest.core.Is.is;

/**
 * Created by mzeus on 30/05/16.
 */
public class FileHandlerTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void getDescription() throws Exception {
        //ignored
    }

    @Test
    public void handleFile() throws Exception {
        new FileHandler(new Encryption(), folder.newFile(), System.out::println).handleFile(new NormalAlgorithm(new XorAlgorithm()));
    }

    @Test
    public void showFile() throws Exception {

    }

}