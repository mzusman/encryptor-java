package utils.files;

import utils.StreamManager;
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
    @Setter
    File outFile;

    FilesManager(File inputFile) {
        this.inputFile = inputFile;
    }

    abstract public File getOutFile() throws IOException;

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(getOutFile());
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(inputFile);
    }


}
