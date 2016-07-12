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
    Double(DoubleAlgorithm.class, "Double Algorithm"),
    Multiplication(MultiplicationAlgorithm.class, "Multiplication Algorithm"),
    Split(SplitAlgorithms.class, "Split Algorithm"),
    Reverse(ReverseAlgorithm.class, "Reverse Algorithm");


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
