package me.jy.lang;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

/**
 * @author jy
 */
public class TimeTests {

    @Test
    public void testUnixTimestamp() {
        System.out.println(new Date().getTime());
        System.out.println(Instant.now().getEpochSecond());
    }
}
