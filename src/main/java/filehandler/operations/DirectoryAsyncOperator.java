package filehandler.operations;

import commandline.CommandsEnum;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.Cleanup;
import utils.Timer;
import utils.files.DirectoryFilesManager;
import utils.files.FilesManager;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mzeus on 7/6/16.
 */
//async
public class DirectoryAsyncOperator extends Observable implements Operation<Algorithm<Integer>> {

    private Operator operator;
    private DirectoryFilesManager manager;
    private ExecutorService service = Executors.newFixedThreadPool(THREADS);
    private volatile int counter;
    private ReentrantLock lock = new ReentrantLock();
    private static final int THREADS = 5;

    public DirectoryAsyncOperator(Operator operator) {
        this.operator = operator;
        try {
            this.manager = new DirectoryFilesManager((FilesManager) operator.getStreamManager());
            this.counter = manager.size() - 1;
        } catch (IOException e) {
            //ignored
        }
    }


    @Override
    public void run(Algorithm<Integer> algorithm) {
        try {
            algorithm = operator.fillKeys(algorithm);
            Algorithm<Integer> finalAlgorithm = algorithm;
            setChanged();
            notifyObservers(CommandsEnum.START);
            Timer.getInstance().start();
            int size = manager.size();
            int filesPerThreads = size / THREADS + ((size % THREADS == 0) ? 0 : 1);
            System.out.println(size);
            if (filesPerThreads < 1)
                filesPerThreads = 1;
            int finalFilesPerThreads = filesPerThreads;
            System.out.println(finalFilesPerThreads);
            for (int i = 0; i < THREADS; i++) {
                service.execute(() -> {
                    try {
                        while (getCounter() >= 0) {
                            lock.lock();
                            System.out.println(Thread.currentThread().getName());
                            ArrayList<InputStream> inArray = new ArrayList<>();
                            ArrayList<OutputStream> outArray = new ArrayList<>();
                            for (int j = 0; j < finalFilesPerThreads && getCounter() >= 0; j++) {
                                File in = manager.getInputFile(counter);
                                File out = manager.getOutputFile(counter);
                                setChanged();
                                notifyObservers("file: " + in.getName());
                                inArray.add(new FileInputStream(in));
                                outArray.add(new FileOutputStream(out));
                                counterDown();
                            }
                            lock.unlock();
                            if (inArray.isEmpty()) {
                                break;
                            }
                            readAndWriteFromFiles(inArray, outArray, finalAlgorithm);
                        }
                    } catch (IOException e) {
                        setChanged();
                        notifyObservers(e);
                    }
                });
            }
            service.shutdown();
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException | KeyException | ClassNotFoundException | IOException e) {
            setChanged();
            notifyObservers(e);
        }
        Timer.getInstance().end();
        setChanged();
        notifyObservers(CommandsEnum.END);

    }

    private synchronized int getCounter() {
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
        int raw;
        byte enc;
        int index = 0;
//        byte[] bytes = Files.readAllBytes()
        if ((raw = in.read()) != -1) {
            enc = operate(algorithm, raw, index);
            index++;
            out.write(enc);
        }
    }

    private void readAndWriteFromFiles(ArrayList<InputStream> in, ArrayList<OutputStream> out, Algorithm algorithm) throws IOException {
        int raw;
        int index = 0;
        while (in.size() > 0) {
            for (int i = 0; i < in.size(); i++) {
                InputStream inputStream = in.get(i);
                if ((raw = inputStream.read()) != -1) {
                    OutputStream outputStream = out.get(i);
                    outputStream.write(operate(algorithm, raw, index));
                } else {
                    out.remove(in.indexOf(inputStream)).close();
                    inputStream.close();
                    in.remove(inputStream);
                }
            }
            index++;
        }
    }

    @Override
    public String toString() {
        return "async " + operator.toString();
    }
}
