package boot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import commandline.CommandlineHandler;
import commandline.OperationProcessor;
import domain.algorithm.*;
import domain.operations.Operation;
import modules.CommandlineModule;
import utils.xml.Manager;

import java.util.Observable;

/**
 * Created by Mor on 5/18/2016.
 */
public class Main {

    /**
     * args - if it's -e then we will choose encrypt, for -d we will decrypt
     * else we will show options.
     * we ignore everything else.
     *
     * @param args
     */

    public static void main(String args[]) {
        Injector injector = Guice.createInjector(new CommandlineModule());
        CommandlineHandler handler = injector.getInstance(CommandlineHandler.class);
        if (!handler.start(args))
            return;
        Algorithm algorithm = injector.getInstance(Algorithm.class);
        injector = Guice.createInjector(((OperationProcessor) handler.getProcessor()).getModule());
        Operation operator = injector.getInstance(handler.getSelectOperation());
        Manager manager = injector.getInstance(Manager.class);
        ((Observable) operator).addObserver(handler);
        ((Observable) operator).addObserver(manager);

        operator.run(algorithm);

    }

}
