package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by mzeus on 31/05/16.
 */


@AllArgsConstructor
public class KeyException extends Exception {
    private
    @Getter
    String message;


}
