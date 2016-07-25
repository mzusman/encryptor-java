package utils.files;

import exceptions.FileAlreadyExistsException;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.MessageFormat;

/**
 * Created by mzeus on 7/2/16.
 */
public class DecryptionFilesManager extends AbstractFilesManager {

    private final static String decrypted = "_decrypted";

    public DecryptionFilesManager(File inputFile) {
        super(inputFile);
    }


    @Override
    public File getOutFile() throws IOException {
        String[] filename = getInputFile().getPath().split("\\.", 2);
        StringBuilder sp;
        sp = new StringBuilder(filename[0]) .append(decrypted);
        if (filename.length > 1)
            sp.append(".").append(filename[1]);
        File outputFile = new File(sp.toString());
        if (outputFile.exists())
            throw new FileAlreadyExistsException(outputFile.getName());
        if (outputFile.createNewFile())
            return outputFile;
        throw new IOException("cannot create new file for decryption");
    }

    @Override
    public String getFileExtension() {
        return decrypted;
    }
}
