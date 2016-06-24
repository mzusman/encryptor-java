package boot;

import commandline.CliHandler;
import filehandler.algorithm.*;
import filehandler.algorithm.ManipulatedAlgorithm;
import filehandler.algorithm.ReverseManipulatedAlgorithm;
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
                .addAlgorithm(DoubleAlgorithm::new)
                .addAlgorithm(ReverseManipulatedAlgorithm::new)
                .addAlgorithm(SplitManipulatedAlgorithm::new)
                .addAlgorithm(() -> new ManipulatedAlgorithm().addAlgorithm(new CaesarAlgorithm()))
                .addAlgorithm(() -> new ManipulatedAlgorithm().addAlgorithm(new XorAlgorithm()))
                .addAlgorithm(() -> new ManipulatedAlgorithm().addAlgorithm(new MultiplicationAlgorithm()))
                .create().handleArguments(args);



    }

}
