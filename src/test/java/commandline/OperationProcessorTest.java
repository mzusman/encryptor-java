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
        assertTrue(processor.scanForPattern(new String[]{"dec", "a", "asdasd"}));
        assertTrue(processor.scanForPattern(new String[]{"dec", "a", "asdasd"}));
        assertFalse(processor.scanForPattern(new String[]{"enc", "s"}));
        assertFalse(processor.scanForPattern(new String[]{"asd", "s", "asdasd"}));
        assertFalse(processor.scanForPattern(new String[]{"enc", "123", "asdasd"}));
        assertFalse(processor.scanForPattern(new String[]{"enc", "as", "asdasd123"}));

    }

    @Test
    public void showOptions() throws Exception {

    }

}