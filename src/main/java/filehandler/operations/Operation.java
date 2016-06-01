package filehandler.operations;

import commandline.CliHandler;
import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import filehandler.algorithm.cipheralgorithm.CipherAlgorithm;
import utils.DisplayMessage;
import utils.Selectable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mzeus on 29/05/16.
 */
public interface Operation extends Selectable {
    File act(File file, Algorithm algorithm) throws IOException, KeyException;

    int getKey(Algorithm algorithm) throws IOException;

}