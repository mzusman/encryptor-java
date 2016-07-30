package exceptions;

import java.io.IOException;

/**
 * Created by mzeus on 25/07/16.
 */
public class CannotCreateFileException extends IOException{
    /**
     * Instantiates a new Cannot create file exception.
     *
     * @param s the s
     */
    public CannotCreateFileException(String s){
        super(s);
    }
}

