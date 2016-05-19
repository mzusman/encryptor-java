package commandline;

import filehandlers.FileDecryption;
import filehandlers.FileEncryption;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandlerTest {
    private FileDecryption fileDecryption = mock(FileDecryption.class);
    private FileEncryption fileEncryption = mock(FileEncryption.class);

    @Test
    public void addOption() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        assertEquals(cliHandler, cliHandler.addOption("-d", fileDecryption));
        assertEquals(cliHandler, cliHandler.addOption("-e", fileEncryption));
        assertEquals(cliHandler, cliHandler.addOption(null, null));
        assertEquals(cliHandler, cliHandler.addOption("-e", null));
        assertEquals(cliHandler, cliHandler.addOption(null, fileDecryption));
    }

    @Test
    public void handleArguments() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        cliHandler.addOption("-e", fileEncryption);
        cliHandler.handleArguments(new String[0]);
        cliHandler.handleArguments(new String[]{"-e","~/Documents/hello.txt"});

    }

    @Test
    public void showOptions() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        cliHandler.showOptions();
        cliHandler.addOption(null, null);
        cliHandler.showOptions();
        cliHandler.addOption(null, null);
        cliHandler.addOption("-d", fileDecryption);
        cliHandler.showOptions();
    }

}