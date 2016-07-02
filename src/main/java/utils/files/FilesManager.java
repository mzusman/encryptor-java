package utils.files;

import filehandler.operations.StreamManager;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;

import java.io.*;

/**
 * Created by mzeus on 7/2/16.
 */
public abstract class FilesManager implements StreamManager {
    @Getter
    @Setter
    File inputFile;


    public abstract File getOutputFile() throws IOException;

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(getOutputFile());
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(inputFile);
    }
}
