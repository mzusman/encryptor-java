package commandline;


import filehandler.FileHandler;
import filehandler.Operation;
import lombok.NonNull;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler {
    private static CliHandler instance = new CliHandler();

    public static CliHandler getInstance() {
        return instance;
    }

    private HashMap<String, Operation> operationFactory = new HashMap<String, Operation>();

    private CliHandler() {
    }

    public CliHandler addOption(String arg, Operation operation) {
        if (operation == null || arg == null)
            return this;
        operationFactory.put(arg, operation);
        return this;
    }

    public void handleArguments(@NonNull String[] arg) {
        if (arg.length != 2) {
            showOptions();
            return;
        }

        File file = new File(arg[1]);
        if (!file.exists() || !file.isFile()) handleNotFoundFile(file.getPath());
        else if (!file.isFile()) System.out.println("error: not a file");
        else if (!file.canRead()) System.out.println("don't have permission to read");
        else if (!file.canWrite()) System.out.println("don't have permission to write");

        Operation operation = operationFactory.get(arg[0]);
        if (operation == null) {
            showOptions();
            return;
        }
        FileHandler fileHandler = new FileHandler(operation, file);
        fileHandler.showFile();

    }

    public String handleNotFoundFile(@NonNull String path) {
        System.out.printf("file at %s was not found or does not exist\n", path);
        System.out.println("Enter the path again:");
        return System.console().readLine();
    }

    public void showOptions() {
        System.out.println("usage: ... <option> <file>\nOptions:");
        if (operationFactory.size() == 0) {
            System.out.println("no handlers are available");
            return;
        }
        operationFactory.forEach((s, f)
                -> System.out.printf("%s - %s\n", s, f.getDescription()));
    }


}
