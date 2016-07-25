package utils.files;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import exceptions.CannotCreateFileException;
import exceptions.EmptyDirectoryException;
import exceptions.FileAlreadyExistsException;

import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mzeus on 7/6/16.
 */
public class DirectoryFilesManager extends AbstractFilesManager {

    private final ArrayList<File> inFiles = new ArrayList<>();
    private final ArrayList<File> outFiles = new ArrayList<>();
    private final ArrayList<Map.Entry<File, File>> inToOutMap = new ArrayList<>();
    private final AbstractFilesManager filesManager;
    private File opDir;

    @Inject
    public DirectoryFilesManager(@Named("decorator") AbstractFilesManager filesManager) throws IOException {
        super(filesManager.getInputFile());
        this.filesManager = filesManager;
    }

    private void initInFiles() {
        if (inFiles.isEmpty()) {
            File[] inFiles = filesManager.getInputFile().listFiles();
            if (inFiles != null) {
                for (File inFile : inFiles) {
                    if (inFile.isFile())
                        this.inFiles.add(inFile);
                }
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
                inToOutMap.add(new AbstractMap.SimpleEntry<>(inFile, outFile));
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
        if (!outFiles.isEmpty())
            return new FileOutputStream(outFiles.get(0));
        throw new EmptyDirectoryException();
    }

    /***
     * should'nt be used - gives back only the first file in the directory
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public synchronized InputStream getInputStream() throws FileNotFoundException, EmptyDirectoryException {
        if (!outFiles.isEmpty())
            return new FileInputStream(inFiles.get(0));
        throw new EmptyDirectoryException();
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
        return inToOutMap.get(i).getKey();
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
        return inToOutMap.get(i).getValue();
    }

    public int size() {
        initInFiles();
        return inFiles.size();
    }

    @Override
    public String getFileExtension() {
        return filesManager.getFileExtension().substring(1);
    }


}
