package domain.operations;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import exceptions.FileErrorException;
import exceptions.KeyException;
import domain.algorithm.Algorithm;
import lombok.extern.log4j.Log4j2;
import utils.Timer;
import utils.files.DirectoryFilesManager;
import utils.status.FileEnd;
import utils.status.FileStart;

import java.io.*;
import java.util.Observable;

/**
 * Created by mzeus on 7/11/16.
 */
@Log4j2
public class DirectorySyncOperator extends Observable implements Operation<Algorithm<Byte>, Byte> {
    private AbstractOperation operator;
    private DirectoryFilesManager manager;
    public static final String BASE = "DirectorySync.base";

    @Inject
    public DirectorySyncOperator(@Named(BASE) AbstractOperation operator, DirectoryFilesManager manager) {
        this.operator = operator;
        this.manager = manager;
    }

    @Override
    public void run(Algorithm<Byte> algorithm) {
        try {
            // fill keys
            algorithm = operator.fillKeys(algorithm);
            setChanged();
            notifyObservers(CommandsEnum.START_OPT);
            Timer.getInstance().start();
            //for each file run sync
            for (int i = 0; i < manager.size(); i++) {
                File in;
                File out;
                try {
                    in = manager.getInputFile(i);
                    out = manager.getOutputFile(i);
                } catch (IOException e) {
                    setChanged();
                    notifyObservers(e);
                    return;
                }
                setChanged();
                notifyObservers(new FileStart(in));
                try {
                    operator.runSync(new FileInputStream(in), new FileOutputStream(out), algorithm);
                    setChanged();
                    notifyObservers(new FileEnd(in));
                } catch (IOException e) {
                    setChanged();
                    notifyObservers(new FileErrorException(e, in, ""));
                }
            }
            Timer.getInstance().end();
            setChanged();
            notifyObservers(CommandsEnum.END_OPT);
        } catch (IOException | ClassNotFoundException | KeyException e) {
            setChanged();
            notifyObservers(e);
        }
    }

    @Override
    public Byte operate(Algorithm<Byte> algorithm, Byte raw, int index) {
        return operator.operate(algorithm, raw, index);
    }

    @Override
    public Algorithm<Byte> fillKeys(Algorithm<Byte> algorithm) throws IOException, ClassNotFoundException, KeyException {
        return operator.fillKeys(algorithm);
    }


    @Override
    public String toString() {
        return "sync " + operator.toString();
    }
}
