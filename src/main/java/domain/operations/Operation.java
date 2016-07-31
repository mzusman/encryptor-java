package domain.operations;

import exceptions.KeyException;
import domain.algorithm.Algorithm;

import java.io.IOException;

/**
 * Operation - runs the algorithm on each T of the i\o streams
 *
 * @param <E> the type parameter
 * @param <T> the type parameter
 */
public interface Operation<E extends Algorithm<T>, T> {
    /**
     * Run.
     *
     * @param algorithm the algorithm
     */
    void run(E algorithm);

    /**
     * The actual operation (part of the run method)
     * the algorithm is ran on parameter T
     * and returns the renault
     *
     * @param algorithm the algorithm
     * @param raw       the raw
     * @param index     the index
     * @return the t
     */
    T operate(Algorithm<T> algorithm, T raw, int index);

    /**
     * On given algorithm return the same algorithm filled with keys
     *
     * @param algorithm the algorithm
     * @return the algorithm
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     * @throws KeyException           the key exception
     */
    Algorithm<T> fillKeys(Algorithm<T> algorithm) throws IOException, ClassNotFoundException, KeyException;

}
