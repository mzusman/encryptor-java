package commandline;

import filehandler.algorithm.DoubleAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import lombok.Cleanup;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class CliHandlerTest {


    CliHandler cliHandler;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void start() {
        cliHandler = new CliHandler();

    }

    @Test
    public void scanForOperationTest() throws IOException {
        folder.create();
        File file = folder.newFile();
        assertTrue(cliHandler.scanForOperation(new String[]{"enc", "a", file.getPath()}));
        assertTrue(cliHandler.scanForOperation(new String[]{"dec", "a", file.getPath()}));
        assertFalse(cliHandler.scanForOperation(new String[]{"enc", "b", "asdadws"}));
        assertFalse(cliHandler.scanForOperation(new String[]{"enc", "c", "asdadws"}));
        assertFalse(cliHandler.scanForOperation(new String[]{"enc", "d", "asdadws"}));
    }

    @Test
    public void startTest() throws IOException {
        folder.create();
        assertTrue(folder.newFolder().canRead());
    }

    @Test
    public void showOptions() throws Exception {
        cliHandler.showOptions();
        //todo
    }

    @Test
    public void startUserSelect() throws Exception {
    }


    @Test
    public void getKey() throws Exception {

    }


    @Test
    public void selectAlgorithm() throws Exception {

//        mockUserInput("1\n");
//        assertNotNull(cliHandler.selectAlgorithm());
//        mockUserInput("2\n");
//        assertNotNull(cliHandler.selectAlgorithm());
    }

    @Test
    public void getUserChoice() throws Exception {
//        List list = Mockito.mock(List.class);
//        mockUserInput("1\n");
//        cliHandler.getUserChoice(list);
//        verify(list, atLeastOnce()).size();
//        verify(list, times(1)).remove(0);

    }

    public void mockUserInput(String s) throws IOException {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        System.setIn(pis);
        OutputStreamWriter writer = new OutputStreamWriter(pos);
        writer.write(s);
        writer.flush();
    }

    @Test
    public void selectOperation() throws Exception {

    }

}