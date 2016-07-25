package exceptions;

import java.io.IOException;

/**
 * Created by mzeus on 25/07/16.
 */
public class CannotCreateFileException extends IOException{
    public CannotCreateFileException(String s){
        super(s);
    }
}

