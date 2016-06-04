package boot;

import commandline.CliHandler;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.DoubleAlgorithm;
import filehandler.algorithm.ReverseAlgorithm;
import filehandler.algorithm.SplitAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import filehandler.operations.DecryptionOperation;
import filehandler.operations.EncryptionOperation;

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
        CliHandler cliHandler = CliHandler.getInstance();
        cliHandler.addOption(new DecryptionOperation())
                .addOption(new EncryptionOperation())
                .addAlgorithm(new DoubleAlgorithm())
                .addAlgorithm(new ReverseAlgorithm())
                .addAlgorithm(new SplitAlgorithm())
                .addAlgorithm(new Algorithm().addAlgorithm(new CaesarAlgorithm()))
                .addAlgorithm(new Algorithm().addAlgorithm(new XorAlgorithm()))
                .addAlgorithm(new Algorithm().addAlgorithm(new MultiplicationAlgorithm()))
                .handleArguments(args);

    }

}
