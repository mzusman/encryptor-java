package utils.files;

import filehandler.algorithm.ListOfAlgorithms;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import lombok.Cleanup;

import java.io.*;

/**
 * Created by mzeus on 7/2/16.
 */
public class KeyFilesManager extends FilesManager {
    private final static String KEY_FILE_NAME = "key.bin";

    @Override
    public File getOutputFile() throws IOException {
        File file = new File(getInputFile().getParentFile().getPath() + KEY_FILE_NAME);
        if (file.exists())
            return file;
        if (file.createNewFile())
            return file;
        throw new IOException("Cannot create a file");
    }

    public void writeAlgorithmsToFile(ListOfAlgorithms algorithms) throws IOException {
        @Cleanup ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getOutputFile()));
        oos.writeObject(algorithms);
    }

    public ListOfAlgorithms readAlgorithmsFromFile() throws IOException, ClassNotFoundException {
        File file = new File(getInputFile().getParentFile().getPath() + KEY_FILE_NAME);
        if (!file.exists())
            return new NormalAlgorithm().addAlgorithm(new CaesarAlgorithm());
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        return (ListOfAlgorithms) ois.readObject();
    }


}
