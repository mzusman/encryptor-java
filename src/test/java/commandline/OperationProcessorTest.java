package commandline;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 29/07/16.
 */
public class OperationProcessorTest {
    OperationProcessor processor = new OperationProcessor();

    @Test
    public void processArgs() throws Exception {
    }

    @Test
    public void scanForPattern() throws Exception {
        assertTrue(processor.scanForPattern(new String[]{"enc", "a", "asdasd"}));
        assertTrue(processor.scanForPattern(new String[]{"enc", "s", "asdasd"}));

    }

    @Test
    public void showOptions() throws Exception {

    }

}