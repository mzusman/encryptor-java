package commandline;

import modules.DecryptModule;
import modules.EncryptModule;
import com.google.inject.Module;
import domain.operations.*;
import lombok.Getter;

import java.io.File;

/**
 * Created by mzeus on 28/07/16.
 */
public class OperationProcessor implements CommandlineProcessor<Operation> {
    private final String pattern;
    @Getter
    private Module module;


    /**
     * Instantiates a new Operation processor.
     *
     * @param pattern the pattern
     */
    public OperationProcessor(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Class<? extends Operation> processArgs(String[] args) {
        File file = new File(args[2]);

        if (args[0].equals("enc"))
            this.module = new EncryptModule(file);
        else if (args[0].equals("dec"))
            this.module = new DecryptModule(file);

        if (file.canRead()) {
            if (file.isDirectory()) {
                if (args[1].equals("a"))
                    return DirectoryAsyncOperator.class;
                else if (args[1].equals("s"))
                    return DirectorySyncOperator.class;
            } else {
                if (args[0].equals("enc"))
                    return EncryptionOperator.class;
                else if (args[1].equals("dec"))
                    return DecryptionOperator.class;
            }
        }
        return null;
    }


    @Override
    public boolean scanForPattern(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : args) {
            stringBuilder.append(s).append(" ");
        }
        String arg = stringBuilder.toString();
        return arg.matches(pattern);
    }

    @Override
    public void showOptions() {
        System.out.println("usage: encryptor <enc|dec> <s|a> <file|dir>");
        System.out.println("enc|dec - encrypt or decrypt");
        System.out.println("s|a - sync or async");
        System.out.println("file|dir - path to file or directory");
    }
}
