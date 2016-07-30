package commandline;

/**
 * interface for parser and processor of the args[]
 * from commandline
 *
 * @param <T> the type parameter
 */
public interface CommandlineProcessor<T> {
    /**
     * Process args and build a class accordingly.
     *
     * @param args the args
     * @return class of T that will be initialized
     */
    Class<? extends T> processArgs(String[] args);

    /**
     * Scan for pattern boolean.
     * scan for pattern wanted from the user
     *
     * @param args - args from commandline
     * @return true - if args match the pattern
     * else - false.
     */
    boolean scanForPattern(String args[]);

    /**
     * print options to the user.
     */
    void showOptions();

}
