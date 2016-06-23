package commandline;


import exceptions.KeyException;
import filehandler.algorithm.NormalAlgorithm;
import filehandler.operations.AbstractOperation;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import filehandler.operations.FileHandler;
import filehandler.operations.Operator;
import lombok.NonNull;
import utils.DisplayMessage;
import utils.Selectable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler implements UserInterface {

    private DisplayMessage displayMessage = System.out::println;

    private ArrayList<AbstractOperation> abstractOperationFactory;
    private ArrayList<CipherAlgorithm> algorithmFactory;

    private void furtherSelect(NormalAlgorithm normalAlgorithm) throws IOException {
        for (int i = 0; i < normalAlgorithm.exceptedSize(); i++) {
            normalAlgorithm.addAlgorithm((CipherAlgorithm) selectSelectable(algorithmFactory, "NormalAlgorithm" + i));
        }
        for (CipherAlgorithm cipherAlgorithm :
                normalAlgorithm.getAlgorithms()) {
            if (((NormalAlgorithm) cipherAlgorithm).exceptedSize() > 1) {
                furtherSelect((NormalAlgorithm) cipherAlgorithm);
            }
        }
    }


    private CliHandler(Builder builder) {
        this.abstractOperationFactory = builder.abstractOperationFactory;
        this.algorithmFactory = builder.algorithmFactory;
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
            Operator operator = new Operator(abstractOperation);
            operator.init(displayMessage, file, normalAlgorithm);
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
            AbstractOperation abstractOperation = (AbstractOperation) selectSelectable(abstractOperationFactory, "AbstractOperation");
            NormalAlgorithm normalAlgorithm = (NormalAlgorithm) selectSelectable(algorithmFactory, "NormalAlgorithm");
            if (normalAlgorithm.exceptedSize() > 1 || normalAlgorithm.getAlgorithms().size() == 0)
                furtherSelect(normalAlgorithm);
    }


    @Override
    public Selectable selectSelectable(List<? extends Selectable> list, String type) throws IOException {
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

    public void showOptions() {
        System.out.println("usage: ... <file>\n");
        if (abstractOperationFactory.size() == 0) {
            System.out.println("no handlers are available");
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

    /**
     * Builder class for the {@link CliHandler} class
     */
    public static class Builder {
        private ArrayList<AbstractOperation> abstractOperationFactory = new ArrayList<>();
        private ArrayList<CipherAlgorithm> algorithmFactory = new ArrayList<>();

        public Builder addOption(AbstractOperation abstractOperation) {
            if (abstractOperation == null)
                return this;
            abstractOperationFactory.add(abstractOperation);
            return this;
        }

        public Builder addAlgorithm(CipherAlgorithm algorithm) {
            if (algorithm == null) {
                System.out.println("null");
                return this;
            }
            algorithmFactory.add(algorithm);
            return this;
        }

        public CliHandler create() {
            return new CliHandler(this);
        }


    }


}
