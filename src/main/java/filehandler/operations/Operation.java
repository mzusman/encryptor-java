package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.ListOfAlgorithms;

import java.io.IOException;

/**
 * Created by mzeus on 04/06/16.
 */
public interface Operation<E extends ListOfAlgorithms> {
    void run(StreamManager streamManager, E algorithm) throws IOException, KeyException;

    void findKey(E algorithm) throws IOException;

    byte operate(E algorithm, int raw, int index);

}
