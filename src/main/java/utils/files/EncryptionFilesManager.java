package utils.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mzeus on 7/2/16.
 */
public class EncryptionFilesManager extends FilesManager {

    private static final String encrypted = ".encrypted";

    public EncryptionFilesManager(File inputFile) {
        super(inputFile);
    }


    @Override
    public File getOutFile() throws IOException {
        String exception = "cannot create a new file for encryption";
        File outputFile = new File(getInputFile().getPath() + encrypted);
        if (outputFile.exists())
            outputFile.delete();
        if (outputFile.createNewFile())
            return outputFile;
        throw new IOException(exception);
    }
}
