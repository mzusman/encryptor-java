package domain.operations;

import exceptions.KeyException;
import domain.algorithm.Algorithm;
import javafx.beans.Observable;

import java.io.IOException;

/**
 * Created by mzeus on 04/06/16.
 */
public interface Operation<E extends Algorithm<T>,T> {
    void run(E algorithm);

    T operate(Algorithm<T> algorithm, T raw, int index);

    Algorithm<T> fillKeys(Algorithm<T> algorithm) throws IOException, ClassNotFoundException, KeyException;

}
