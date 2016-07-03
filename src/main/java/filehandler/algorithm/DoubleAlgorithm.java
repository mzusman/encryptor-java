package filehandler.algorithm;

import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by mzeus on 7/2/16.
 */
public class DoubleAlgorithm implements Algorithm<Integer> {
    private ArrayList<AlgorithmKey<Integer>> algorithms;

    public DoubleAlgorithm() {
        algorithms = new ArrayList<>();
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
        for (AlgorithmKey algorithmKey :
                algorithms) {
            Algorithm<Integer> algorithm = algorithmKey.getSingleAlgorithm();
            dec = algorithm.decrypt(dec, key, streamIndex);
        }
        return dec;
    }

    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        Integer enc = raw;
        for (AlgorithmKey algorithmKey :
                algorithms) {
            Algorithm<Integer> algorithm = algorithmKey.getSingleAlgorithm();
            enc = algorithm.encrypt(raw, key, streamIndex);
        }
        return enc;
    }

    @Override
    public void pushAlgorithm(Algorithm algorithm) {
        algorithms.add(new AlgorithmKey<Integer>(algorithm, -1));
    }

    @Override
    public Integer getKey(Algorithm algorithm, int index) {
        AlgorithmKey<Integer> algorithmKey = algorithms.stream().findAny().filter((a) -> a.getSingleAlgorithm().equals(algorithm)).get();
        if (algorithmKey != null)
            return algorithmKey.getKey();
        return 0;
    }

    @Override
    public boolean generateEncryptKeys() {
        return algorithms.stream().allMatch((a) -> generateEncryptKeys());
    }

    @Override
    public void setDecryptionKey(Integer key, int index, Algorithm algorithm) {
        algorithms.stream().findAny().filter((a) -> a.getSingleAlgorithm().equals(algorithm)).get().setKey(key);
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return algorithms.stream().allMatch((a) -> checkIfKeyIsValid(key));
    }
}
