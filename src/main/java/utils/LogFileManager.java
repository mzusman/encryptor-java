package utils;

import lombok.extern.log4j.Log4j2;

import java.io.File;

/**
 * Created by mzeus on 7/21/16.
 */
@Log4j2
public class LogFileManager {

    private final static LogFileManager instance = new LogFileManager();

    public static LogFileManager getInstance() {
        return instance;
    }

    public void started(String desc, File file) {
        log.info("operation: " + desc + " started on file:" +
                file.getName());
    }

    public void ended(File file) {
        log.info("operation on file : " + file
                + "ended, took: " + Timer.getInstance().current());

    }
}
