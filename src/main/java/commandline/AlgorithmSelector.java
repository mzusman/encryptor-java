package commandline;

import filehandler.algorithm.Algorithm;
import lombok.Getter;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mzeus on 28/07/16.
 */
public class AlgorithmSelector implements CommandlineSelector<Algorithm> {

    @Getter
    private Algorithm selectedAlgorithm;

    @Override
    public Algorithm selectFromList(List list) throws InstantiationException, IllegalAccessException, IOException {
        return selectAlgorithmClass();
    }

    private Algorithm selectAlgorithmClass() throws IllegalAccessException, InstantiationException, IOException {
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


    private boolean yesOrNo() throws IOException {
        String in;
        while (!(in = getStringFromUser()).matches("^(n|y)$")) {
            System.out.println("wrong input");
        }
        return in.equals("y");
    }

    private boolean askIf(String message) throws IOException {
        System.out.println(message + "(y/n)");
        return yesOrNo();
    }


    public void startUserSelect() {
        try {
            if (selector.askIf("Would you like to use the default algorithm?")) {
                selectedAlgorithm = XmlFilesManager.getInstance().readAlgorithmFromXml();
            } else {
                if (askIf("would you like to import an algorithm xml?"))
                    selectedAlgorithm = XmlFilesManager.readAlgorithmFromXml(getFileFromUser(File::isFile));
                else {
                    selectedAlgorithm = selectAlgorithmClass();
                    if (askIf("Would you like to export the built algorithm?"))
                        XmlFilesManager.writeAlgorithmToXml(selectedAlgorithm, getFileFromUser(File::isDirectory));
                }
            }
        } catch (IOException | InstantiationException | IllegalAccessException | JAXBException e) {
            e.printStackTrace();
        }
    }


}
