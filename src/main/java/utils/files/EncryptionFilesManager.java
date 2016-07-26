package utils.files;

import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 7/2/16.
 */
public class EncryptionFilesManager extends AbstractFilesManager {

    private static final String encrypted = ".encrypted";

    public EncryptionFilesManager(File inputFile) {
        super(inputFile);
    }


    @Override
    public File getOutFile() throws IOException {
        File outputFile = new File(getInputFile().getPath() + encrypted);
        return createNewFile(outputFile);
    }

    @Override
    public String getFileExtension() {
        return encrypted;
    }


}
