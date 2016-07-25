package utils;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by mzeus on 7/21/16.
 */
@Log4j2()
public class LogFileManager {

    private final static LogFileManager instance = new LogFileManager();

    public static LogFileManager getInstance() {
        return instance;
    }

    public void started(String desc, File file) {
        log.info("operation: " + desc + " started on file: " +
                file.getName());
    }

    public void ended(File file) {
        log.info("operation on file : " + file.getName()
                + " ended, took : " + Timer.getInstance().current());

    }

    public void error(File in, Throwable e) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        String stackTrace = errors.toString();
        if (in != null)
            log.info("file :" + in.getName() + "operation error : " + stackTrace);
        else
            log.info("file operation error : " + stackTrace);
    }
}
