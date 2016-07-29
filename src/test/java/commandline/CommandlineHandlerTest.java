package commandline;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class CommandlineHandlerTest {


    CommandlineHandler commandlineHandler;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void start() {
//        commandlineHandler = new CommandlineHandler();

    }

    @Test
    public void scanForOperationTest() throws IOException {
        folder.create();
        File file = folder.newFile();
//        assertTrue(commandlineHandler.scanForOperation(new String[]{"enc", "a", file.getPath()}));
//        assertTrue(commandlineHandler.scanForOperation(new String[]{"dec", "a", file.getPath()}));
//        assertFalse(commandlineHandler.scanForOperation(new String[]{"enc", "b", "asdadws"}));
//        assertFalse(commandlineHandler.scanForOperation(new String[]{"enc", "b", "asdadws"}));
//        assertFalse(commandlineHandler.scanForOperation(new String[]{"enc", "ab", "asdadws"}));
//        assertFalse(commandlineHandler.scanForOperation(new String[]{"enc", "b", "asdadws"}));
//        assertFalse(commandlineHandler.scanForOperation(new String[]{"enc", "c", "asdadws"}));
//        assertFalse(commandlineHandler.scanForOperation(new String[]{"enc", "d", "asdadws"}));
    }

    @Test
    public void startTest() throws IOException {
        folder.create();
        assertTrue(folder.newFolder().canRead());
    }

    @Test
    public void showOptions() throws Exception {
//        commandlineHandler.showOptions();
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
//        assertNotNull(commandlineHandler.selectAlgorithm());
//        mockUserInput("2\n");
//        assertNotNull(commandlineHandler.selectAlgorithm());
    }

    @Test
    public void getUserChoice() throws Exception {
//        List list = Mockito.mock(List.class);
//        mockUserInput("1\n");
//        commandlineHandler.getUserChoice(list);
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