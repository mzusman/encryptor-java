package utils;

import org.junit.Test;

import java.sql.Time;
import java.time.Clock;

import static org.junit.Assert.*;

/**
 * Created by mzeus on 7/3/16.
 */
public class TimerTest {
    @Test
    public void end() throws Exception {
        long time = 1000;
        Timer.getInstance().start();
        Thread.sleep(time);
        Timer.getInstance().end();
        assertNotEquals(Timer.getInstance().getEnd(), Timer.getInstance().getStart());
    }


}