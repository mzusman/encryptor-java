package filehandler.algorithm;

import exceptions.KeyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by mzeus on 6/25/16.
 */
@AllArgsConstructor
@Data
public class AlgorithmKey<T> implements Serializable {
    private Algorithm<T> singleAlgorithm;
    private T key;

}
