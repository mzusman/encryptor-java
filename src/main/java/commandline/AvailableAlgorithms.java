package commandline;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by mzeus on 6/24/16.
 */
@AllArgsConstructor
public enum AvailableAlgorithms {
    XorAlgorithm("Xor"),
    MultiplicationAlgorithm("Multi"),
    CaesarAlgorithm("Caesar"),
    DoubleAlgorithm("Double"),
    ReverseNormalAlgorithm("Reverse"),
    SplitNormalAlgorithm("Split");

    private final
    @Getter
    String val;


}
