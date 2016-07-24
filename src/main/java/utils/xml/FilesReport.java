package utils.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by mzeus on 7/20/16.
 */
@XmlRootElement(name = "file")
@NoArgsConstructor
class FilesReport {
    @XmlElement
    private
    String fileName;
    @XmlElement
    private
    String status;

    FilesReport(File file, boolean status) {
        if (status)
            this.status = "success";
        else this.status = "failed";
        if (file != null)
            this.fileName = file.getName();
        else fileName = "unknown";
    }

}
