package commandline;

import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.CommandlineModule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 29/07/16.
 */
public class OperationProcessorTest {
    private OperationProcessor processor;

    @Before
    public void warmUp() {
        Injector injector = Guice.createInjector(new CommandlineModule());
        processor = injector.getInstance(OperationProcessor.class);
    }

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