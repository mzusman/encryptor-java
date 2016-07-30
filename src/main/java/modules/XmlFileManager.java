package modules;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 28/07/16.
 *
 * @param <T> the type parameter
 */
public interface XmlFileManager<T> {
    /**
     * Write algorithm.
     *
     * @param t    the t
     * @param file the file
     * @throws JAXBException the jaxb exception
     * @throws IOException   the io exception
     */
    void writeAlgorithm(T t, File file) throws JAXBException, IOException;

    /**
     * Read algorithm t.
     *
     * @param file the file
     * @return the t
     * @throws JAXBException the jaxb exception
     */
    T readAlgorithm(File file) throws JAXBException;
}
