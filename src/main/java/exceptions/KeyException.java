package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by mzeus on 31/05/16.
 */


public class KeyException extends Exception {


    public KeyException(String s) {
        super(s);
    }
}
