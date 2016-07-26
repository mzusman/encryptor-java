package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 04/06/16.
 */
public interface Operation<E extends Algorithm<T>,T> {
    void run(E algorithm);

    T operate(Algorithm<T> algorithm, T raw, int index);

    Algorithm<T> fillKeys(Algorithm<T> algorithm) throws IOException, ClassNotFoundException, KeyException;

}
