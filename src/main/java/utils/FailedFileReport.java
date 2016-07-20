package utils;

import lombok.AllArgsConstructor;
import lombok.Data;
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

    public FailedFileReport(File file, Exception e) {
        super(file, false);
        eName = e.getClass().getName();
        eMsg = e.getMessage();
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        stackTrace = errors.toString();

    }
}
