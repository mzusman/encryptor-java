package domain.operations;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import exceptions.EmptyDirectoryException;
import exceptions.FileErrorException;
import exceptions.KeyException;
import domain.algorithm.Algorithm;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import utils.Timer;
import utils.status.FileEnd;
import utils.status.FileStart;
import utils.files.DirectoryFilesManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mzeus on 7/6/16.
 */
//async
@Log4j2
public class DirectoryAsyncOperator extends Observable implements Operation<Algorithm<Byte>, Byte> {

    private AbstractOperation operator;
    private DirectoryFilesManager manager;
    private ExecutorService service = Executors.newFixedThreadPool(NTHREADS);
    private static final int NTHREADS = 5;
    public static final String BASE = "DirectoryAsync.base";
    private DirectoryAsyncTask asyncTask;

    @Inject
    public DirectoryAsyncOperator(@Named(BASE) AbstractOperation operator, DirectoryFilesManager directoryFilesManager) {
        this.operator = operator;
        this.manager = directoryFilesManager;
    }


    @Override
    public void run(Algorithm<Byte> algorithm) {
        try {
            algorithm = fillKeys(algorithm);
            setChanged();
            notifyObservers(CommandsEnum.START_OPT);
            Timer.getInstance().start();
            runOnThreads(algorithm);
        } catch (InterruptedException | KeyException | ClassNotFoundException | IOException e) {
            setChanged();
            notifyObservers(e);
        }
        Timer.getInstance().end();
        setChanged();
        notifyObservers(CommandsEnum.END_OPT);

    }

    private void runOnThreads(Algorithm<Byte> algorithm) throws IOException, InterruptedException, KeyException, ClassNotFoundException {
        int size = manager.size();
        //calculate how many files each thread will treat
        val filesPerThreads = size / NTHREADS + ((size % NTHREADS == 0) ? 0 : 1);
        asyncTask = new DirectoryAsyncTask(filesPerThreads, manager, this, algorithm);
        setChanged();
        notifyObservers(asyncTask);
        for (int i = 0; i < NTHREADS; i++) {
            service.execute(asyncTask);
        }
        service.shutdown();
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
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
        return "async " + operator.toString();
    }
}
