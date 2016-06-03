package commandline;


import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.operations.Operation;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import filehandler.operations.Operator;
import utils.DisplayMessage;
import utils.Selectable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler {
    private static CliHandler instance = new CliHandler();

    public static CliHandler getInstance() {
        return instance;
    }

    private ArrayList<Operation> operationFactory = new ArrayList<>();
    private ArrayList<CipherAlgorithm> algorithmFactory = new ArrayList<>();
    private ArrayList<Algorithm> wrapAlgorithmFactory = new ArrayList<>();
    private DisplayMessage displayMessage = System.out::println;

    private CliHandler() {
    }

    public CliHandler addOption(Operation operation) {
        if (operation == null)
            return this;
        operationFactory.add(operation);
        return this;
    }

    public CliHandler addAlgorithm(CipherAlgorithm algorithm) {
        if (algorithm == null)
            return this;
        algorithmFactory.add(algorithm);
        return this;
    }


    public CliHandler addWrapAlgorithm(Algorithm algorithm) {
        if (algorithm == null)
            return this;
        wrapAlgorithmFactory.add(algorithm);
        return this;
    }


    public void handleArguments(String[] args) {
        if (args.length != 1) {
            showOptions();
            return;
        }

        File file = new File(args[0]);
        if (!file.exists() || !file.isFile()) try {
            handleNotFoundFile(file.getPath());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        else if (!file.isFile()) System.err.println("error: not a file");
        else if (!file.canRead()) System.err.println("don't have permission to read");
        else if (!file.canWrite()) System.err.println("don't have permission to write");


        try {
            Operation operation = (Operation) selectSelectable(operationFactory, "Operation");
            List<CipherAlgorithm> list = new ArrayList<>();

            Operator operator = new Operator(operation);
            operation.act(displayMessage,file,list);
        } catch (KeyException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            try {
                handleNotFoundFile(e.getMessage());
            } catch (IOException e1) {
                System.err.println(e1.getMessage());
            }
        }
    }



    private Selectable selectSelectable(ArrayList<? extends Selectable> list, String type) throws IOException {
        System.out.printf("Select an %s:\n", type);
        list.forEach((s) ->
                System.out.printf("%s - %s\n", list.indexOf(s), (s).getDescription()));
        System.out.println("enter :");
        String input = getStringFromUser();
        return list.get(Integer.parseInt(input));// todo: have to make it more safety
    }


    private String handleNotFoundFile(String message) throws IOException {
        if (message != null)
            System.out.println(message);
        else
            System.out.println("Error with files!");
        System.out.println("Enter the path again:");
        return getStringFromUser();
    }

    private void showOptions() {
        System.out.println("usage: ... <file>\n");
        if (operationFactory.size() == 0) {
            System.out.println("no handlers are available");
            return;
        }
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
