package utils.xml;

import utils.Timer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mzeus on 7/20/16.
 */
@XmlRootElement(name = "Report")
public class XmlReportManager implements Observer{
    private static XmlReportManager instance = new XmlReportManager();

    public static XmlReportManager getInstance() {
        return instance;
    }

    private JAXBContext jaxbContext;

    @XmlElementWrapper(name = "files")
    @XmlAnyElement(lax = true)
    private ArrayList<FilesReport> queue = new ArrayList<>();


    private XmlReportManager() {

        try {
            jaxbContext = JAXBContext.newInstance(XmlReportManager.class, SuccFileReport.class
                    , FailedFileReport.class, FilesReport.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }


    public void writeFileDone(File file) {
        SuccFileReport report = new SuccFileReport(file, Timer.getInstance().current());
        queue.add(report);
    }

    public void writeFileError(File file, Exception e) {
        FailedFileReport report = new FailedFileReport(file, e);
        queue.add(report);
    }

    public void writeReport(File dirFile) {
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this, new File(dirFile, "report.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
