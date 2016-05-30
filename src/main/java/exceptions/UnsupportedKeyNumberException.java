package exceptions;

/**
 * Created by mzeus on 31/05/16.
 */
public class UnsupportedKeyNumberException extends KeyException {
    public UnsupportedKeyNumberException() {
        super("Unsupported Key Number");

    }

    public UnsupportedKeyNumberException(int value) {
        super(String.format("Unsupported Key Number - %d", value));

    }
}
