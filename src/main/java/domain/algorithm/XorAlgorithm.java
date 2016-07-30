package domain.algorithm;


import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Preforms a Xor operation on the key and the raw byte
 */
@XmlRootElement
@NoArgsConstructor
public class XorAlgorithm extends AbstractAlgorithm {

    @Override
    public Byte decrypt(Byte raw, Byte key, int streamIndex) {
        return (byte) ((raw ^ getKey()) & 0xff);
    }

    @Override
    public Byte encrypt(Byte raw, Byte key, int streamIndex) {
        return (byte) ((raw ^ getKey()) & 0xff);
    }

    @Override
    public boolean checkIfKeyIsValid(Byte key) {
        return (byte) key.intValue() != 0;
    }
}
