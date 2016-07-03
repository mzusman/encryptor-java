package utils.files;

import filehandler.algorithm.Algorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class DecryptionFilesManagerTest {
    @Test
    public void getOutFile() throws Exception {
        ArrayList<Algorithm<Integer>> list = new ArrayList<>();
        list.add(new XorAlgorithm());
        XorAlgorithm xorAlgorithm1 = (XorAlgorithm) list.get(0);
        XorAlgorithm xorAlgorithm2 = (XorAlgorithm) list.get(0);
        xorAlgorithm1.setDecryptionKey(1, 0, null);
        xorAlgorithm2.setDecryptionKey(2, 0, null);
        System.out.println(xorAlgorithm1.getKey(null,0));
        System.out.println(xorAlgorithm2.getKey(null,0));
        assertEquals(xorAlgorithm1.getKey(null, 0), xorAlgorithm2.getKey(null, 0));
    }

}