package me.jy.lang.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jy
 */
@Slf4j
public final class SleepUtil {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.interrupted();
            log.warn("I am interrupted");
        }
    }

}
