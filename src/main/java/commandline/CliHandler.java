package commandline;


import exceptions.KeyException;
import exceptions.UnsupportedKeyNumberException;
import filehandler.FileHandler;
import filehandler.operations.Operation;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.Cleanup;
import lombok.NonNull;
import utils.DisplayMessage;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler {
    private static CliHandler instance = new CliHandler();

    public static CliHandler getInstance() {
        return instance;
    }

    private HashMap<String, Operation> operationFactory = new HashMap<String, Operation>();
    private HashMap<String, CipherAlgorithm> algorithmFactory = new HashMap<>();
    private DisplayMessage displayMessage = System.out::println;

    private CliHandler() {
    }

    public CliHandler addOption(String arg, Operation operation) {
        if (operation == null || arg == null)
            return this;
        operationFactory.put(arg, operation);
        return this;
    }

    public CliHandler addAlgorithm(String arg, CipherAlgorithm algorithm) {
        if (algorithm == null || arg == null)
            return this;
        algorithmFactory.put(arg, algorithm);
        return this;
    }


    public void handleArguments(@NonNull String[] arg) {
        if (arg.length != 2) {
            showOptions();
            return;
        }

        File file = new File(arg[1]);
        if (!file.exists() || !file.isFile()) try {
            handleNotFoundFile(file.getPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        else if (!file.isFile()) System.out.println("error: not a file");
        else if (!file.canRead()) System.out.println("don't have permission to read");
        else if (!file.canWrite()) System.out.println("don't have permission to write");

        Operation operation = operationFactory.get(arg[0]);
        if (operation == null) {
            showOptions();
            return;
        }
        try {
            CipherAlgorithm algorithm = selectAlgorithm();
            FileHandler fileHandler = new FileHandler(operation, file, displayMessage);
            fileHandler.handleFile(algorithm);
        } catch (KeyException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            try {
                handleNotFoundFile(e.getMessage());
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
        }
    }


    private CipherAlgorithm selectAlgorithm() throws IOException {
        System.out.println("Select an algorithm:");
        algorithmFactory.forEach((s, c) ->
                System.out.printf("%s - %s\n", s, c.getDescription()));
        System.out.println("enter :");
        String input = getStringFromUser();
        return algorithmFactory.get(input);// todo: have to make it more safety
    }

    private String handleNotFoundFile(String message) throws IOException {
        if (message != null)
            System.out.println(message);
        else
            System.out.println("Error with files!");
        System.out.println("Enter the path again:");
        return getStringFromUser();
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


    public int getKey() throws IOException {
        System.out.println("Enter the key that used for encryption:");
        String key = getStringFromUser();
        if (key.matches("\\d+$") && Integer.parseInt(key) < 256
                && Integer.parseInt(key) > 0)
            return Integer.parseInt(key);
        else {
            return getKey();
        }
    }

    private String getStringFromUser() throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        try {
            String in = console.readLine();
            if (in == null)
                return "";
            return in;
        } catch (IOException e) {
            throw new IOException("cannot read from console");
        }
    }


}
