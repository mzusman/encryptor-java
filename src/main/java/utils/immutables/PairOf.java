package utils.immutables;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.File;

/**
 * An immutable var , made inorder to save in file to out file
 * or in stream to out stream.
 *
 * @param <T> the type parameter
 * @param <P> the type parameter
 */
@Value
public class PairOf<T, P> {
    /**
     * The Key.
     */
    T key;
    /**
     * The Val.
     */
    P val;
}
