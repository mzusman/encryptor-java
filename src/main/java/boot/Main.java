package boot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import commandline.CommandlineHandler;
import commandline.OperationProcessor;
import domain.algorithm.*;
import domain.operations.Operation;
import modules.CommandlineModule;
import utils.xml.LogManager;

import java.util.Observable;

public class Main {

    public static void main(String args[]) {
        Injector injector = Guice.createInjector(new CommandlineModule());
        CommandlineHandler handler = injector.getInstance(CommandlineHandler.class);
        if (!handler.start(args))
            return;
        Algorithm algorithm = injector.getInstance(Algorithm.class);
        injector = Guice.createInjector(((OperationProcessor) handler.getProcessor()).getModule());
        Operation operator = injector.getInstance(handler.getSelectOperation());
        LogManager logManager = injector.getInstance(LogManager.class);
        ((Observable) operator).addObserver(handler);
        ((Observable) operator).addObserver(logManager);

        operator.run(algorithm);

    }

}
