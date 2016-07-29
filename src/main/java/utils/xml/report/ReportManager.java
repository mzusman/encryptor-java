package utils.xml.report;

import java.io.File;

/**
 * Created by mzeus on 29/07/16.
 */
public interface ReportManager {
    void writeFileDone(File file);

    void writeFileError(File file, Throwable throwable);

    void writeReport();
}
