package filehandler.operations;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import utils.DisplayMessage;
import utils.Selectable;

import java.io.File;
import java.io.IOException;

/**
 * Created by mzeus on 29/05/16.
 */
public interface Operation extends Selectable {
    File act(File file, CipherAlgorithm algorithm, DisplayMessage displayMessage) throws IOException, KeyException;
}
