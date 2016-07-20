package filehandler.operations;

import com.google.inject.Inject;
import commandline.CommandsEnum;
import exceptions.KeyException;
import utils.StreamManager;
import utils.Timer;
import filehandler.algorithm.Algorithm;
import lombok.*;
import utils.files.FilesManager;
import utils.files.FilesManagerFactory;
import utils.files.KeyFilesManager;

import java.io.*;
import java.security.Key;
import java.util.Observable;

/**
 * Created by mzeus on 29/05/16.
 */
//sync
public class Operator extends Observable implements Operation<Algorithm<Integer>> {

    private
    @Getter
    @Setter
    StreamManager streamManager;
    @Getter
    private KeyFilesManager keyFilesManager;

    @Inject
    Operator(StreamManager streamManager, KeyFilesManager keyFilesManager) {
        this.keyFilesManager = keyFilesManager;
        this.streamManager = streamManager;
    }


    @Override
    public void run(Algorithm<Integer> algorithm) {
        try {
            algorithm = fillKeys(algorithm);
            @Cleanup InputStream in = streamManager.getInputStream();
            @Cleanup OutputStream out = streamManager.getOutputStream();
            setChanged();
            notifyObservers(CommandsEnum.START);
            Timer.getInstance().start();
            runSync(in, out, algorithm);
            Timer.getInstance().end();
            setChanged();
            notifyObservers(CommandsEnum.END);
        } catch (IOException | KeyException | ClassNotFoundException e) {
            setChanged();
            notifyObservers(e);
        }
    }

    @Override
    public byte operate(Algorithm<Integer> algorithm, int raw, int index) {
        return 0;
    }

    @Override
    public Algorithm<Integer> fillKeys(Algorithm<Integer> algorithm) throws IOException, ClassNotFoundException, KeyException {
        return algorithm;
    }

    @Override
    public void runSync(InputStream in, OutputStream out, Algorithm algorithm) throws IOException {
        int raw;
        byte enc;
        int index = 0;

        while ((raw = in.read()) != -1) {
            enc = operate(algorithm, raw, index);
            index++;
            out.write(enc);
        }

    }

}
