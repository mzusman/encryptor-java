package commandline;


import com.google.inject.Inject;
import domain.algorithm.Algorithm;
import domain.operations.*;
import lombok.Getter;
import utils.Timer;

import java.util.*;

/**
 * Class used for handling prints to console
 */
public class CommandlineHandler<T> implements Observer, UserInterface {


    @Getter
    private Class<? extends T> selectOperation;
    @Getter
    private CommandlineProcessor<T> processor;


    /**
     * Instantiates a new Commandline handler.
     *
     * @param processor the processor
     */
    @Inject
    public CommandlineHandler(CommandlineProcessor processor) {
        this.processor = processor;
    }

    public boolean start(String[] args) {
        if (!processor.scanForPattern(args) || (selectOperation = processor.processArgs(args)) == null) {
            processor.showOptions();
            return false;
        }
        return true;
    }


    /**
     * handling of events - invoked by the observable
     *
     * @param observable
     * @param o
     */
    @Override
    public void update(Observable observable, Object o) {
        if (o.equals(CommandsEnum.START_OPT)) {
            System.out.println("Operation have started");
        } else if (o.equals(CommandsEnum.END_OPT)) {
            System.out.println("Operation have ended, took :" + Timer.getInstance().getLastTime());
        } else if (o instanceof Exception) {
            String message = ((Exception) o).getMessage();
            if (message != null)
                System.out.println(((Exception) o).getMessage());
        } else if (o instanceof Observable) {
            ((Observable) o).addObserver(this);
        } else if (o instanceof String) {
            System.out.println(o);
        }
    }


}
