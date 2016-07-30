package domain.algorithm;


import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mzeus on 30/05/16.
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
