package utils;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by mzeus on 7/3/16.
 */
@ToString
public class Timer {


    private static Timer instance = new Timer();

    private long start = 0;
    private long end = 0;
    @Getter
    private long lastTime = 0;

    public static Timer getInstance() {
        return instance;
    }

    private Timer() {
    }


    public void start() {
        start = System.currentTimeMillis();
    }

    public long end() {
        end = System.currentTimeMillis();
        lastTime = end - start;
        return lastTime;
    }


}
