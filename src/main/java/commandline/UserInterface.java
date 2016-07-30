package commandline;

import domain.algorithm.Algorithm;
import domain.operations.Operation;

/**
 * Created by mzeus on 01/06/16.
 *
 * @param <C> the type parameter
 * @param <O> the type parameter
 */
public interface UserInterface<C extends Algorithm, O extends Operation> {


    /**
     * Start boolean.
     *
     * @param args the args
     * @return the boolean
     */
    boolean start(String args[]);

}
