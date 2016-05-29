package filehandler;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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

        new FileHandler(new Encryption(), folder.newFile()).handleFile();
    }

    @Test
    public void showFile() throws Exception {

    }

}