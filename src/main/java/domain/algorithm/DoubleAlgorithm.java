package domain.algorithm;


import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Double algorithm - using two different algorithms.
 *
 * @param <T> the type parameter
 */
@XmlRootElement
@NoArgsConstructor
public class DoubleAlgorithm<T> extends MultiAlgorithm<T> {

    @Override
    public int numberOfAlgorithms() {
        return 2;
    }

}
