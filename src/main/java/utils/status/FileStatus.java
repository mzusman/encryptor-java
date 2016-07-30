package utils.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

/**
 * Created by mzeus on 29/07/16.
 */
@AllArgsConstructor
public class FileStatus {
    /**
     * The File.
     */
    @Getter
    File file;
}
