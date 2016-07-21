package filehandler.operations;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commandline.CommandsEnum;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.LogFileManager;
import utils.Timer;
import utils.files.DirectoryFilesManager;
import utils.files.FilesManager;

import java.io.*;
import java.util.Observable;

/**
 * Created by mzeus on 7/11/16.
 */
@Log4j2
public class DirectorySyncOperator extends Observable implements Operation<Algorithm<Integer>> {
    private Operator operator;
    private DirectoryFilesManager manager;
    public static final String BASE = "DirectorySync.base";
    Logger logger = LogManager.getLogger(DirectorySyncOperator.class);

    @Inject
    public DirectorySyncOperator(@Named(BASE) Operator operator, DirectoryFilesManager manager) {

        this.operator = operator;
        this.manager = manager;
        for (int i = 0; i < 1000; i++) {
            logger.debug("hello");
        }
//            this.manager = new DirectoryFilesManager((FilesManager) operator.getStreamManager());
    }

    @Override
    public void run(Algorithm<Integer> algorithm) {
        try {
            algorithm = operator.fillKeys(algorithm);
            setChanged();
            notifyObservers(CommandsEnum.START);
            Timer.getInstance().start();
            for (int i = 0; i < manager.size() - 1; i++) {
                File in = manager.getInputFile(i);
                File out = manager.getOutputFile(i);
                setChanged();
                notifyObservers("file: " + in.getName());
                LogFileManager.getInstance().started(toString(), in);
                runSync(new FileInputStream(in), new FileOutputStream(out), algorithm);
                LogFileManager.getInstance().ended(in);
            }
            Timer.getInstance().end();
            setChanged();
            notifyObservers(CommandsEnum.END);
        } catch (IOException | ClassNotFoundException | KeyException e) {
            setChanged();
            notifyObservers(e);
        }

    }

    @Override
    public byte operate(Algorithm<Integer> algorithm, int raw, int index) {
        return operator.operate(algorithm, raw, index);
    }

    @Override
    public Algorithm<Integer> fillKeys(Algorithm<Integer> algorithm) throws IOException, ClassNotFoundException, KeyException {
        return operator.fillKeys(algorithm);
    }

    @Override
    public void runSync(InputStream in, OutputStream out, Algorithm algorithm) throws IOException {
        operator.runSync(in, out, algorithm);
    }

    @Override
    public String toString() {
        return "sync " + operator.toString();
    }
}
