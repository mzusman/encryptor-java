package utils.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

/**
 * Class FileStatus - class used as
 * a utility to notify a file start or file end
 */
@AllArgsConstructor
public class FileStatus {
    /**
     * The File.
     */
    @Getter
    File file;
}
