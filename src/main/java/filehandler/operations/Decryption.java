package filehandler.operations;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.AlgorithmOnce;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
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

    @Override
    public String getDescription() {
        return "decrypt a file";
    }


    @Override
    public File act(File file, CipherAlgorithm cipherAlgorithm) throws IOException, KeyException {

        String[] filename = file.getPath().split("\\.", 2);
        StringBuilder sp;
        if (filename.length > 1)
            sp = new StringBuilder(filename[0])
                    .append(decrypted).append(".").append(filename[1]);
        else sp = new StringBuilder(filename[0]).
                append(decrypted);
        File outputFile = new File(sp.toString());
        outputFile.createNewFile();
        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        int key = CliHandler.getInstance().getKey();
        Algorithm algorithm = new AlgorithmOnce(cipherAlgorithm);
        algorithm.decrypt(fis, fos, key);
        return outputFile;

    }

}
