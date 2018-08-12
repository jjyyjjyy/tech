package me.jy.util;

/**
 * @author jy
 */
public interface RateLimiter {

    int MAX = 1;

    /**
     * 是否超過調用限制
     *
     * @param key 用来判断限制的key
     * @return 超過限制為true
     */
    boolean limit(String key);
}
