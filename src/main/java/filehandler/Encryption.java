package filehandler;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class Encryption implements Operation {


    public String getDescription() {
        return "encrypt a file";
    }

    @Override
    public String act(File file) {
        return "";
    }
}
