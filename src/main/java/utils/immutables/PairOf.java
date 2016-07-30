package utils.immutables;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.File;

/**
 * Created by mzues on 7/30/2016.
 */
@Value
public class PairOf<T, P> {
    T key;
    P val;
}
