package commandline;

import com.sun.istack.internal.NotNull;
import filehandlers.FileHandler;

import java.util.HashMap;
import java.util.Map;

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
        if (arg.length == 0) {
            showOptions();
            return;
        }
        FileHandler fileHandler = fileHandlerHashMap.get(arg[0]);
        if (fileHandler == null) {
            showOptions();
            System.out.println("no handlers are available");
        }
    }

    public void showOptions() {
        System.out.println("Usage:... <option>\nOptions:");
        if (fileHandlerHashMap.size() == 0) {
            System.out.println("no handlers are available");
            return;
        }
        for (Map.Entry<String, FileHandler> entry :
                fileHandlerHashMap.entrySet()) {
            System.out.printf("%s - %s\n", entry.getKey(), entry.getValue().getDescription());
        }
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
