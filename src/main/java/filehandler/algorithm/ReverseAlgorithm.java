package filehandler.algorithm;


import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by mzeus on 01/06/16.
 */
@XmlRootElement
public class ReverseAlgorithm implements Algorithm<Integer> {

    private Algorithm<Integer> algorithm;

    public ReverseAlgorithm() {

    }

    @Override
    public int numberOfAlgorithms() {
        return 1;
    }

    @Override
    public int numberOfKeys() {
        return 0;
    }

    @XmlElement(type = Object.class)
    public Algorithm<Integer> getAlgorithm() {
        return algorithm;
    }

    /**
     * This method override the {@link ExtendedAlgorithm#decryptionOperation(int, int)}
     * implementation to make it run in reverse - decryption operation is now encryption
     * and vice versa.
     *
     * @param raw          raw byte to encode
     * @param algorithmKey key used for encode
     * @return the encoded bye
     */


    @Override
    public Integer decrypt(Integer raw, Integer key, int streamIndex) {
        return algorithm.encrypt(raw, key, streamIndex);
    }


    /**
     * This method is similar to {@link #decryptionOperation(int, int)}
     */
    @Override
    public Integer encrypt(Integer raw, Integer key, int streamIndex) {
        return algorithm.decrypt(raw, key, streamIndex);
    }

    @Override
    public void pushAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Integer getKey() {
        return algorithm.getKey();
    }

    @Override
    public Integer getKey(Algorithm algorithm, int index) {
        return this.algorithm.getKey(algorithm, index);
    }

    @Override
    public Integer getKey(int index) {
        return algorithm.getKey(index);
    }

    @Override
    public boolean generateEncryptKeys() {
        return algorithm.generateEncryptKeys();
    }

    @Override
    public void setDecryptionKey(Integer key, int index, Algorithm algorithm) {
        this.algorithm.setDecryptionKey(key, index, algorithm);
    }

    @Override
    public boolean checkIfKeyIsValid(Integer key) {
        return algorithm.checkIfKeyIsValid(key);
    }

    @Override
    public String toString() {
        return "run a " + getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReverseAlgorithm)) return false;

        ReverseAlgorithm that = (ReverseAlgorithm) o;

        return algorithm != null ? algorithm.equals(that.algorithm) : that.algorithm == null;

    }

    @Override
    public int hashCode() {
        return algorithm != null ? algorithm.hashCode() : 0;
    }
}
