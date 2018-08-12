package me.jy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author jy
 */
@Service
public class WindowRateLimiter implements RateLimiter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean limit(String key) {
        String currentTimes = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            StringRedisConnection redisConnection = (StringRedisConnection) connection;
            long currentTime = System.nanoTime();
            redisConnection.zAdd(key, currentTime, currentTime + "");
//            redisConnection.zRemRangeByScore(key, 0, currentTime - 1_000_000_000);
            redisConnection.zCount(key, currentTime - 1_000_000_000, currentTime);
            return null;
        }).get(1).toString();
        int times = Integer.valueOf(currentTimes);
        return times > MAX;
    }
}
