package boot;

import commandline.CliHandler;
import filehandler.algorithm.*;
import filehandler.algorithm.ExtendedAlgorithm;
import filehandler.algorithm.ReverseAlgorithm;
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
        builder.addOption(DecryptionOperation::new)
                .addOption(EncryptionOperation::new)
                .addAlgorithm(ExtendedAlgorithm.DoubleAlgorithm::new)
                .addAlgorithm(ReverseAlgorithm::new)
                .addAlgorithm(SplitAlgorithm::new)
                .addAlgorithm(() -> new ExtendedAlgorithm().addAlgorithm(new CaesarAlgorithm()))
                .addAlgorithm(() -> new ExtendedAlgorithm().addAlgorithm(new XorAlgorithm()))
                .addAlgorithm(() -> new ExtendedAlgorithm().addAlgorithm(new MultiplicationAlgorithm()))
                .create().handleArguments(args);



    }

}
