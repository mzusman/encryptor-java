package utils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 28/07/16.
 */
public interface XmlFileManager<T> {
    void writeAlgorithm(T t, File file) throws JAXBException, IOException;

    T readAlgorithm(File file) throws JAXBException;
}
