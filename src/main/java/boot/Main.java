package boot;

import commandline.CliHandler;
import filehandler.algorithm.DoubleAlgorithm;
import filehandler.algorithm.NormalAlgorithm;
import filehandler.algorithm.ReverseAlgorithm;
import filehandler.algorithm.SplitAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import filehandler.operations.Decryption;
import filehandler.operations.Encryption;

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
        cliHandler.addOption(new Decryption())
                .addOption(new Encryption())
                .addWrapAlgorithm(new DoubleAlgorithm())
                .addWrapAlgorithm(new ReverseAlgorithm())
                .addWrapAlgorithm(new SplitAlgorithm())
                .addWrapAlgorithm(new NormalAlgorithm())
                .addAlgorithm(new CaesarAlgorithm())
                .addAlgorithm(new XorAlgorithm())
                .addAlgorithm(new MultiplicationAlgorithm())
                .handleArguments(args);

    }

}
