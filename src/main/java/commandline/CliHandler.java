package commandline;

import filehandlers.FileHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler {

    private HashMap<String, FileHandler> fileHandlerHashMap;

    public CliHandler() {
    }

    public CliHandler addOption(String arg, FileHandler fileHandler) {
        if (fileHandlerHashMap == null)
            fileHandlerHashMap = new HashMap<String, FileHandler>();
        if (fileHandler == null || arg == null)
            return this;
        fileHandlerHashMap.put(arg, fileHandler);
        return this;
    }

    public void handleArgument(String arg) {
        FileHandler fileHandler = fileHandlerHashMap.get(arg);
        if (fileHandler == null)
            showOptions();
    }

    private void showOptions() {
        System.out.println("Usage:... <option>\nOptions:");
        for (Map.Entry<String, FileHandler> entry :
                fileHandlerHashMap.entrySet()) {
            System.out.printf("%s - %s", entry.getKey(), entry.getValue().getDescription());
        }
    }

}
