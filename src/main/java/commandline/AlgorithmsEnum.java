package commandline;

import domain.algorithm.Algorithm;
import domain.algorithm.DoubleAlgorithm;
import domain.algorithm.ReverseAlgorithm;
import domain.algorithm.SplitAlgorithm;
import domain.algorithm.CaesarAlgorithm;
import domain.algorithm.MultiplicationAlgorithm;
import domain.algorithm.XorAlgorithm;
import lombok.Getter;

/**
 * Enum used for storing an instance of the classes
 * of the algorithm that the program will support
 */
public enum AlgorithmsEnum {

    /**
     * The Caesar.
     */
    Caesar(CaesarAlgorithm.class, "Caesar Algorithm"),
    /**
     * The Xor.
     */
    Xor(XorAlgorithm.class, "Xor Algorithm"),
    /**
     * The Multiplication.
     */
    Multiplication(MultiplicationAlgorithm.class, "Multiplication Algorithm"),
    /**
     * The Double.
     */
    Double(DoubleAlgorithm.class, "Double Algorithm - run another 2 different algorithms (back to back)"),
    /**
     * The Split.
     */
    Split(SplitAlgorithm.class, "Split Algorithm - run the next algorithm with 2 keys instead of 1"),
    /**
     * The Reverse.
     */
    Reverse(ReverseAlgorithm.class, "Reverse Algorithm - run the next algorithm in reverse");


    @Getter
    private Class<? extends Algorithm> algorithmClass;
    @Getter
    private String desc;

    AlgorithmsEnum(Class<? extends Algorithm> algorithmClass, String desc) {
        this.algorithmClass = algorithmClass;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
