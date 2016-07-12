package filehandler.algorithm;


import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by mzeus on 7/2/16.
 */
@XmlRootElement
public class DoubleAlgorithm implements Algorithm<Integer> {

    private ArrayList<Algorithm<Integer>> algorithms;

    public DoubleAlgorithm() {
        algorithms = new ArrayList<>();
    }

    @XmlElementWrapper
    @XmlAnyElement(lax = true)
    public ArrayList<Algorithm<Integer>> getAlgorithms() {
        return algorithms;
    }

    @Override
    public int numberOfAlgorithms() {
        return 2;
    }

    @Override
    public int numberOfKeys() {
        return 2;
    }

    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        Integer dec = raw;
        for (int i = algorithms.size() - 1; i >= 0; i--) {
            Algorithm<Integer> algorithm = algorithms.get(i);
            dec = algorithm.decrypt(dec, key, streamIndex);
        }
        return dec;
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        Integer enc = raw;
        for (int i = 0; i < algorithms.size(); i++) {
            Algorithm<Integer> algorithm = algorithms.get(i);
            enc = algorithm.encrypt(enc, key, streamIndex);
        }
        return enc;
    }

    @Override
    public void pushAlgorithm(Algorithm algorithm) {
        algorithms.add(algorithm);
    }

    @Override
    public Integer getKey() {
        return algorithms.get(0).getKey();
    }

    @Override
    public Integer getKey(Algorithm algorithm, int index) {
        Algorithm<Integer> al = algorithms.stream().findAny().filter((a) -> a.equals(algorithm)).get();
        if (al != null)
            return al.getKey();
        return getKey();
    }

    @Override
    public Integer getKey(int index) {
        return algorithms.get(index).getKey();
    }

    @Override
    public boolean generateEncryptKeys() {
        algorithms.stream().forEach(Algorithm::generateEncryptKeys);
        return true;
    }

    @Override
    public void setDecryptionKey(Integer key, int index, Algorithm algorithm) {
        algorithms.stream().findAny().filter((a) -> a.equals(algorithm)).get().setDecryptionKey(key, 0, null);
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return algorithms.stream().allMatch((a) -> checkIfKeyIsValid(key));
    }

    @Override
    public String toString() {
        return "run a " + getClass().getSimpleName();
    }
}
