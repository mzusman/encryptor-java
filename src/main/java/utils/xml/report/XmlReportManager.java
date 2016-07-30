package utils.xml.report;

import utils.Timer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by mzeus on 7/20/16.
 */
@XmlRootElement(name = "Report")
public class XmlReportManager implements ReportManager {

    private JAXBContext jaxbContext;

    @XmlElementWrapper(name = "files")
    @XmlAnyElement(lax = true)
    private ArrayList<FilesReport> queue = new ArrayList<>();

    private File dirFile;

    /**
     * Instantiates a new Xml report manager.
     *
     * @param dirFile the dir file
     */
    public XmlReportManager(File dirFile) {
        this.dirFile = dirFile;
        try {
            jaxbContext = JAXBContext.newInstance(XmlReportManager.class, SuccFileReport.class
                    , FailedFileReport.class, FilesReport.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    /**
     * Instantiates a new Xml report manager.
     */
    public XmlReportManager() {

    }

    @Override
    public void writeFileDone(File file) {
        SuccFileReport report = new SuccFileReport(file, Timer.getInstance().current());
        queue.add(report);
    }

    @Override
    public void writeFileError(File file, Throwable throwable) {
        FailedFileReport report = new FailedFileReport(file, throwable);
        queue.add(report);
    }

    @Override
    public void writeReport() {
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this, new File(dirFile, "report.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
