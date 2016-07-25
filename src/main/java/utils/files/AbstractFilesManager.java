package utils.files;

import com.google.inject.Inject;
import exceptions.EmptyDirectoryException;
import utils.StreamManager;
import lombok.Getter;
import lombok.Setter;

import java.io.*;

/**
 * Created by mzeus on 7/2/16.
 */
public abstract class AbstractFilesManager implements StreamManager {
    @Getter
    @Setter
    private File inputFile;
    @Setter
    private File outFile;

    AbstractFilesManager(File inputFile) {
        this.inputFile = inputFile;
    }

    abstract public File getOutFile() throws IOException;

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(getOutFile());
    }

    @Override
    public InputStream getInputStream() throws IOException{
        return new FileInputStream(inputFile);
    }

    abstract public String getFileExtension();

}
