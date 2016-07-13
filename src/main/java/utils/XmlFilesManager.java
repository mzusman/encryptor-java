package utils;

import com.sun.jmx.remote.internal.Unmarshal;
import commandline.AlgorithmsEnum;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.DoubleAlgorithm;
import filehandler.algorithm.ReverseAlgorithm;
import filehandler.algorithm.SplitAlgorithms;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.MultiplicationAlgorithm;
import filehandler.algorithm.cipheralgorithm.SingleAlgorithm;
import filehandler.algorithm.cipheralgorithm.XorAlgorithm;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
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
//    private Schema schema;

    private XmlFilesManager() {

//        Class[] classes = new Class[AlgorithmsEnum.values().length];
//        for (int i = 0; i < classes.length; i++) {
//            classes[i] = AlgorithmsEnum.values()[i].getAlgorithmClass();
//        }
        try {
            jaxbContext = JAXBContext.newInstance(CaesarAlgorithm.class,
                    XorAlgorithm.class, MultiplicationAlgorithm.class,
                    DoubleAlgorithm.class, SplitAlgorithms.class, ReverseAlgorithm.class);

//            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            schema = schemaFactory.newSchema(new File("schema1.xsd"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void writeAlgorithmToXml(Algorithm algorithm) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setSchema(schema);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(algorithm, new File("default-algorithm.xml"));
    }

    public void writeAlgorithmToXml(Algorithm algorithm, File directory) throws JAXBException, IOException {
        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setSchema(schema);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File(directory, "algorithm.xml");
        if (file.exists())
            file.delete();
        if (file.createNewFile())
            marshaller.marshal(algorithm, file);
    }

    public Algorithm readAlgorithmFromXml() throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        unmarshaller.setSchema(schema);
        return (Algorithm) unmarshaller.unmarshal(new File("default-algorithm.xml"));
    }

    public Algorithm readAlgorithmFromXml(File file) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        unmarshaller.setSchema(schema);
        unmarshaller.setListener(new Unmarshaller.Listener() {
            @Override
            public void beforeUnmarshal(Object target, Object parent) {
                super.beforeUnmarshal(target, parent);
            }

            @Override
            public void afterUnmarshal(Object target, Object parent) {
                if (parent instanceof Algorithm && target instanceof Algorithm)
                    ((Algorithm) parent).pushAlgorithm((Algorithm) target);
                else super.afterUnmarshal(target, parent);
            }
        });
        return (Algorithm) unmarshaller.unmarshal(file);
    }


}
