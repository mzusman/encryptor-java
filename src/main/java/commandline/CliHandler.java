package commandline;


import filehandler.algorithm.Algorithm;
import filehandler.operations.Operation;
import lombok.Getter;
import lombok.NonNull;
import utils.Timer;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler implements Observer, UserInterface<Algorithm, Operation> {


    private ArrayList<Operation> operations;
    private ArrayList<Algorithm> algorithms;
    @Getter
    private Operation selectedOperation;
    @Getter
    private Algorithm selectedAlgorithm;


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
        startUserSelect();
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

    public void startUserSelect() {
        try {
            selectedOperation = selectOperation();
//            selectedAlgorithm = selectAlgorithm();
//            selectedAlgorithm = selectAlgorithmClass();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public Algorithm selectAlgorithm() throws IOException {
        System.out.println("Select an algorithm:");
        printDescriptions(algorithms);
        Algorithm algorithm = (Algorithm) getUserChoice(algorithms);
        for (int i = 0; i < algorithm.numberOfAlgorithms(); i++) {
            algorithm.pushAlgorithm(selectAlgorithm());
        }
        return algorithm;
    }

    private Algorithm selectAlgorithmClass() throws IOException, IllegalAccessException, InstantiationException {
        System.out.println("Select an algorithm:");
        ArrayList<AlgorithmsEnum> classes = new ArrayList<>();
        Collections.addAll(classes, AlgorithmsEnum.values());
        printDescriptions(classes);
        AlgorithmsEnum anEnum = (AlgorithmsEnum) getUserChoice(classes);
        Algorithm algorithm = anEnum.getAlgorithmClass().newInstance();
        for (int i = 0; i < algorithm.numberOfAlgorithms(); i++) {
            algorithm.pushAlgorithm(selectAlgorithmClass());
        }
        return algorithm;
    }

    private Object getUserChoice(List list) throws IOException {
        System.out.printf("enter(%d-%d) :", 1, list.size());
        String input = getStringFromUser();
        while (!input.matches("\\d$")) {
            System.out.println("Wrong input");
            printDescriptions(list);
            System.out.printf("enter(%d-%d) :", 1, list.size());
            input = getStringFromUser();
        }
        return list.get(Integer.parseInt(input) - 1);// todo: have to make it more safety
    }


    private void printDescriptions(List list) {
        list.forEach(c -> System.out.printf("%s - %s\n", list.indexOf(c) + 1, c.toString()));
    }

    private Operation selectOperation() throws IOException {
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
        if (o.equals(CommandsEnum.START)) {
            System.out.println("Operation have started");
        } else if (o.equals(CommandsEnum.END)) {
            System.out.println("Operation have ended, took :" + Timer.getInstance().getLastTime());
        } else if (o instanceof Exception) {
            System.out.println(((Exception) o).getMessage());
            ((Exception) o).printStackTrace();
        } else if (o instanceof Observable) {
            ((Observable) o).addObserver(this);
        } else if (o instanceof String) {
            System.out.println(o);
        }


    }


    public static class Builder {
        private ArrayList<Operation> operations = new ArrayList<>();
        private ArrayList<Algorithm> algorithms = new ArrayList<>();
        private ArrayList<Class> classes = new ArrayList<>();

        public Builder addOption(Supplier<Operation> abstractOperation) {
            if (abstractOperation == null)
                return this;
            operations.add(abstractOperation.get());
            return this;
        }

        public Builder addAlgorithm(Supplier<Algorithm> algorithm) {
            if (algorithm == null)
                return this;
            algorithms.add(algorithm.get());
            return this;
        }

        public Builder addAlgorithmClass(Class<? extends Algorithm> aClass) {
            if (aClass == null)
                return this;
            classes.add(aClass);
            return this;
        }

        public CliHandler create() {
            return new CliHandler(this);
        }
    }


}
