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

    DisplayMessage displayMessage = new DisplayMessage() {
        @Override
        public void display(String message) {
            System.out.println(message);
        }
    };

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
        if (!file.exists() || !file.isFile()) handleNotFoundFile(file.getPath());
        else if (!file.isFile()) System.out.println("error: not a file");
        else if (!file.canRead()) System.out.println("don't have permission to read");
        else if (!file.canWrite()) System.out.println("don't have permission to write");

        Operation operation = operationFactory.get(arg[0]);
        if (operation == null) {
            showOptions();
            return;
        }
        CipherAlgorithm algorithm = selectAlgorithm();
        FileHandler fileHandler = new FileHandler(operation, file, displayMessage);
        try {
            fileHandler.handleFile(algorithm);
        } catch (KeyException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            handleNotFoundFile(e.getMessage());
        }

    }

    Console console = System.console();

    private CipherAlgorithm selectAlgorithm() {
        System.out.println("Select an algorithm:");
        algorithmFactory.forEach((s, c) ->
                System.out.printf("%s - %s\n", s, c.getDescription()));
        String input = console.readLine("enter:");
        return algorithmFactory.get(input);// todo: have to make it more safety
    }

    private String handleNotFoundFile(String message) {
        if (message != null)
            System.out.println(message);
        else
            System.out.println("Error with files!");
        return console.readLine("Enter the path again:");
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


    public int getKey() {
        String key = console.readLine("Enter the key that used for encryption: ");
        if (key.matches("\\d+$") && Integer.parseInt(key) < 256
                && Integer.parseInt(key) > 0)
            return Integer.parseInt(key);
        else {
            return getKey();
        }
    }


}
