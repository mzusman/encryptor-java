package commandline;

import boot.DecryptModule;
import boot.EncryptModule;
import com.google.inject.Module;
import filehandler.operations.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by mzeus on 28/07/16.
 */
public class OperationProcessor implements CommandlineProcessor<Operation> {
    private ArrayList<Module> modules = new ArrayList<>();

    @Override
    public Class<? extends Operation> processArgs(String[] args) {
        File file = new File(args[2]);
        if (file.canRead()) {
            if (args[0].equals("enc"))
                modules.add(new EncryptModule(file));
            else modules.add(new DecryptModule(file));
            if (file.isDirectory()) {
                if (args[1].equals("a"))
                    return DirectoryAsyncOperator.class;
                else return DirectorySyncOperator.class;
            } else {
                if (args[0].equals("enc"))
                    return EncryptionOperator.class;
                else return DecryptionOperator.class;
            }
        } else return null;
    }

}
