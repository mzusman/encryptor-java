package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import utils.files.DirectoryFilesManager;
import utils.files.FilesManager;

import java.io.IOException;

/**
 * Created by mzeus on 7/6/16.
 */
//async
public class DirectoryOperator implements Operation<Algorithm<Integer>> {

    private Operator operator;
    private DirectoryFilesManager manager;

    public DirectoryOperator(Operator operator) {
        this.operator = operator;
        manager = new DirectoryFilesManager((FilesManager) operator.getStreamManager());
    }

    @Override
    public void run(Algorithm<Integer> algorithm) {
        operator.run(algorithm);
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
    public void runSync(Algorithm algorithm) throws IOException {
        operator.runSync(algorithm);
    }
}
