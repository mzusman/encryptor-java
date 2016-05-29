package commandline;

import filehandler.Decryption;
import filehandler.Encryption;
import filehandler.FileHandler;
import filehandler.algorithm.CaesarAlgorithm;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandlerTest {
    private Decryption decryption = mock(Decryption.class);
    private Encryption encryption = mock(Encryption.class);
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void addOption() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        assertEquals(cliHandler, cliHandler.addOption("-d", decryption));
        assertEquals(cliHandler, cliHandler.addOption("-e", encryption));
        assertEquals(cliHandler, cliHandler.addOption(null, null));
        assertEquals(cliHandler, cliHandler.addOption("-e", null));
        assertEquals(cliHandler, cliHandler.addOption(null, decryption));
    }

    @Test
    public void handleArguments() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        FileHandler fileHandler = mock(FileHandler.class);
        File file = temporaryFolder.newFile();
        cliHandler.addOption("-e", encryption);
        cliHandler.handleArguments(new String[]{"-e",file.getPath()});
        verify(encryption,times(1)).act(file,new CaesarAlgorithm());
    }

    @Test
    public void showOptions() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        cliHandler.showOptions();
        cliHandler.addOption(null, null);
        cliHandler.showOptions();
        cliHandler.addOption(null, null);
        cliHandler.addOption("-d", decryption);
        cliHandler.showOptions();
    }

}