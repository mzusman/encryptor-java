package commandline;

import filehandlers.FileHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mor on 5/19/2016.
 */
public class CliHandler {

    private HashMap<String, FileHandler> fileHandlerHashMap;

    public CliHandler(Map<String, FileHandler> fileHandlers) {
        this.fileHandlerHashMap = (HashMap<String, FileHandler>) fileHandlers;
    }

    public void handleArgument(String arg) {
        FileHandler fileHandler = fileHandlerHashMap.get(arg);
        if(fileHandler == null)
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
