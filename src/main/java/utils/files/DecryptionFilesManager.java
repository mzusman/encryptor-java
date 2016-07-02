package utils.files;

import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 7/2/16.
 */
public class DecryptionFilesManager extends FilesManager {

    private final static String decrypted = "_decrypted";

    @Override
    public File getOutputFile() throws IOException {
        String[] filename = getInputFile().getPath().split("\\.", 2);
        StringBuilder sp;
        if (filename.length > 1)
            sp = new StringBuilder(filename[0])
                    .append(decrypted).append(".").append(filename[1]);
        else sp = new StringBuilder(filename[0]).
                append(decrypted);
        File outputFile = new File(sp.toString());
        try {
            if (outputFile.createNewFile())
                return outputFile;
            else throw new IOException("cannot create new file for decryption");

        } catch (IOException e) {
            throw new IOException("cannot create new file for decryption");
        }
    }
}
