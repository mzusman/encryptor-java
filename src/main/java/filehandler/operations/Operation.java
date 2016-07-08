package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 04/06/16.
 */
public interface Operation<E extends Algorithm<Integer>> {
    void run(E algorithm);

    byte operate(Algorithm<Integer> algorithm, int raw, int index);

    Algorithm<Integer> fillKeys(Algorithm<Integer> algorithm) throws IOException, ClassNotFoundException, KeyException;

    void runSync(InputStream in, OutputStream out, Algorithm algorithm) throws IOException;
}
