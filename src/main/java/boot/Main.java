package boot;

import commandline.CliHandler;
import filehandler.algorithm.*;
import filehandler.algorithm.ReverseAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import filehandler.operations.DecryptionOperator;
import filehandler.operations.EncryptionOperator;
import filehandler.operations.Operation;
import lombok.Cleanup;

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
        builder.addOption(()-> new DecryptionOperator(file))
                .addOption(() -> new EncryptionOperator(file))
                .addAlgorithm(CaesarAlgorithm::new)
                .addAlgorithm(ReverseAlgorithm::new)
                .addAlgorithm(XorAlgorithm::new)
                .addAlgorithm(MultiplicationAlgorithm::new)
                .addAlgorithm(DoubleAlgorithm::new)
                .addAlgorithm(SplitAlgorithms::new);
        CliHandler cliHandler = builder.create();
        cliHandler.startUserSelect();

        Operation operation = cliHandler.getSelectedOperation();
        Algorithm algorithms = cliHandler.getSelectedAlgorithm();
        ((Observable)operation).addObserver(cliHandler);
        operation.run(algorithms);

    }

}
