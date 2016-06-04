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

    private String decrypted = "_decrypted";

    public DecryptionOperation() {

    }

    @Override
    public String getDescription() {
        return "decrypt a file";
    }


    @Override
    public void run(DisplayMessage message, InputStream in, OutputStream out, int key, CipherAlgorithm cipherAlgorithm) throws KeyException, IOException {
        cipherAlgorithm.checkKey(key);
        int raw;
        byte dec;
        try {
            while ((raw = in.read()) != -1) {
                dec = cipherAlgorithm.decryptionOperation(raw, key);
                out.write(dec);
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file");
        }
    }

    @Override
    public int getKey(CipherAlgorithm cipherAlgorithm) throws IOException {
        return CliHandler.getInstance().getKey();
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
