package boot;

import commandline.CliHandler;
import filehandler.algorithm.*;
import filehandler.operations.*;

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

        if (file.isDirectory()) {
            builder.addOption(() -> new DirectoryAsyncOperator(new DecryptionOperator(file)))
                    .addOption(() -> new DirectoryAsyncOperator(new EncryptionOperator(file)))
                    .addOption(() -> new DirectorySyncOperator(new EncryptionOperator(file)))
                    .addOption(() -> new DirectorySyncOperator(new DecryptionOperator(file)));

        } else {
            builder.addOption(() -> new DecryptionOperator(file))
                    .addOption(() -> new EncryptionOperator(file));
        }

        CliHandler cliHandler = builder.create();
        cliHandler.startUserSelect();

        Operation operation = cliHandler.getSelectedOperation();
        Algorithm algorithm = cliHandler.getSelectedAlgorithm();

        ((Observable) operation).addObserver(cliHandler);
        operation.run(algorithm);

    }

}
