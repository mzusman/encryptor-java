package commandline;


import com.sun.corba.se.spi.orb.Operation;
import filehandler.algorithm.ExtendedAlgorithm;
import filehandler.operations.AbstractOperation;
import filehandler.algorithm.CipherAlgorithm;
import lombok.NonNull;
import utils.AvailableAlgorithms;
import utils.DisplayMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Supplier;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler extends Observable implements Observer, UserInterface<CipherAlgorithm, Operation> {


    private ArrayList<Operation> operations;
    private ArrayList<CipherAlgorithm> algorithms;

//    private void furtherSelect(ExtendedAlgorithm normalAlgorithm) throws IOException {
//        for (int i = 0; i < normalAlgorithm; i++) {
//            normalAlgorithm.addAlgorithm((CipherAlgorithm) selectSelectable(algorithmFactory, "ExtendedAlgorithm" + i));
//        }
//        for (CipherAlgorithm cipherAlgorithm :
//                normalAlgorithm.getAlgorithms()) {
//            if (((ExtendedAlgorithm) cipherAlgorithm).exceptedSize() > 1) {
//                furtherSelect((ExtendedAlgorithm) cipherAlgorithm);
//            }
//        }
//    }


    private CliHandler(Builder builder) {
        this.operations = builder.operations;
        this.algorithms = builder.algorithms;
    }

    /**
     * This method handles the arguments came from the cli
     *
     * @param args - cli args
     */
    public void handleArguments(String[] args) {
        if (args.length != 1) {
            showOptions();
            return;
        }

        File file = new File(args[0]);
        checkForFileError(file);


        try {
            startUserSelect();
        } catch (IOException e) {
            try {
                handleNotFoundFile(e.getMessage());
            } catch (IOException e1) {
                System.err.println(e1.getMessage());
            }
        }
    }

    private void checkForFileError(@NonNull File file) {
        if (!file.exists() || !file.isFile()) try {
            handleNotFoundFile(file.getPath());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        else if (!file.isFile()) System.err.println("error: not a file");
        else if (!file.canRead()) System.err.println("don't have permission to read");
        else if (!file.canWrite()) System.err.println("don't have permission to write");
    }

    private void startUserSelect() throws IOException {
        Operation operation = (Operation) selectOperation(operations);
        CipherAlgorithm cipherAlgorithm = (CipherAlgorithm) selectAlgorithm(algorithms);
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
        System.out.println("usage: ... <file>\n");
    }

    @Override
    public CipherAlgorithm selectAlgorithm(List<CipherAlgorithm> algorithms) throws IOException {
        System.out.println("Select an algorithm:");
        printDescriptions(algorithms);
        return (CipherAlgorithm) getUserChoice(algorithms);
    }

    private Object getUserChoice(List list) throws IOException {
        System.out.printf("enter(%d-%d) :", 1, list.size() + 1);
        String input = getStringFromUser();
        while (!input.matches("\\d$")) {
            System.out.println("Wrong input");
            printDescriptions(list);
            System.out.printf("enter(%d-%d) :", 1, list.size() + 1);
            input = getStringFromUser();
        }
        return list.get(Integer.parseInt(input) - 1);// todo: have to make it more safety

    }

    private void printDescriptions(List list) {
        list.forEach(c -> System.out.printf("%s - %s\n", list.indexOf(c) + 1, c.toString()));
    }

    @Override
    public Operation selectOperation(List<Operation> operations) throws IOException {
        System.out.println("Select an operation:");
        printDescriptions(operations);
        return (Operation) getUserChoice(operations);
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

    @Override
    public void update(Observable observable, Object o) {

    }

    /**
     * Builder class for the {@link CliHandler} class
     */
    public static class Builder {
        private ArrayList<Operation> operations = new ArrayList<>();
        private ArrayList<CipherAlgorithm> algorithms = new ArrayList<>();

        public Builder addOption(Supplier<Operation> abstractOperation) {
            if (abstractOperation == null)
                return this;
            operations.add(abstractOperation.get());
            return this;
        }

        public Builder addAlgorithm(Supplier<CipherAlgorithm> algorithm) {
            if (algorithm == null)
                return this;
            algorithms.add(algorithm.get());
            return this;
        }

        public CliHandler create() {
            return new CliHandler(this);
        }


    }


}
