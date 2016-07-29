package commandline;


import com.google.inject.Inject;
import domain.algorithm.Algorithm;
import domain.operations.*;
import lombok.Getter;
import utils.Timer;

import java.util.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class CommandlineHandler implements Observer, UserInterface<Algorithm, Operation> {


    @Getter
    private Class<? extends Operation> selectOperation;
    @Getter
    private CommandlineProcessor<Operation> processor;


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


    @Override
    public void update(Observable observable, Object o) {
        if (o.equals(CommandsEnum.START_OPT)) {
            System.out.println("Operation have started");
        } else if (o.equals(CommandsEnum.END_OPT)) {
            System.out.println("Operation have ended, took :" + Timer.getInstance().getLastTime());
        } else if (o instanceof Exception) {
            System.out.println(((Exception) o).getMessage());
            ((Exception) o).printStackTrace();
        } else if (o instanceof Observable) {
            ((Observable) o).addObserver(this);
        } else if (o instanceof String) {
            System.out.println(o);
        }
    }


}
