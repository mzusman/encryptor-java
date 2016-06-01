package commandline;

import filehandler.algorithm.Algorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import filehandler.operations.Decryption;
import filehandler.operations.Encryption;
import filehandler.FileHandler;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PipedOutputStream;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandlerTest {
    private Decryption decryption = mock(Decryption.class);
    private Encryption encryption = mock(Encryption.class);
    private XorAlgorithm algorithm = mock(XorAlgorithm.class);
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void addAlgorithm() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        assertEquals(cliHandler, cliHandler.addAlgorithm(algorithm));
        assertEquals(cliHandler, cliHandler.addAlgorithm(null));
        assertEquals(cliHandler, cliHandler.addAlgorithm(algorithm));
        assertEquals(cliHandler, cliHandler.addAlgorithm(algorithm));
    }


    @Test
    public void getKey() throws Exception {
        for (int i = 1; i < 256; i++) {
            System.setIn(new ByteArrayInputStream((String.valueOf(i) + "\n").getBytes()));
            System.out.println(i);
            assertEquals(CliHandler.getInstance().getKey(), i);

        }
//        System.setIn(new ByteArrayInputStream(String.valueOf(0).getBytes()));
//        assertNotEquals(CliHandler.getInstance().getKey(), 0);

    }


    @Test
    public void addOption() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        assertEquals(cliHandler, cliHandler.addOption(decryption));
        assertEquals(cliHandler, cliHandler.addOption(encryption));
        assertEquals(cliHandler, cliHandler.addOption(null));
    }

    @Test
    public void handleArguments() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        FileHandler fileHandler = mock(FileHandler.class);
        File file = temporaryFolder.newFile();
        CaesarAlgorithm algorithm = new CaesarAlgorithm();
        cliHandler.addOption(encryption);
        cliHandler.addAlgorithm(algorithm);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        cliHandler.handleArguments(file.getPath());
        verify(encryption, times(1)).act(any(File.class), any(Algorithm.class));
    }


}