package boot;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import commandline.CliHandler;
import filehandler.algorithm.*;
import filehandler.operations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.SyslogAppender;
import org.apache.logging.log4j.core.pattern.ArrayPatternConverter;
import utils.files.DecryptionFilesManager;
import utils.files.EncryptionFilesManager;
import utils.files.FilesManagerFactory;

import java.io.File;
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

        operator.run(algorithm);

    }

}
