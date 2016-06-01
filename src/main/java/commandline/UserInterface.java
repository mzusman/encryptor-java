package commandline;

import filehandler.operations.Operation;

import java.util.HashMap;

/**
 * Created by mzeus on 01/06/16.
 */
public interface UserInterface<T, K, F> {
    UserInterface addOption(String arg, F f);

    UserInterface addAlgorithm(String arg, T t);

    UserInterface addAlgorithmWrapper(String arg, K k);

    void handleOptions(String[] arg);

    void handleAlgorithms(String[] arg);

    void handleAlgorithmWrappers(String[] arg);

    void showOptions();

    void selectAlgorithms();

    void selectAlgorithmWrappers();

    void handleErrors();


}
