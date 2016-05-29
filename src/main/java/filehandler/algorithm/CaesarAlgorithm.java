package filehandler.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.util.Scanner;

/**
 * Created by mzeus on 29/05/16.
 */
@AllArgsConstructor
public class CaesarAlgorithm implements CipherAlgorithm {


    @Override
    public void encrypt(InputStream in, OutputStream out,int key) {
        try {
            int raw;
            byte enc;
            while ((raw = in.read()) != -1) {
                enc = (byte) (raw + Integer.valueOf(key).byteValue());
                out.write(enc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void decrypt(InputStream in, OutputStream out,int key) {
        try {
            int raw;
            byte dec;
            while ((raw = in.read()) != -1) {
                dec = (byte) (raw - Integer.valueOf(key).byteValue());
                out.write(dec);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
