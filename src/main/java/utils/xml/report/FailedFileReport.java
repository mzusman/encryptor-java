package utils.xml.report;

import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by mzeus on 7/20/16.
 */
@XmlRootElement(name = "file")
@NoArgsConstructor
public class FailedFileReport extends FilesReport {
    /**
     * The E name.
     */
    @XmlElement
    String eName;
    /**
     * The E msg.
     */
    @XmlElement
    String eMsg;
    /**
     * The Stack trace.
     */
    @XmlElement
    String stackTrace;

    /**
     * Instantiates a new Failed file report.
     *
     * @param file      the file
     * @param throwable the throwable
     */
    public FailedFileReport(File file, Throwable throwable) {
        super(file, false);
        eName = throwable.getClass().getName();
        eMsg = throwable.getMessage();
        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));
        stackTrace = errors.toString();

    }
}
