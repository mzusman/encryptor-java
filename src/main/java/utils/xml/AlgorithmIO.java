package utils.xml;

import filehandler.algorithm.Algorithm;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 28/07/16.
 */
public interface AlgorithmIO {
    void writeAlgorithm(Algorithm algorithm, File file) throws JAXBException, IOException;

    Algorithm readAlgorithm(File file) throws JAXBException;
}
