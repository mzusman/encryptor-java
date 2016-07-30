package domain.operations;

import com.google.inject.Inject;
import exceptions.KeyException;
import lombok.extern.log4j.Log4j2;
import utils.files.StreamManager;
import domain.algorithm.Algorithm;
import lombok.*;
import utils.Timer;
import utils.files.KeyFilesManager;
import utils.immutables.PairOf;

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

    private PairOf<InputStream, OutputStream> warmUp(Algorithm algorithm) throws KeyException, IOException, ClassNotFoundException {
        algorithm = fillKeys(algorithm);
        @Cleanup InputStream in = streamManager.getInputStream();
        @Cleanup OutputStream out = streamManager.getOutputStream();
        PairOf<InputStream, OutputStream> streamPair = new PairOf<>(in, out);
        setChanged();
        notifyObservers(CommandsEnum.START_OPT);
        Timer.getInstance().start();
        return streamPair;
    }

    @Override
    public void run(Algorithm<Byte> algorithm) {
        try {
            PairOf<InputStream, OutputStream> streamPair = warmUp(algorithm);
            runSync(streamPair, algorithm);
            beforeFinish();
        } catch (IOException | KeyException | ClassNotFoundException e) {
            setChanged();
            notifyObservers(e);
        }
    }

    private void beforeFinish() {
        Timer.getInstance().end();
        setChanged();
        notifyObservers(CommandsEnum.START_OPT);
    }

    @Override
    public Byte operate(Algorithm<Byte> algorithm, Byte raw, int index) {
        return 0;
    }

    @Override
    public Algorithm<Byte> fillKeys(Algorithm<Byte> algorithm) throws IOException, ClassNotFoundException, KeyException {
        return algorithm;
    }

    public void runSync(PairOf<InputStream, OutputStream> streamPair, Algorithm<Byte> algorithm) throws IOException {
        int raw;
        Byte enc;
        int index = 0;
        while ((raw = streamPair.getKey().read()) != -1) {
            enc = operate(algorithm, (byte) raw, index);
            index++;
            streamPair.getVal().write(enc);
        }

    }

}
