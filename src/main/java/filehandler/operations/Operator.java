package filehandler.operations;

import commandline.CommandsEnum;
import exceptions.KeyException;
import utils.Timer;
import filehandler.algorithm.Algorithm;
import lombok.*;
import utils.files.FilesManager;
import utils.files.KeyFilesManager;

import java.io.*;
import java.util.Observable;

/**
 * Created by mzeus on 29/05/16.
 */
class Operator extends Observable implements Operation<Algorithm<Integer>> {

    private
    @Setter
    FilesManager streamManager;
    @Getter
    private KeyFilesManager keyFilesManager;

    Operator(File inputFile) {
        keyFilesManager = new KeyFilesManager(inputFile);
    }


    @Override
    public void run(Algorithm<Integer> algorithm) {
        try {
            algorithm = fillKeys(algorithm);
            @Cleanup InputStream in = streamManager.getInputStream();
            @Cleanup OutputStream out = streamManager.getOutputStream();
            int raw;
            byte enc;
            int index = 0;
            //start
            setChanged();
            notifyObservers(CommandsEnum.START);
            Timer.getInstance().start();
            while ((raw = in.read()) != -1) {
                enc = operate(algorithm, raw, index);
                index++;
                out.write(enc);
            }
            //end
            setChanged();
            Timer.getInstance().end();
            notifyObservers(CommandsEnum.END);
        } catch (IOException | KeyException e) {
            setChanged();
            notifyObservers(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte operate(Algorithm<Integer> algorithm, int raw, int index) {
        return 0;
    }

    @Override
    public Algorithm<Integer> fillKeys(Algorithm<Integer> algorithm) throws IOException, ClassNotFoundException, KeyException {
        return algorithm;
    }

}
