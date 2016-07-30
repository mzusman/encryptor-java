package modules;

import com.google.inject.AbstractModule;
import lombok.AllArgsConstructor;
import utils.log.LogFileManager;
import utils.log.Logging;
import utils.xml.report.ReportManager;
import utils.xml.report.XmlReportManager;

import java.io.File;

/**
 * Created by mzeus on 29/07/16.
 */
@AllArgsConstructor
public class LoggingModule extends AbstractModule {
    /**
     * The File.
     */
    File file;

    @Override
    protected void configure() {
        bind(Logging.class).to(LogFileManager.class);
        bind(ReportManager.class).to(XmlReportManager.class);
        bind(XmlReportManager.class).toInstance(new XmlReportManager(file));
    }
}
