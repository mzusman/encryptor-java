package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.SingleAlgorithm;
import filehandler.algorithm.ListOfAlgorithms;

import java.io.*;
import java.util.Random;

/**
 * Created by Mor on 5/19/2016.
 */
public class EncryptionOperation extends AbstractOperation {


    @Override
    public byte operate(ListOfAlgorithms algorithm, int raw, int index) {
        return algorithm.encryptionOperation(raw, index);
    }

    @Override
    public void findKey(ListOfAlgorithms algorithms) {
//
        algorithms.createEncryptionKeys();
    }


}
