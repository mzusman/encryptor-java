package commandline;

import domain.algorithm.Algorithm;
import domain.operations.Operation;

/**
 * Created by mzeus on 01/06/16.
 */
public interface UserInterface<C extends Algorithm, O extends Operation> {


    boolean start(String args[]);

}
