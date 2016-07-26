package utils.files;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import exceptions.CannotCreateFileException;
import exceptions.EmptyDirectoryException;
import exceptions.FileAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by mzeus on 7/6/16.
 */
public class DirectoryFilesManager extends AbstractFilesManager {

    private ArrayList<File> inFiles = new ArrayList<>();
    private final ArrayList<Pair> inToOutMap = new ArrayList<>();
    private final AbstractFilesManager filesManager;
    private File opDir;

    @Inject
    public DirectoryFilesManager(@Named("decorator") AbstractFilesManager filesManager) throws IOException {
        super(filesManager.getInputFile());
        this.filesManager = filesManager;
    }

    private void initInFiles() throws EmptyDirectoryException {
        if (inFiles.isEmpty()) {
            File[] inFiles = filesManager.getInputFile().listFiles();
            if (inFiles != null && inFiles.length > 0)
                this.inFiles = Arrays.stream(inFiles).filter(f -> f.isFile() && f.canRead())
                        .collect(Collectors.toCollection(ArrayList::new));
            else throw new EmptyDirectoryException();
        }
    }

    @Override
    public File getOutFile() throws IOException {
        return filesManager.getOutFile();
    }

    private void createOutputFiles() throws IOException {
        if (opDir == null) {
            opDir = new File(filesManager.getInputFile(), getFileExtension());
            if (opDir.exists())
                throw new FileAlreadyExistsException(opDir.getName());
            if (!opDir.mkdir()) {
                throw new CannotCreateFileException(opDir.getName());
            }
            if (inFiles.isEmpty())
                initInFiles();
            for (File inFile : inFiles) {
                File outFile = new File(opDir, inFile.getName());
                if (!outFile.createNewFile())
                    throw new CannotCreateFileException(opDir.getName());
                inToOutMap.add(new Pair(inFile, outFile));
            }
            if (inToOutMap.isEmpty())
                throw new EmptyDirectoryException();
        }
    }

    /***
     * should'nt be used - gives back the first file
     * @return
     * @throws IOException
     */
    @Override
    public synchronized OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(getOutputFile(0));
    }

    /***
     * should'nt be used - gives back only the first file in the directory
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public synchronized InputStream getInputStream() throws IOException {
        return new FileInputStream(getInputFile(0));
    }

    /**
     * lazy initiation
     *
     * @param i
     * @return
     * @throws IOException
     */
    public synchronized File getInputFile(int i) throws IOException {
        createOutputFiles();
        return inToOutMap.get(i).getInputFile();
    }

    /**
     * lazy initiation
     *
     * @param i
     * @return
     * @throws IOException
     */
    public synchronized File getOutputFile(int i) throws IOException {
        createOutputFiles();
        return inToOutMap.get(i).getOutputFile();
    }

    public synchronized int size() throws EmptyDirectoryException {
        initInFiles();
        return inFiles.size();
    }

    @Override
    public String getFileExtension() {
        return filesManager.getFileExtension().substring(1);
    }


    @Data
    @AllArgsConstructor
    private class Pair {
        File inputFile;
        File outputFile;
    }

}

