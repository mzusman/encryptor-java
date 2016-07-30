package utils.xml.report;

import java.io.File;

/**
 * Created by mzeus on 29/07/16.
 */
public interface ReportManager {
    /**
     * Write file done.
     *
     * @param file the file
     */
    void writeFileDone(File file);

    /**
     * Write file error.
     *
     * @param file      the file
     * @param throwable the throwable
     */
    void writeFileError(File file, Throwable throwable);

    /**
     * Write report.
     */
    void writeReport();
}
