package utils.xml;

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
    @XmlElement
    String eName;
    @XmlElement
    String eMsg;
    @XmlElement
    String stackTrace;

    public FailedFileReport(File file, Throwable throwable) {
        super(file, false);
        eName = throwable.getClass().getName();
        eMsg = throwable.getMessage();
        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));
        stackTrace = errors.toString();

    }
}
