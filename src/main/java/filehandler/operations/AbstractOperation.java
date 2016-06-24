package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.Cleanup;
import utils.Selectable;

import java.io.*;
import java.util.Observable;

/**
 * Created by mzeus on 29/05/16.
 */
public abstract class AbstractOperation extends Observable implements Operation, Selectable, FileHandler {

    @Override
    public File init(File file, CipherAlgorithm algorithm) throws IOException, KeyException {
        File outputFile = createNewFile(file);
        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        run(fis, fos, findKey(algorithm), algorithm);
        return outputFile;
    }

    @Override
    public void run(InputStream in, OutputStream out, int key, CipherAlgorithm cipherAlgorithm) throws IOException {
        int raw;
        byte enc;
        try {
            while ((raw = in.read()) != -1) {
                enc = operate(cipherAlgorithm, raw, key);
                out.write(enc);
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file");
        }

    }


    @Override
    public String getDescription() {
        return "make an operation";
    }
}
