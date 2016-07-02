package utils.files;

import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 7/2/16.
 */
public class EncryptionFilesManager extends FilesManager {

    private static final String encrypted = ".encrypted";

    @Override
    public File getOutputFile() throws IOException {
        String exception = "cannot create a new file for encryption";
        File outputFile = new File(getInputFile().getPath() + encrypted);
        try {
            if (!outputFile.createNewFile())
                throw new IOException(exception);
            return outputFile;
        } catch (IOException e) {
            throw new IOException(exception);
        }
    }
}
