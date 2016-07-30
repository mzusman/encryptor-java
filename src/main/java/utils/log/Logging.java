package utils.log;

import java.io.File;

/**
 * Created by mzeus on 29/07/16.
 */
public interface Logging {

    /**
     * File started.
     *
     * @param file the file
     */
    void fileStarted(File file);

    /**
     * File finished.
     *
     * @param file the file
     */
    void fileFinished(File file);

    /**
     * File error.
     *
     * @param file      the file
     * @param throwable the throwable
     */
    void fileError(File file, Throwable throwable);

}
