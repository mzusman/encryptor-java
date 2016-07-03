package commandline;

import filehandler.algorithm.Algorithm;
import filehandler.operations.Operation;

import java.io.IOException;
import java.util.List;

/**
 * Created by mzeus on 01/06/16.
 */
public interface UserInterface<C extends Algorithm, O extends Operation> {


    void showOptions();


}
