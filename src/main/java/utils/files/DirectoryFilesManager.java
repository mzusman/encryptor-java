package utils.files;

import lombok.AllArgsConstructor;
import utils.StreamManager;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by mzeus on 7/6/16.
 */
public class DirectoryFilesManager implements StreamManager {

    private FilesManager filesManager;
    ArrayList<File> files = new ArrayList<>();
    Semaphore sem = new Semaphore(5);

    public DirectoryFilesManager(FilesManager filesManager) {
        this.filesManager = filesManager;
        File[] inFiles = filesManager.getInputFile().listFiles();
        if (inFiles != null) {
            for (File inFile : inFiles) {
                if (inFile.isFile())
                    files.add(inFile);
            }
        }
    }


    @Override
    public OutputStream getOutputStream() throws IOException {

        return null;
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return null;
    }
}
