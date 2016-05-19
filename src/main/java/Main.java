import commandline.CliHandler;
import filehandlers.Decryptor;
import filehandlers.Encryptor;

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
        CliHandler cliHandler = new CliHandler();
        cliHandler.addOption("-d", new Decryptor())
                .addOption("-e", new Encryptor()).handleArgument(args[0]);

    }

}
