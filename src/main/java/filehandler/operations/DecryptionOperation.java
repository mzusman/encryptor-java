package filehandler.operations;

import filehandler.algorithm.ListOfAlgorithms;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class DecryptionOperation extends AbstractOperation {

    private final String decrypted = "_decrypted";


    @Override
    public void findKey(ListOfAlgorithms algorithms) throws IOException {
//        algorithms.setDecryptionKey();
    }

    @Override
    public byte operate(ListOfAlgorithms algorithm, int raw, int index) {
        return algorithm.decryptionOperation(raw, index);
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
