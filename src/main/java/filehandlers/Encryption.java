package filehandlers;

import commandline.CliHandler;

import java.io.*;

/**
 * Created by Mor on 5/19/2016.
 */
public class Encryption implements FileHandler{


    public void handleFile(File file) {
        FileInputStream fis = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            System.out.println(bufferedReader.readLine());
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            do {
                file = new File(CliHandler.getInstance().handleNotFoundFile(file.getPath()));
            } while (!file.exists() || !file.isFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getDescription() {
        return "encrypt a file";
    }
}
