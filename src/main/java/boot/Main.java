package boot;

import commandline.CliHandler;
import filehandler.algorithm.DoubleNormalAlgorithm;
import filehandler.algorithm.NormalAlgorithm;
import filehandler.algorithm.ReverseNormalAlgorithm;
import filehandler.algorithm.SplitNormalAlgorithm;
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
        CliHandler.Builder builder = new CliHandler.Builder();
        builder.addOption(new DecryptionOperation())
                .addOption(new EncryptionOperation())
                .addAlgorithm(new DoubleNormalAlgorithm())
                .addAlgorithm(new ReverseNormalAlgorithm())
                .addAlgorithm(new SplitNormalAlgorithm())
                .addAlgorithm(new NormalAlgorithm().addAlgorithm(new CaesarAlgorithm()))
                .addAlgorithm(new NormalAlgorithm().addAlgorithm(new XorAlgorithm()))
                .addAlgorithm(new NormalAlgorithm().addAlgorithm(new MultiplicationAlgorithm()))
                .create().handleArguments(args);

    }

}
