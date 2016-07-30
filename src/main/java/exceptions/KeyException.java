package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by mzeus on 31/05/16.
 */


public class KeyException extends Exception {


    /**
     * Instantiates a new Key exception.
     *
     * @param s the s
     */
    public KeyException(String s) {
        super(s);
    }
}
