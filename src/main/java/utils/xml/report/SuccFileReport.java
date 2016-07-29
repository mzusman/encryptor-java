package utils.xml.report;

import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

/**
 * Created by mzeus on 7/20/16.
 */
@XmlRootElement(name = "file")
@NoArgsConstructor
public class SuccFileReport extends FilesReport {
    @XmlElement
    long time;

    public SuccFileReport(File file, long time) {
        super(file, true);
        this.time = time;
    }
}
