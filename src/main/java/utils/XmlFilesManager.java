package utils;

import com.sun.jmx.remote.internal.Unmarshal;
import commandline.AlgorithmsEnum;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.DoubleAlgorithm;
import filehandler.algorithm.cipheralgorithm.SingleAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.transform.Result;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mzeus on 7/11/16.
 */
public class XmlFilesManager {
    private static XmlFilesManager instance = new XmlFilesManager();

    public static XmlFilesManager getInstance() {
        return instance;
    }

    private JAXBContext jaxbContext;
    private Schema schema;

    private XmlFilesManager() {

        Class[] classes = new Class[AlgorithmsEnum.values().length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = AlgorithmsEnum.values()[i].getAlgorithmClass();
        }
        try {
            jaxbContext = JAXBContext.newInstance(classes, null);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(new File("schema1.xsd"));
        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
        }
    }

    public void writeAlgorithmToXml(Algorithm algorithm) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(algorithm, new File("default-algorithm.xml"));
    }

    public void writeAlgorithmToXml(Algorithm algorithm, File directory) throws JAXBException, IOException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File(directory, "algorithm.xml");
        if (file.exists())
            file.delete();
        if (file.createNewFile())
            marshaller.marshal(algorithm, file);
    }

    public Algorithm readAlgorithmFromXml() throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);
        return (Algorithm) unmarshaller.unmarshal(new File("default-algorithm.xml"));
    }

    public Algorithm readAlgorithmFromXml(File file) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);
        return (Algorithm) unmarshaller.unmarshal(file);
    }
}
