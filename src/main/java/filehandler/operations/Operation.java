package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.ListOfAlgorithms;

import java.io.IOException;

/**
 * Created by mzeus on 04/06/16.
 */
public interface Operation<E extends ListOfAlgorithms> {
    void run(E algorithm) throws IOException, KeyException;

}
