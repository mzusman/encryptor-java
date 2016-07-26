package commandline;

import filehandler.algorithm.Algorithm;
import filehandler.algorithm.DoubleAlgorithm;
import filehandler.algorithm.ReverseAlgorithm;
import filehandler.algorithm.SplitAlgorithms;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import lombok.Getter;

/**
 * Created by mzeus on 7/11/16.
 */
public enum AlgorithmsEnum {

    Caesar(CaesarAlgorithm.class, "Caesar Algorithm"),
    Xor(XorAlgorithm.class, "Xor Algorithm"),
    Multiplication(MultiplicationAlgorithm.class, "Multiplication Algorithm"),
    Double(DoubleAlgorithm.class, "Double Algorithm - run another 2 different algorithms (back to back)"),
    Split(SplitAlgorithms.class, "Split Algorithm - run the next algorithm with 2 keys instead of 1"),
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
