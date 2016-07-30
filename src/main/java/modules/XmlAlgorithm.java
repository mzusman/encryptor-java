package modules;

import commandline.AlgorithmsEnum;
import domain.algorithm.Algorithm;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 7/11/16.
 */
public class XmlAlgorithm implements XmlFileManager<Algorithm> {
    private static XmlAlgorithm instance = new XmlAlgorithm();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static XmlAlgorithm getInstance() {
        return instance;
    }

    private JAXBContext jaxbContext;
    private Schema schema;

    private XmlAlgorithm() {

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

    private void writeAlgorithmToXml(Algorithm algorithm, File directory) throws JAXBException, IOException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File(directory, "algorithm.xml");
        if (file.exists())
            file.delete();
        if (file.createNewFile())
            marshaller.marshal(algorithm, file);
    }

    private Algorithm readAlgorithmFromXml() throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);
        return (Algorithm) unmarshaller.unmarshal(new File("default-algorithm.xml"));
    }

    private Algorithm readAlgorithmFromXml(File file) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);
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

    @Override
    public void writeAlgorithm(Algorithm algorithm, File file) throws JAXBException, IOException {
        writeAlgorithmToXml(algorithm, file);
    }

    @Override
    public Algorithm readAlgorithm(File file) throws JAXBException {
        if (file == null)
            return readAlgorithmFromXml();
        else return readAlgorithmFromXml(file);
    }
}
