package modules;

import commandline.AlgorithmsEnum;
import commandline.Selector;
import domain.algorithm.Algorithm;
import modules.XmlAlgorithm;

import javax.inject.Provider;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by mzeus on 28/07/16.
 */
public class AlgorithmSelector implements Selector<AlgorithmsEnum>, Provider<Algorithm> {


    @Override
    public AlgorithmsEnum selectFromList(List list) throws InstantiationException, IllegalAccessException, IOException {
        return (AlgorithmsEnum) getUserChoice(list);
    }

    private void printDescriptions(List list) {
        list.forEach(c -> System.out.printf("%s - %s\n", list.indexOf(c) + 1, c.toString()));
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

    private File getFileFromUser(Predicate<File> predicate) throws IOException {
        System.out.println("enter path:");
        String in = getStringFromUser();
        File file = new File(in);
        while (!predicate.test(file)) {
            in = getStringFromUser();
            file = new File(in);
            System.out.println("wrong input");
        }
        return file;
    }

    private Algorithm startUserSelect() {
        Algorithm algorithm = null;
        try {
            if (askIf("Would you like to use the default algorithm?")) {
                algorithm = XmlAlgorithm.getInstance().readAlgorithm(null);
            } else {
                if (askIf("would you like to import an algorithm xml?"))
                    algorithm = XmlAlgorithm.getInstance().readAlgorithm(getFileFromUser(File::isFile));
                else {
                    algorithm = selectAlgorithmClass();
                    if (askIf("Would you like to export the built algorithm?"))
                        XmlAlgorithm.getInstance().writeAlgorithm(algorithm, getFileFromUser(File::isDirectory));
                }
            }
            return algorithm;
        } catch (IOException | JAXBException e) {
            return startUserSelect();
        }
    }


    private boolean askIf(String message) throws IOException {
        System.out.println(message + "(y/n)");
        return yesOrNo();
    }

    private boolean yesOrNo() throws IOException {
        String in;
        while (!(in = getStringFromUser()).matches("^(n|y)$")) {
            System.out.println("wrong input");
        }
        return in.equals("y");
    }


    @Override
    public Algorithm get() {
        return startUserSelect();
    }

    private Algorithm selectAlgorithmClass() {
        System.out.println("Select an algorithm:");
        ArrayList<AlgorithmsEnum> classes = new ArrayList<>();
        Collections.addAll(classes, AlgorithmsEnum.values());
        printDescriptions(classes);
        AlgorithmsEnum algorithmsEnum;
        Algorithm algorithm = null;
        try {
            algorithmsEnum = selectFromList(classes);
            algorithm = algorithmsEnum.getAlgorithmClass().newInstance();
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            //ignored
        }
        for (int i = 0; i < algorithm.numberOfAlgorithms(); i++) {
            algorithm.pushAlgorithm(selectAlgorithmClass());
        }

        return algorithm;
    }

}
