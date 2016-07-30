package domain.operations;

import domain.algorithm.Algorithm;
import exceptions.EmptyDirectoryException;
import exceptions.FileErrorException;
import utils.files.DirectoryFilesManager;
import utils.immutables.PairOf;
import utils.status.FileEnd;
import utils.status.FileStart;
import utils.xml.Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mzues on 7/30/2016.
 */
public class DirectoryAsyncTask extends Observable implements Runnable {

    private volatile int counter;
    private ReentrantLock lock = new ReentrantLock();
    private final int filesPerThreads;
    private final Operation<Algorithm<Byte>, Byte> operation;
    private final DirectoryFilesManager manager;
    private final Algorithm algorithm;

    DirectoryAsyncTask(int filesPerThread, DirectoryFilesManager manager, Operation<Algorithm<Byte>, Byte> operation, Algorithm algorithm) {
        this.algorithm = algorithm;
        this.filesPerThreads = filesPerThread;
        this.manager = manager;
        this.operation = operation;
        try {
            this.counter = manager.size();
        } catch (EmptyDirectoryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (counter > 0) {
                ArrayList<PairOf<File, File>> pairArray = new ArrayList<>();
                lock.lock();
                for (int j = 0; j < filesPerThreads && counter > 0; j++) {
                    File in = manager.getInputFile(counter - 1);
                    File out = manager.getOutputFile(counter - 1);
                    pairArray.add(new PairOf<>(in, out));
                    if (pairArray.isEmpty()) {
                        lock.unlock();
                        break;
                    }
                    counterDown();
                }
                lock.unlock();
                readAndWriteFromFiles(pairArray, algorithm);
            }
        } catch (IOException | InterruptedException e) {
            setChanged();
            notifyObservers(e);
        }
    }


    private void counterDown() {
        counter--;
    }

    private ArrayList<PairOf<InputStream, OutputStream>> filesToStreams(ArrayList<PairOf<File, File>> pairArray) {
        ArrayList<PairOf<InputStream, OutputStream>> streamsArray = new ArrayList<>();
        pairArray.forEach(p -> {
            try {
                streamsArray.add(new PairOf<>(new FileInputStream(p.getKey()), new FileOutputStream(p.getVal())));
                setChanged();
                notifyObservers(new FileStart(p.getKey()));
            } catch (FileNotFoundException e) {
                notifyObservers(new FileErrorException(e, p.getKey(), ""));
            }
        });
        return streamsArray;
    }

    private void readAndWriteFromFiles(ArrayList<PairOf<File, File>> pairArray, Algorithm<Byte> algorithm) throws InterruptedException, FileNotFoundException {
        ArrayList<PairOf<InputStream, OutputStream>> streamsArray = filesToStreams(pairArray);

        int raw;
        int index = 0;
        while (pairArray.size() > 0) {
            for (int i = 0; i < pairArray.size(); i++) {
                PairOf<InputStream, OutputStream> streamsPair = streamsArray.get(i);
                try {
                    if ((raw = streamsPair.getKey().read()) != -1) {
                        streamsPair.getVal().write(operation.operate(algorithm, (byte) raw, index));
                    } else {
                        File file = pairArray.get(streamsArray.indexOf(streamsPair)).getKey();
                        // removing streams and files that their treatment is done
                        streamsArray.remove(streamsPair);
                        pairArray.remove(i);
                        streamsPair.getKey().close();
                        streamsPair.getVal().close();
                        //
                        setChanged();
                        notifyObservers(new FileEnd(file));
                    }
                } catch (IOException e) {
                    setChanged();
                    notifyObservers(new FileErrorException(e, pairArray.get(i).getKey(), ""));
                }
            }
            index++;
        }
    }

}
