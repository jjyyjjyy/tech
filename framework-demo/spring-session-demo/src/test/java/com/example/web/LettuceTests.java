package com.example.web;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * @author jy
 */
@Testcontainers
class LettuceTests {

    private static GenericContainer redis = new GenericContainer("redis:alpine")
        .withExposedPorts(6379);

    private static RedisClient redisClient;

    @BeforeAll
    static void init() {
        redis.start();

        // redis://[password@]host[:port][/databaseNumber]
        // rediss://[password@]host[:port][/databaseNumber]
        // redis-sentinel://[password@]host[:port][,host2[:port2]][/databaseNumber]#sentinelMasterId
        redisClient = RedisClient.create(RedisURI.create(redis.getContainerIpAddress(), redis.getFirstMappedPort()));
    }

    @AfterAll
    static void destroy() {
        redis.stop();
        redisClient.shutdown();
    }

    @Test
    void testContainerStartup() {
        Assertions.assertTrue(redis.isRunning());
    }

    @Test
    void testApis() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        Assertions.assertEquals("PONG", commands.ping());

        connection.async().ping().thenAccept(res -> Assertions.assertEquals("PONG", res));
        connection.close();
    }

}
