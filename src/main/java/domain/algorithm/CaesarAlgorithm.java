package domain.algorithm;


import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@NoArgsConstructor
public class CaesarAlgorithm extends AbstractAlgorithm {


    @Override
    public Byte decrypt(Byte raw, Byte key, int streamIndex) {
        return (byte) (raw - getKey());
    }

    @Override
    public Byte encrypt(Byte raw, Byte key, int streamIndex) {
        return (byte) (raw + getKey());
    }

    @Override
    public boolean checkIfKeyIsValid(Byte key) {
        return (byte) key.intValue() != 0;
    }

}
