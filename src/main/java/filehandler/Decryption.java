package filehandler;

import java.io.File;

/**
 * Created by Mor on 5/19/2016.
 */
public class Decryption implements Operation {

    public String getDescription() {
        return "decrypt a file";
    }

    @Override
    public String act(File file) {
        return "";
    }

}
