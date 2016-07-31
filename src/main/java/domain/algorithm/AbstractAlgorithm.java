package domain.algorithm;

import domain.algorithm.Algorithm;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Random;

/**
 * The type Abstract algorithm - AbstractAlgorithm that holds 1 key.
 */
@EqualsAndHashCode
@XmlRootElement
@NoArgsConstructor
public abstract class AbstractAlgorithm implements Algorithm<Byte>, Serializable {
    private int key = 0;

    @Override
    public int numberOfKeys() {
        return 1;
    }

    @Override
    public int numberOfAlgorithms() {
        return 0;
    }


    @Override
    public void pushAlgorithm(Algorithm algorithm) {
        //does nothing
    }

    @Override
    public Byte getKey() {
        return (byte) key;
    }

    @Override
    public Byte getKey(Algorithm algorithm, int index) {
        return (byte) key;
    }

    @Override
    public Byte getKey(int index) {
        return (byte) key;
    }

    @Override
    public boolean generateEncryptKeys() {
        Random random = new Random();
        key = random.nextInt(255) + 1;
        while (!checkIfKeyIsValid((byte) key))
            key = random.nextInt(255) + 1;
        return true;
    }

    @Override
    public void setDecryptionKey(Byte key, int index, Algorithm algorithm) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "run a " + getClass().getSimpleName();
    }
}
