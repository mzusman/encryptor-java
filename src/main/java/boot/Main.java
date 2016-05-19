package boot;

import commandline.CliHandler;
import filehandlers.FileDecryption;
import filehandlers.FileEncryption;

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
        cliHandler.addOption("-d", new FileDecryption())
                .addOption("-e", new FileEncryption()).handleArguments(args);

    }

}
