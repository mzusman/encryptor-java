package utils.files;

import exceptions.CannotReadFromFileException;
import utils.StreamManager;

import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by mzeus on 7/6/16.
 */
public class DirectoryFilesManager extends FilesManager {

    private final ArrayList<File> inFiles = new ArrayList<>();
    private final ArrayList<File> outFiles = new ArrayList<>();
    private final ArrayList<Map.Entry<File, File>> fileHashMap = new ArrayList<>();
    private FilesManager filesManager;
    private File opDir;

    public DirectoryFilesManager(FilesManager filesManager) throws IOException {
        super(filesManager.inputFile);
        this.filesManager = filesManager;
        File dir = filesManager.getInputFile();
        File[] inFiles = filesManager.getInputFile().listFiles();
        if (inFiles != null) {
            for (File inFile : inFiles) {
                if (inFile.isFile())
                    this.inFiles.add(inFile);
            }
        }
    }


    @Override
    public File getOutFile() throws IOException {
        return filesManager.getOutFile();
    }

    private void createOutputFiles() throws IOException {
        if (opDir == null) {
            opDir = new File(filesManager.getInputFile(), getFileExtension());
            System.out.println("Poke");
            if (!opDir.mkdir())
                throw new CannotReadFromFileException();
            for (File inFile : inFiles) {
                File outFile = new File(opDir, inFile.getName());
                if (!outFile.createNewFile())
                    throw new CannotReadFromFileException();
                fileHashMap.add(new AbstractMap.SimpleEntry<>(inFile, outFile));
            }
        }
    }

    @Override
    public synchronized OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(outFiles.get(0));
    }

    @Override
    public synchronized InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(inFiles.get(0));
    }

    public synchronized File getInputFile(int i) throws IOException {
        createOutputFiles();
        return fileHashMap.get(i).getKey();
    }

    public synchronized File getOutputFile(int i) throws IOException {
        createOutputFiles();
        return fileHashMap.get(i).getValue();
    }

    public int size() throws IOException {
        return inFiles.size();
    }

    @Override
    public String getFileExtension() {
        return filesManager.getFileExtension().substring(1);
    }


}
