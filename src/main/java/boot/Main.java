package boot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import commandline.CliHandler;
import filehandler.algorithm.*;
import filehandler.operations.*;
import sun.rmi.log.ReliableLog;
import utils.LogFileManager;
import utils.xml.XmlFilesManager;

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
        CliHandler cliHandler = new CliHandler();
        if (!cliHandler.start(args))
            return;
        cliHandler.startUserSelect();
        Injector injector = Guice.createInjector(cliHandler.getModules());
        Operation operator = injector.getInstance(cliHandler.getSelectOperation());
        Algorithm algorithm = cliHandler.getSelectedAlgorithm();
        ((Observable) operator).addObserver(cliHandler);
        ((Observable) operator).addObserver(new LogFileManager());
        ((Observable) operator).addObserver(new XmlFilesManager());
        ((Observable) operator).addObserver(new XmlFilesManager());


        operator.run(algorithm);

    }

}
