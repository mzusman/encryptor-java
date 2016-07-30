package utils.files;

import exceptions.FileAlreadyExistsException;
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

    /**
     * Instantiates a new Abstract files manager.
     *
     * @param inputFile the input file
     */
    AbstractFilesManager(File inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Gets out file.
     *
     * @return the out file
     * @throws IOException the io exception
     */
    abstract public File getOutFile() throws IOException;

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(getOutFile());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(inputFile);
    }

    /**
     * Gets file extension.
     *
     * @return the file extension
     */
    abstract public String getFileExtension();

    /**
     * Create new file file.
     *
     * @param file the file
     * @return the file
     * @throws IOException the io exception
     */
    File createNewFile(File file) throws IOException {
        if (file.exists())
            throw new FileAlreadyExistsException(file.getName());
        if (file.createNewFile())
            return file;
        throw new IOException("cannot create new file ");
    }

}
