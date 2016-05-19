package commandline;

import filehandlers.Decryptor;
import filehandlers.Encryptor;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandlerTest {
    private Decryptor decryptor = Mockito.mock(Decryptor.class);
    private Encryptor encryptor = Mockito.mock(Encryptor.class);

    @Test
    public void addOption() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        assertEquals(cliHandler, cliHandler.addOption("-d", decryptor));
        assertEquals(cliHandler, cliHandler.addOption("-e", encryptor));
        assertEquals(cliHandler, cliHandler.addOption(null, null));
        assertEquals(cliHandler, cliHandler.addOption("-e", null));
        assertEquals(cliHandler, cliHandler.addOption(null, decryptor));

    }

    @Test
    public void handleArguments() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        cliHandler.handleArguments(new String[0]);
        cliHandler.handleArguments(new String[]{"-e"});
    }

    @Test
    public void showOptions() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        cliHandler.showOptions();
        cliHandler.addOption(null,null);
        cliHandler.showOptions();
        cliHandler.addOption(null,null);
        cliHandler.addOption("-d",decryptor);
        cliHandler.showOptions();


    }

}