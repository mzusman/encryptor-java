package commandline;

import com.sun.istack.internal.NotNull;
import filehandlers.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler {
    private static CliHandler instance = new CliHandler();

    public static CliHandler getInstance() {
        return instance;
    }

    private HashMap<String, FileHandler> fileHandlerHashMap = new HashMap<String, FileHandler>();

    private CliHandler() {
    }

    public
    @NotNull
    CliHandler addOption(String arg, FileHandler fileHandler) {
        if (fileHandler == null || arg == null)
            return this;
        fileHandlerHashMap.put(arg, fileHandler);
        return this;
    }

    public void handleArguments(@NotNull String[] arg) {
        if (arg.length != 2) {
            showOptions();
            return;
        }

        File file = new File(arg[1]);
        if (!file.exists() || !file.isFile()) handleNotFoundFile(file.getPath());
        else if (!file.isFile()) System.out.println("error: not a file");
        else if (!file.canRead()) System.out.println("don't have permission to read");
        else if (!file.canWrite()) System.out.println("don't have permission to write");

        FileHandler fileHandler = fileHandlerHashMap.get(arg[0]);
        if (fileHandler == null) {
            showOptions();
            return;
        }

        fileHandler.handleFile(file);

    }

    public String handleNotFoundFile(String path) {
        System.out.printf("file at %s was not found or does not exist\n", path);
        System.out.println("Enter the path again:");
        return System.console().readLine();
    }

    public void showOptions() {
        System.out.println("usage: ... <option> <file>\nOptions:");
        if (fileHandlerHashMap.size() == 0) {
            System.out.println("no handlers are available");
            return;
        }
        fileHandlerHashMap.forEach((s, f)
                -> System.out.printf("%s - %s\n", s, f.getDescription()));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CliHandler)) return false;

        CliHandler that = (CliHandler) o;

        return fileHandlerHashMap != null ? fileHandlerHashMap.equals(that.fileHandlerHashMap) : that.fileHandlerHashMap == null;

    }

    @Override
    public int hashCode() {
        return fileHandlerHashMap != null ? fileHandlerHashMap.hashCode() : 0;
    }
}
