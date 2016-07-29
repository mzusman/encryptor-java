package boot;

import com.google.inject.AbstractModule;
import lombok.AllArgsConstructor;
import utils.LogFileManager;
import utils.xml.Logging;
import utils.xml.ReportManager;
import utils.xml.XmlReportManager;

import java.io.File;

/**
 * Created by mzeus on 29/07/16.
 */
@AllArgsConstructor
public class LoggingModule extends AbstractModule {
    File file;

    @Override
    protected void configure() {
        bind(Logging.class).to(LogFileManager.class);
        bind(ReportManager.class).to(XmlReportManager.class);
        bind(File.class).toInstance(file.getParentFile());
    }
}
