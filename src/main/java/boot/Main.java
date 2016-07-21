package boot;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import commandline.CliHandler;
import filehandler.algorithm.*;
import filehandler.operations.*;
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
        CliHandler.Builder builder = new CliHandler.Builder();
        File file = new File(args[0]);

//        if (file.isDirectory()) {
//            builder.addOption(() -> new DirectoryAsyncOperator(new DecryptionOperator(file, new DecryptionFilesManager(file))))
//                    .addOption(() -> new DirectoryAsyncOperator(new EncryptionOperator(file, new EncryptionFilesManager(file))))
//                    .addOption(() -> new DirectorySyncOperator(new EncryptionOperator(file, new EncryptionFilesManager(file))))
//                    .addOption(() -> new DirectorySyncOperator(new DecryptionOperator(file, new DecryptionFilesManager(file))));
//        } else {
//            builder.addOption(() -> new DecryptionOperator(file, new DecryptionFilesManager(file)))
//                    .addOption(() -> new EncryptionOperator(file, new EncryptionFilesManager(file)));
//        }
        Injector injector = Guice.createInjector(new DirectoryModule(), new DecryptModule(file));
        DirectorySyncOperator operator = injector.getInstance(DirectorySyncOperator.class);

        CliHandler cliHandler = builder.create();
        cliHandler.startUserSelect();


//        Operation operation = cliHandler.getSelectedOperation();
        Algorithm algorithm = cliHandler.getSelectedAlgorithm();

        ((Observable) operator).addObserver(cliHandler);

        operator.run(algorithm);

    }

}
