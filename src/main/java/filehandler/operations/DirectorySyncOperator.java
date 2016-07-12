package filehandler.operations;

import commandline.CommandsEnum;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import utils.Timer;
import utils.files.DirectoryFilesManager;
import utils.files.FilesManager;

import java.io.*;
import java.util.Observable;

/**
 * Created by mzeus on 7/11/16.
 */
public class DirectorySyncOperator extends Observable implements Operation<Algorithm<Integer>> {
    Operator operator;
    private DirectoryFilesManager manager;

    public DirectorySyncOperator(Operator operator) {
        try {
            this.operator = operator;
            this.manager = new DirectoryFilesManager((FilesManager) operator.getStreamManager());
        } catch (IOException e) {
            //ignored
        }
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
                runSync(new FileInputStream(in), new FileOutputStream(out), algorithm);
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
