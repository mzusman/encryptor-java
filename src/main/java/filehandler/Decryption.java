package filehandler;

import commandline.CliHandler;
import filehandler.algorithm.CipherAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Cleanup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Mor on 5/19/2016.
 */
public class Decryption implements Operation {

    public String decrypted = "_decrypted";

    public String getDescription() {
        return "decrypt a file";
    }


    @Override
    public File act(File file, CipherAlgorithm algorithm) throws IOException {

        String[] filename = file.getPath().split("\\.", 2);
        System.out.println(filename.length);

        StringBuilder sp = new StringBuilder(filename[0])
                .append(decrypted).append(".").append(filename[1]);
        File outputFile = new File(sp.toString());
        System.out.println(sp.toString());
        outputFile.createNewFile();
        @Cleanup FileInputStream fis = new FileInputStream(file);
        @Cleanup FileOutputStream fos = new FileOutputStream(outputFile);
        int key = CliHandler.getInstance().getKey();
        algorithm.decrypt(fis, fos, key);
        return outputFile;


    }

}
