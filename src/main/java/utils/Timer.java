package utils;

import lombok.Getter;
import lombok.ToString;

import java.time.Clock;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mzeus on 7/3/16.
 */
@ToString
public class Timer implements Observer{


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
        return Clock.systemDefaultZone().millis() - start;
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
