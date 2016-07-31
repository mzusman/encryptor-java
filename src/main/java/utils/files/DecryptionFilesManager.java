package utils.files;

import exceptions.FileAlreadyExistsException;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.MessageFormat;

/**
 * Manages the decryption file
 */
public class DecryptionFilesManager extends AbstractFilesManager {

    private final static String decrypted = "_decrypted";

    /**
     * Instantiates a new Decryption files manager.
     *
     * @param inputFile the input file
     */
    public DecryptionFilesManager(File inputFile) {
        super(inputFile);
    }


    @Override
    public File getOutFile() throws IOException {
        String[] filename = getInputFile().getPath().split("\\.", 2);
        StringBuilder sp;
        sp = new StringBuilder(filename[0]).append(decrypted);
        if (filename.length > 1)
            sp.append(".").append(filename[1]);
        File outputFile = new File(sp.toString());
        return createNewFile(outputFile);
    }

    @Override
    public String getFileExtension() {
        return decrypted;
    }
}
