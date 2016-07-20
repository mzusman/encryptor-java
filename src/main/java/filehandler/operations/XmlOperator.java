package filehandler.operations;

import exceptions.KeyException;
import filehandler.algorithm.Algorithm;
import utils.xml.XmlFilesManager;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

/**
 * Created by mzeus on 7/13/16.
 */
public class XmlOperator extends Observable implements Operation<Algorithm<Integer>> {

    private Operator operator;
    private Algorithm algorithm;

    public XmlOperator(Operator operator) {
        this.operator = operator;
        try {
            this.algorithm = XmlFilesManager.getInstance().readAlgorithmFromXml();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(Algorithm<Integer> algorithm) {
        operator.run(this.algorithm);
    }

    @Override
    public byte operate(Algorithm<Integer> algorithm, int raw, int index) {
        return operator.operate(this.algorithm, raw, index);
    }

    @Override
    public Algorithm<Integer> fillKeys(Algorithm<Integer> algorithm) throws IOException, ClassNotFoundException, KeyException {
        return operator.fillKeys(this.algorithm);
    }

    @Override
    public void runSync(InputStream in, OutputStream out, Algorithm algorithm) throws IOException {
        operator.runSync(in, out, this.algorithm);
    }
}
