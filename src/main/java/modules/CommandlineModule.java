package modules;

import com.google.inject.AbstractModule;
import commandline.CommandlineProcessor;
import commandline.OperationProcessor;

/**
 * Created by mzeus on 29/07/16.
 */
public class CommandlineModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new AlgorithmModule());
        bind(CommandlineProcessor.class).to(OperationProcessor.class);
    }
}
