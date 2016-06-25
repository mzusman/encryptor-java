package commandline;

import filehandler.algorithm.ExtendedAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import filehandler.operations.DecryptionOperation;
import filehandler.operations.EncryptionOperation;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.operations.Operator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandlerTest {
    private DecryptionOperation decryptionOperation = mock(DecryptionOperation.class);
    private EncryptionOperation encryptionOperation = mock(EncryptionOperation.class);
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
//        assertNotEquals(CliHandler.getInstance().findKey(), 0);

    }


    @Test
    public void addOption() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        assertEquals(cliHandler, cliHandler.addOption(decryptionOperation));
        assertEquals(cliHandler, cliHandler.addOption(encryptionOperation));
        assertEquals(cliHandler, cliHandler.addOption(null));
    }

    @Test
    public void handleArguments() throws Exception {
        CliHandler cliHandler = CliHandler.getInstance();
        Operator fileHandler = mock(Operator.class);
        File file = temporaryFolder.newFile();
        CaesarAlgorithm algorithm = new CaesarAlgorithm();
        cliHandler.addOption(encryptionOperation);
        cliHandler.addAlgorithm(algorithm);
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        cliHandler.handleArguments(new String[]{file.getPath()});
        verify(encryptionOperation, times(1)).init(System.out::println, any(File.class), any(ExtendedAlgorithm.class));
    }


}