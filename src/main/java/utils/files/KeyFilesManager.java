package utils.files;

import domain.algorithm.Algorithm;
import lombok.Cleanup;

import java.io.*;

/**
 * Created by mzeus on 7/2/16.
 */
public class KeyFilesManager extends AbstractFilesManager {
    private final static String KEY_FILE_NAME = "key.bin";

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

    public void writeAlgorithmsToFile(Algorithm algorithms) throws IOException {
        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getOutFile()));
        oos.writeObject(algorithms);
    }

    public Algorithm readAlgorithmsFromFile() throws IOException, ClassNotFoundException {
        File file = new File(getInputFile().getParentFile().getPath() + File.separator + KEY_FILE_NAME);
        if (!file.exists())
            throw new IOException();
        @Cleanup ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        return (Algorithm) ois.readObject();
    }


}
