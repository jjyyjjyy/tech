package me.jy.lang.util;

import lombok.SneakyThrows;

/**
 * @author jy
 */
public final class SleepUtil {

    @SneakyThrows
    public static void sleep(int ms) {
        Thread.sleep(ms);
    }

}
