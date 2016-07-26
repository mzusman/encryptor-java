package filehandler.operations;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commandline.CommandsEnum;
import exceptions.EmptyDirectoryException;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import lombok.extern.log4j.Log4j2;
import utils.LogFileManager;
import utils.Timer;
import utils.xml.XmlReportManager;
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
            Algorithm<Byte> finalAlgorithm = algorithm;
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
    public Byte operate(Algorithm<Byte> algorithm, Byte raw, int index) {
        return operator.operate(algorithm, raw, index);
    }

    @Override
    public Algorithm<Byte> fillKeys(Algorithm<Byte> algorithm) throws IOException, ClassNotFoundException, KeyException {
        return operator.fillKeys(algorithm);
    }


    private void readAndWriteFromFiles(ArrayList<File> in, ArrayList<File> out, Algorithm<Byte> algorithm) throws InterruptedException {

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
                        outputStream.write(operate(algorithm, (byte) raw, index));
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
