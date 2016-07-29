package commandline;

/**
 * Created by mzeus on 28/07/16.
 */
public interface CommandlineProcessor<T> {
    Class<? extends T> processArgs(String[] args);

    boolean scanForPattern(String args[]);

    void showOptions();

}
