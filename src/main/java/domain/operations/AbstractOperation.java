package domain.operations;

import com.google.inject.Inject;
import exceptions.KeyException;
import lombok.extern.log4j.Log4j2;
import utils.StreamManager;
import domain.algorithm.Algorithm;
import lombok.*;
import utils.Timer;
import utils.files.KeyFilesManager;

import java.io.*;
import java.util.Observable;

/**
 * Created by mzeus on 29/05/16.
 */
@Log4j2
public abstract class AbstractOperation extends Observable implements Operation<Algorithm<Byte>, Byte> {

    private
    @Getter
    @Setter
    StreamManager streamManager;
    @Getter
    private final KeyFilesManager keyFilesManager;

    @Inject
    AbstractOperation(StreamManager streamManager, KeyFilesManager keyFilesManager) {
        this.keyFilesManager = keyFilesManager;
        this.streamManager = streamManager;
    }


    @Override
    public void run(Algorithm<Byte> algorithm) {
        try {
            algorithm = fillKeys(algorithm);
            @Cleanup InputStream in = streamManager.getInputStream();
            @Cleanup OutputStream out = streamManager.getOutputStream();
            setChanged();
            notifyObservers(CommandsEnum.START_OPT);
            Timer.getInstance().start();
//            LogFileManager.getInstance().started(toString(), keyFilesManager.getInputFile());
            runSync(in, out, algorithm);
            Timer.getInstance().end();
//            LogFileManager.getInstance().ended(keyFilesManager.getInputFile());
            setChanged();
            notifyObservers(CommandsEnum.START_OPT);
        } catch (IOException | KeyException | ClassNotFoundException e) {
            setChanged();
            notifyObservers(e);
        }
    }

    @Override
    public Byte operate(Algorithm<Byte> algorithm, Byte raw, int index) {
        return 0;
    }

    @Override
    public Algorithm<Byte> fillKeys(Algorithm<Byte> algorithm) throws IOException, ClassNotFoundException, KeyException {
        return algorithm;
    }

    public void runSync(InputStream in, OutputStream out, Algorithm<Byte> algorithm) throws IOException {
        int raw;
        Byte enc;
        int index = 0;
        while ((raw = in.read()) != -1) {
            enc = operate(algorithm, (byte) raw, index);
            index++;
            out.write(enc);
        }

    }

}
