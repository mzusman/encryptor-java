package commandline;

import filehandler.algorithm.DoubleAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import lombok.Cleanup;
import org.junit.Before;
import org.junit.Test;
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

    @Before
    public void start() {
        CliHandler.Builder builder = new CliHandler.Builder();
        cliHandler = builder.addAlgorithm(XorAlgorithm::new)
                .addAlgorithm(DoubleAlgorithm::new)
                .addAlgorithm(CaesarAlgorithm::new).create();
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