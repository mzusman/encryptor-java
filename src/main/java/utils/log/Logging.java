package utils.log;

import java.io.File;

/**
 * Created by mzeus on 29/07/16.
 */
public interface Logging {

    void fileStarted(File file);

    void fileFinished(File file);

    void fileError(File file, Throwable throwable);

}
