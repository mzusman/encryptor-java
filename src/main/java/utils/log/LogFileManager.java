package utils.log;

import lombok.extern.log4j.Log4j2;
import utils.Timer;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by mzeus on 7/21/16.
 */
@Log4j2()
public class LogFileManager implements Logging {

    /**
     * Instantiates a new Log file manager.
     */
    public LogFileManager() {

    }

    private void started(File file) {
        log.info("operation: started on file: " +
                file.getName());
    }

    private void ended(File file) {
        log.info("operation on file : " + file.getName()
                + " ended, took : " + Timer.getInstance().current());

    }

    private void error(File in, Throwable e) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        String stackTrace = errors.toString();
        if (in != null)
            log.info("file :" + in.getName() + "operation error : " + stackTrace);
        else
            log.info("file operation error : " + stackTrace);
    }

    @Override
    public void fileStarted(File file) {
        started(file);
    }

    @Override
    public void fileFinished(File file) {
        ended(file);
    }

    @Override
    public void fileError(File file, Throwable throwable) {
        error(file, throwable);
    }

}
