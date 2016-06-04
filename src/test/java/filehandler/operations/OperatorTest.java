package filehandler.operations;

import filehandler.algorithm.DoubleAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

/**
 * Created by mzeus on 03/06/16.
 */
public class OperatorTest {
    @Rule
    TemporaryFolder folder = new TemporaryFolder();

//    @Test
//    void test() throws Exception {
//        File file = folder.newFile();
//        Operator operator = new OperatorWithMsg(new DecryptionOperation(), System.out::println);
//        operator.act(file, new DoubleAlgorithm(new CaesarAlgorithm(), new NormalAlgorithm(new CaesarAlgorithm())));
//
//    }

}