package domain.operations;

import domain.algorithm.Algorithm;
import exceptions.EmptyDirectoryException;
import exceptions.FileErrorException;
import utils.files.DirectoryFilesManager;
import utils.immutables.PairOf;
import utils.status.FileEnd;
import utils.status.FileStart;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Directory async task -
 * Responsible on running the executors
 * used on the @{@link DirectoryAsyncOperator}
 */
class DirectoryAsyncTask extends Observable implements Runnable {

    private volatile int counter;
    private ReentrantLock lock = new ReentrantLock();
    private final int filesPerThreads;
    private final Operation<Algorithm<Byte>, Byte> operation;
    private final DirectoryFilesManager manager;
    private final Algorithm algorithm;

    /**
     * Instantiates a new Directory async task.
     *
     * @param filesPerThread the files per thread
     * @param manager        the manager
     * @param operation      the operation
     * @param algorithm      the algorithm
     */
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
        /**
         * runs in a while up until there are no files left in the manager
         * counter var is the size of the directory.
         * and we decrease it each iteration
         */
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
        /**
         * happens to be synchronized in the run method
         */
        counter--;
    }

    private ArrayList<PairOf<InputStream, OutputStream>> filesToStreams(ArrayList<PairOf<File, File>> pairArray) {
        /**
         * converts array of files to array if streams.
         */
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
        /**
         * reads from inputstream, 1 byte at a time , from each file
         * in circulation and writes to the corresponding outputstream
         */
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
