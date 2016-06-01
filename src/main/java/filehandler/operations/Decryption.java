package filehandler.operations;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.Cleanup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Mor on 5/19/2016.
 */
public class Decryption implements Operation {

    public String decrypted = "_decrypted";
    int key = 0;

    @Override
    public String getDescription() {
        return "decrypt a file";
    }


    @Override
    public File act(File file, Algorithm algorithm) throws KeyException, IOException {

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
        } catch (IOException e) {
            throw new IOException("cannot create new file for decryption");
        }
        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        algorithm.decrypt(fis, fos, getKey(algorithm));

        return outputFile;

    }

    @Override
    public int getKey(Algorithm algorithm) throws IOException {
        if (key == 0) {
            key = CliHandler.getInstance().getKey();
        }
        return key;
    }


}
