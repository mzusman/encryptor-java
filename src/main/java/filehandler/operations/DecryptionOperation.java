package filehandler.operations;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import utils.DisplayMessage;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class DecryptionOperation extends AbstractOperation {

    private final String decrypted = "_decrypted";

    public DecryptionOperation() {

    }

    @Override
    public String getDescription() {
        return "decrypt a file";
    }


    @Override
    public int findKey(CipherAlgorithm cipherAlgorithm) throws IOException {
        return CliHandler.getInstance().getKey();
    }

    @Override
    public byte operate(CipherAlgorithm algorithm, int raw, int key) {
        return algorithm.decryptionOperation(raw, key);
    }


    @Override
    public File createNewFile(File file) throws IOException {
        String[] filename = file.getPath().split("\\.", 2);
        StringBuilder sp;
        if (filename.length > 1)
            sp = new StringBuilder(filename[0])
                    .append(decrypted).append(".").append(filename[1]);
        else sp = new StringBuilder(filename[0]).
                append(decrypted);
        File outputFile = new File(sp.toString());
        try {
            outputFile.createNewFile();
            return outputFile;
        } catch (IOException e) {
            throw new IOException("cannot create new file for decryption");
        }
    }
}
