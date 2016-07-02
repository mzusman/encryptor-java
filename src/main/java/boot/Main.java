package boot;

import commandline.CliHandler;
import filehandler.algorithm.*;
import filehandler.algorithm.ReverseAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import filehandler.operations.DecryptionOperation;
import filehandler.operations.EncryptionOperation;
import filehandler.operations.Operation;

import java.util.ArrayList;

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
        builder.addOption(DecryptionOperation::new)
                .addOption(EncryptionOperation::new)
                .addAlgorithm(() -> new NormalAlgorithm().addAlgorithm(new CaesarAlgorithm()))
                .addAlgorithm(ReverseAlgorithm::new)
                .addAlgorithm(() -> new NormalAlgorithm().addAlgorithm(new MultiplicationAlgorithm()))
                .addAlgorithm(() -> new NormalAlgorithm().addAlgorithm(new XorAlgorithm()))
                .addAlgorithm(SplitAlgorithms::new)
                .addAlgorithm(DoubleAlgorithms::new);
        CliHandler cliHandler = builder.create();
        cliHandler.handleArguments(args);

        Operation operation = cliHandler.getSelectedOperation();
        ListOfAlgorithms algorithms = cliHandler.getSelectedListOfAlgorithms();
        operation.run(algorithms);


    }

}
