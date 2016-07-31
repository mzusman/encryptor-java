package utils.files;

import domain.algorithm.Algorithm;
import lombok.Cleanup;

import java.io.*;

/**
 * Manages the key file.
 */
public class KeyFilesManager extends AbstractFilesManager {
    private final static String KEY_FILE_NAME = "key.bin";

    /**
     * Instantiates a new Key files manager.
     *
     * @param inputFile the input file
     */
    public KeyFilesManager(File inputFile) {
        super(inputFile);
    }

    @Override
    public File getOutFile() throws IOException {
        File file;
        if (!getInputFile().isDirectory())
            file = new File(getInputFile().getParentFile().getPath() + File.separator + KEY_FILE_NAME);
        else file = new File(getInputFile().getPath() + File.separator + KEY_FILE_NAME);
        if (file.exists())
            file.delete();
        if (file.createNewFile())
            return file;
        throw new IOException("Cannot create a file");
    }

    @Override
    public String getFileExtension() {
        return KEY_FILE_NAME;
    }

    /**
     * Write algorithms to file.
     *
     * @param algorithms the algorithms
     * @throws IOException the io exception
     */
    public void writeAlgorithmsToFile(Algorithm algorithms) throws IOException {
        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getOutFile()));
        oos.writeObject(algorithms);
    }

    /**
     * Read algorithms from file algorithm.
     *
     * @return the algorithm
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Algorithm readAlgorithmsFromFile() throws IOException, ClassNotFoundException {
        File file = new File(getInputFile().getParentFile().getPath() + File.separator + KEY_FILE_NAME);
        if (!file.exists())
            throw new IOException();
        @Cleanup ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        return (Algorithm) ois.readObject();
    }


}
