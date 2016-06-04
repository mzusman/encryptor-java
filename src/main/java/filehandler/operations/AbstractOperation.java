package filehandler.operations;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.Cleanup;
import utils.DisplayMessage;
import utils.Selectable;

import java.io.*;
import java.util.List;

/**
 * Created by mzeus on 29/05/16.
 */
public abstract class AbstractOperation implements Operation, FileHandler, Selectable {
    DisplayMessage displayMessage;

    @Override
    public File init(DisplayMessage message, File file, Algorithm algorithm) throws IOException, KeyException {
        if (message != null)
            this.displayMessage = message;
        File outputFile = createNewFile(file);
        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        preAction(fis, fos, algorithm);
        return outputFile;
    }

    private void preAction(InputStream in, OutputStream out, Algorithm algorithm) throws IOException, KeyException {
        if (algorithm.exceptedSize() == 1) {
            preRun(in, out, algorithm);
        } else
            for (CipherAlgorithm cipherAlgorithm :
                    algorithm.getAlgorithms()) {
                if (cipherAlgorithm instanceof Algorithm)
                    if (((Algorithm) cipherAlgorithm).getAlgorithms().size() > 1)
                        preAction(in, out, (Algorithm) cipherAlgorithm);
                    else
                        preRun(in, out, cipherAlgorithm);
                else
                    preRun(in, out, cipherAlgorithm);
            }
    }

    private void preRun(InputStream in, OutputStream out, CipherAlgorithm cipherAlgorithm) throws IOException, KeyException {
        int key = getKey(cipherAlgorithm);
        run(displayMessage, in, out, key, cipherAlgorithm);
        displayMessage.display(String.format("%s: using key: %d , save it for future use!",
                cipherAlgorithm.getDescription(), key));
    }


    @Override
    public String getDescription() {
        return "make an operation";
    }
}
