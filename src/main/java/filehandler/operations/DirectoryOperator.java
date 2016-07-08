package filehandler.operations;

import commandline.CommandsEnum;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.Cleanup;
import utils.Timer;
import utils.files.DirectoryFilesManager;
import utils.files.FilesManager;

import java.io.*;
import java.util.Observable;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mzeus on 7/6/16.
 */
//async
public class DirectoryOperator extends Observable implements Operation<Algorithm<Integer>> {

    private Operator operator;
    private DirectoryFilesManager manager;
    private ExecutorService service = Executors.newCachedThreadPool();

    private volatile int counter;

    public DirectoryOperator(Operator operator) {
        try {
            this.operator = operator;
            setChanged();
            notifyObservers(operator);
            manager = new DirectoryFilesManager((FilesManager) operator.getStreamManager());
            counter = manager.size() - 1;
        } catch (IOException e) {
            setChanged();
            notifyObservers(e);
        }
    }

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run(Algorithm<Integer> algorithm) {
        try {
            algorithm = operator.fillKeys(algorithm);
        } catch (IOException | ClassNotFoundException | KeyException e) {
            setChanged();
            notifyObservers(e);
        }
        Algorithm<Integer> finalAlgorithm = algorithm;
        setChanged();
        notifyObservers(CommandsEnum.START);
        Timer.getInstance().start();
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                try {
                    while (getCounter() >= 0) {
                        lock.lock();
                        File in = manager.getInputFile(counter);
                        File out = manager.getOutputFile(counter);
                        System.out.println(counter);
                        counterDown();
                        lock.unlock();
                        runSync(new FileInputStream(in), new FileOutputStream(out)
                                , finalAlgorithm);
                    }
                    latch.countDown();
                    latch.await();

                } catch (IOException e) {
                    setChanged();
                    notifyObservers(e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
        Timer.getInstance().end();
        setChanged();
        notifyObservers(CommandsEnum.END);

    }

    public synchronized int getCounter() {
        return counter;
    }

    private synchronized void counterDown() {
        counter--;
    }

    @Override
    public byte operate(Algorithm<Integer> algorithm, int raw, int index) {
        return operator.operate(algorithm, raw, index);
    }

    @Override
    public Algorithm<Integer> fillKeys(Algorithm<Integer> algorithm) throws IOException, ClassNotFoundException, KeyException {
        return operator.fillKeys(fillKeys(algorithm));
    }

    @Override
    public void runSync(InputStream in, OutputStream out, Algorithm algorithm) throws IOException {
        operator.runSync(in, out, algorithm);
    }

    @Override
    public String toString() {
        return "async " + operator.toString();
    }
}
