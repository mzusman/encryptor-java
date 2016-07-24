package filehandler.operations;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commandline.CommandsEnum;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.extern.log4j.Log4j2;
import utils.LogFileManager;
import utils.Timer;
import utils.xml.XmlReportManager;
import utils.files.DirectoryFilesManager;
import utils.files.FilesManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by mzeus on 7/6/16.
 */
//async
@Log4j2
public class DirectoryAsyncOperator extends Observable implements Operation<Algorithm<Integer>> {

    private Operator operator;
    private DirectoryFilesManager manager;
    private ExecutorService service = Executors.newFixedThreadPool(THREADS);
    private volatile int counter;
    private ReentrantLock lock = new ReentrantLock();
    private static final int THREADS = 5;
    public static final String BASE = "DirectoryAsync.base";

    @Inject
    public DirectoryAsyncOperator(@Named(BASE) Operator operator, DirectoryFilesManager directoryFilesManager) {
        this.operator = operator;
        this.manager = directoryFilesManager;
        this.counter = manager.size() - 1;
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
            if (filesPerThreads < 1)
                filesPerThreads = 1;
            int finalFilesPerThreads = filesPerThreads;
            for (int i = 0; i < THREADS; i++) {
                service.execute(() -> {
                    try {
                        while (getCounter() >= 0) {
                            lock.lock();
                            ArrayList<File> inArray = new ArrayList<>();
                            ArrayList<File> outArray = new ArrayList<>();
                            for (int j = 0; j < finalFilesPerThreads && getCounter() >= 0; j++) {
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
                            readAndWriteFromFiles(inArray, outArray, finalAlgorithm);
                        }
                    } catch (IOException | InterruptedException e) {
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
        XmlReportManager.getInstance().writeReport();
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

    private void readAndWriteFromFiles(ArrayList<File> in, ArrayList<File> out, Algorithm algorithm) throws InterruptedException {
        ArrayList<InputStream> inputStreams = new ArrayList<>();
        ArrayList<OutputStream> outputStreams = new ArrayList<>();
        try {
            for (int i = 0; i < in.size(); i++) {
                inputStreams.add(new FileInputStream(in.get(i)));
                outputStreams.add(new FileOutputStream(out.get(i)));
                LogFileManager.getInstance().started(toString(), in.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int raw;
        int index = 0;
        while (in.size() > 0) {
            for (int i = 0; i < in.size(); i++) {
                InputStream inputStream = inputStreams.get(i);
                try {
                    if ((raw = inputStream.read()) != -1) {
                        OutputStream outputStream = outputStreams.get(i);
                        outputStream.write(operate(algorithm, raw, index));
                    } else {
                        File file = in.get(inputStreams.indexOf(inputStream));
                        XmlReportManager.getInstance().writeFileDone(file);
                        LogFileManager.getInstance().ended(file);
                        outputStreams.remove(inputStreams.indexOf(inputStream)).close();
                        in.remove(inputStreams.indexOf(inputStream));
                        inputStream.close();
                        inputStreams.remove(inputStream);
                    }
                } catch (IOException e) {
                    LogFileManager.getInstance().error(in.get(inputStreams.indexOf(inputStream)), e);
                    XmlReportManager.getInstance().writeFileError(in.get(inputStreams.indexOf(inputStream)), e);
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
