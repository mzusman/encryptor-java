package commandline;


import boot.DecryptModule;
import boot.DirectoryModule;
import boot.EncryptModule;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Module;
import filehandler.algorithm.Algorithm;
import filehandler.operations.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import utils.Timer;
import utils.xml.XmlFilesManager;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.MatchResult;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler implements Observer, UserInterface<Algorithm, Operation> {


    @Getter
    private Class<? extends Operation> selectOperation;
    private CommandlineScanner scanner;
    private CommandlineSelector selector;
    private CommandlineProcessor processor;


    @Inject
    public CliHandler(CommandlineScanner scanner, CommandlineSelector selector, CommandlineProcessor processor) {
        this.scanner = scanner;
        this.selector = selector;
        this.processor = processor;
    }

    public void start(String[] args) {
        if (!scanner.scanForPattern(args) || (selectOperation = processor.processArgs(args)) == null) {
            showOptions();
            return false;
        }
        return true;
    }



    @Override
    public void update(Observable observable, Object o) {
        if (o.equals(CommandsEnum.START)) {
            System.out.println("Operation have started");
        } else if (o.equals(CommandsEnum.END)) {
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
