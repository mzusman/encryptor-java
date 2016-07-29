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
    private ExecutorService service = Executors.newFixedThreadPool(THREADS);
    private volatile int counter;
    private ReentrantLock lock = new ReentrantLock();
    private static final int THREADS = 5;
    public static final String BASE = "DirectoryAsync.base";

    @Inject
    public DirectoryAsyncOperator(@Named(BASE) AbstractOperation operator, DirectoryFilesManager directoryFilesManager) {
        this.operator = operator;
        this.manager = directoryFilesManager;
        try {
            this.counter = manager.size() - 1;
        } catch (EmptyDirectoryException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run(Algorithm<Byte> algorithm) {
        try {
            algorithm = operator.fillKeys(algorithm);
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

    private void counterDown() {
        counter--;
    }

    private void runOnThreads(Algorithm<Byte> algorithm) throws EmptyDirectoryException, InterruptedException {
        int size = manager.size();
        val filesPerThreads = size / THREADS + ((size % THREADS == 0) ? 0 : 1);
        for (int i = 0; i < THREADS; i++) {
            service.execute(() -> {
                try {
                    while (counter >= 0) {
                        lock.lock();
                        ArrayList<File> inArray = new ArrayList<>();
                        ArrayList<File> outArray = new ArrayList<>();
                        for (int j = 0; j < filesPerThreads && counter >= 0; j++) {
                            File in = manager.getInputFile(counter);
                            File out = manager.getOutputFile(counter);
                            inArray.add(in);
                            outArray.add(out);
                            counterDown();
                        }
                        lock.unlock();
                        if (inArray.isEmpty()) {
                            break;
                        }
                        readAndWriteFromFiles(inArray, outArray, algorithm);
                    }
                } catch (IOException | InterruptedException e) {
                    setChanged();
                    notifyObservers(e);
                }
            });
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

    private void readAndWriteFromFiles(ArrayList<File> in, ArrayList<File> out, Algorithm<Byte> algorithm) throws InterruptedException, FileNotFoundException {

        ArrayList<InputStream> inputStreams = new ArrayList<>();
        ArrayList<OutputStream> outputStreams = new ArrayList<>();
        in.forEach(file -> {
            try {
                inputStreams.add(new FileInputStream(file));
                setChanged();
                notifyObservers(new FileStart(file));
            } catch (FileNotFoundException e) {
                notifyObservers(new FileErrorException(e, file, ""));
            }
        });
        out.forEach(file -> {
            try {
                outputStreams.add(new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                setChanged();
                notifyObservers(new FileErrorException(e, file, ""));
            }
        });


        int raw;
        int index = 0;
        while (in.size() > 0) {
            for (int i = 0; i < in.size(); i++) {
                InputStream inputStream = inputStreams.get(i);
                try {
                    if ((raw = inputStream.read()) != -1) {
                        OutputStream outputStream = outputStreams.get(i);
                        outputStream.write(operate(algorithm, (byte) raw, index));
                    } else {

                        File file = in.get(inputStreams.indexOf(inputStream));

                        // removing streams and files that their treatment is done
                        outputStreams.remove(inputStreams.indexOf(inputStream)).close();
                        in.remove(inputStreams.indexOf(inputStream));
                        inputStream.close();
                        inputStreams.remove(inputStream);
                        //

                        setChanged();
                        notifyObservers(new FileEnd(file));
                    }
                } catch (IOException e) {
                    setChanged();
                    notifyObservers(new FileErrorException(e, in.get(inputStreams.indexOf(inputStream)), ""));

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