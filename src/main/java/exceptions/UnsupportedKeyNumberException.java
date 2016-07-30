package exceptions;

/**
 * Created by mzeus on 31/05/16.
 */
public class UnsupportedKeyNumberException extends KeyException {
    /**
     * Instantiates a new Unsupported key number exception.
     */
    public UnsupportedKeyNumberException() {
        super("Unsupported Key Number");

    }

    /**
     * Instantiates a new Unsupported key number exception.
     *
     * @param value the value
     */
    public UnsupportedKeyNumberException(int value) {
        super(String.format("Unsupported Key Number - %d", value));

    }
}
