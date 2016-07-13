package utils;

import lombok.Getter;
import lombok.ToString;

import java.time.Clock;

/**
 * Created by mzeus on 7/3/16.
 */
@ToString
public class Timer {


    private static Timer instance = new Timer();

    @Getter
    private long start = 0;
    @Getter
    private long end = 0;
    @Getter
    private long lastTime = 0;

    public static Timer getInstance() {
        return instance;
    }

    private Timer() {
    }


    public void start() {
        start = Clock.systemDefaultZone().millis();
    }

    public long end() {
        end = Clock.systemDefaultZone().millis();
        lastTime = end - start;
        return lastTime;
    }

    public long current() {
        return end - start;
    }


}
