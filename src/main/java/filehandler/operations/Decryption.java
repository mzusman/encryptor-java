package filehandler.operations;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.ReverseAlgorithm;
import filehandler.algorithm.cipheralgorithm.CaesarAlgorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import lombok.Cleanup;
import utils.DisplayMessage;

import java.io.*;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Created by Mor on 5/19/2016.
 */
public class Decryption implements Operation, FileHandler {

    private String decrypted = "_decrypted";
    private int key = 0;

    Decryption() {

    }

    @Override
    public String getDescription() {
        return "decrypt a file";
    }


    @Override
    public File act(DisplayMessage message, File file, Algorithm algorithm) throws KeyException, IOException {

        File outputFile = createNewFile(file);
        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        for (CipherAlgorithm cipherAlgorithm :
                algorithm.getAlgorithms()) {
            decrypt(fis, fos, getKey(algorithm), algorithm);
        }
        return outputFile;
    }

    private void decrypt(InputStream in, OutputStream out, int key, CipherAlgorithm cipherAlgorithm) throws KeyException, IOException {
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
        if (key == 0) {
            key = CliHandler.getInstance().getKey();
        }
        return key;
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
