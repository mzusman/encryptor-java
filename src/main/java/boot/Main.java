package boot;

import commandline.CliHandler;
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
        cliHandler.addOption("-d", new Decryption())
                .addOption("-e", new Encryption())
                .addAlgorithm("1", new CaesarAlgorithm())
                .addAlgorithm("2", new XorAlgorithm())
                .addAlgorithm("3", new MultiplicationAlgorithm())
                .handleArguments(args);

    }

}
