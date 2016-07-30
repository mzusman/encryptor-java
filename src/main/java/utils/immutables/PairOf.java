package utils.immutables;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.File;

/**
 * Created by mzues on 7/30/2016.
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
