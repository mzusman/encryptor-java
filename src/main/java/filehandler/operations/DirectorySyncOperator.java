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
import utils.xml.XmlReportManager;

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
            algorithm = operator.fillKeys(algorithm);
            setChanged();
            notifyObservers(CommandsEnum.START);
            Timer.getInstance().start();
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
                notifyObservers("file: " + in.getName());
                try {
                    LogFileManager.getInstance().started(this.toString(), in);
                    operator.runSync(new FileInputStream(in), new FileOutputStream(out), algorithm);
                    XmlReportManager.getInstance().writeFileDone(in);
                    LogFileManager.getInstance().ended(in);
                } catch (IOException e) {
                    LogFileManager.getInstance().error(in, e);
                    XmlReportManager.getInstance().writeFileError(in, e);
                }
            }
            Timer.getInstance().end();
            setChanged();
            notifyObservers(CommandsEnum.END);
            XmlReportManager.getInstance().writeReport();
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
