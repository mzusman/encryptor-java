package commandline;

import filehandler.algorithm.SingleAlgorithm;
import filehandler.algorithm.ListOfAlgorithms;
import filehandler.operations.Operation;

import java.io.IOException;
import java.util.List;

/**
 * Created by mzeus on 01/06/16.
 */
public interface UserInterface<C extends ListOfAlgorithms, O extends Operation> {


    void handleArguments(String[] arg);

    void showOptions();

    C selectAlgorithm(List<C> algorithms) throws IOException;

    O selectOperation(List<O> operations) throws IOException;


}
