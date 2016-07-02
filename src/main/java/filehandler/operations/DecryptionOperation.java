package filehandler.operations;

import filehandler.algorithm.ListOfAlgorithms;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class DecryptionOperation extends AbstractOperation {


    public DecryptionOperation(StreamManager streamManager) {
        super(streamManager);
    }

    @Override
    public void findKey(ListOfAlgorithms algorithms) throws IOException {
//        algorithms.setDecryptionKey();
    }

    @Override
    public byte operate(ListOfAlgorithms algorithm, int raw, int index) {
        return algorithm.decryptionOperation(raw, index);
    }


}
