package com.example.web;

import com.example.SessionDemoApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author jy
 */
@SpringBootTest(classes = SessionDemoApplication.class)
class RedisCommandTests {

    private static final String KEY = "a";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @AfterEach
    void destroy() {
        stringRedisTemplate.delete(KEY);
    }

    @Test
    void testStringCommand() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        valueOperations.set(KEY, "1");
        assertEquals("1", valueOperations.get(KEY));
        // setnx key如果存在则返回false
        Assertions.assertFalse(valueOperations.setIfAbsent(KEY, "2").booleanValue());
        // setxx
        Assertions.assertTrue(valueOperations.setIfPresent(KEY, "2").booleanValue());
        assertEquals("2", valueOperations.get(KEY));
        // incr 返回增长后的值
        assertEquals(3L, valueOperations.increment(KEY).longValue());
        // getset 返回之前的值
        assertEquals("3", valueOperations.getAndSet(KEY, "hello,"));
        // getset 第一次设置值时返回null
        Assertions.assertNull(valueOperations.getAndSet("aaa", "hello,"));
        // append 返回修改过后的字符串长度
        assertEquals(11, valueOperations.append(KEY, "world").intValue());
        // size 返回字符串长度
        assertEquals(11L, valueOperations.size(KEY).longValue());

        stringRedisTemplate.delete("aaa");
    }

    @Test
    void testHashCommand() {
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();

        hashOperations.put(KEY, "a", "1");
        assertEquals("1", hashOperations.get(KEY, "a"));
        assertEquals(1, hashOperations.delete(KEY, "a", "b", "c", "d").longValue());
        assertTrue(hashOperations.putIfAbsent(KEY, "a", "1"));
        assertEquals(23, hashOperations.increment(KEY, "a", 22L).longValue());
        assertEquals(2, hashOperations.lengthOfValue(KEY, "a").longValue());

        assertArrayEquals(new String[]{"23"}, hashOperations.values(KEY).toArray());

        assertEquals(0, hashOperations.delete(KEY, "b").longValue());
        assertEquals(1, hashOperations.delete(KEY, "a").longValue());

    }

    @Test
    void testListCommand() {
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();

        assertEquals(3, listOperations.leftPushAll(KEY, "1", "2", "3").longValue());

        assertEquals("1", listOperations.index(KEY, 2));

        assertArrayEquals(new String[]{"3", "2", "1"}, listOperations.range(KEY, 0, -1).toArray());
        listOperations.leftPushAll(KEY, "1", "2", "3");
        listOperations.remove(KEY, 1, "1");
        assertArrayEquals(new String[]{"3", "2", "3", "2", "1"}, listOperations.range(KEY, 0, -1).toArray());

        listOperations.set(KEY, 0, "33");
        assertArrayEquals(new String[]{"33", "2", "3", "2", "1"}, listOperations.range(KEY, 0, -1).toArray());

        assertEquals(5, listOperations.size(KEY).longValue());

    }

    @Test
    void testSetCommand() {
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

        assertEquals(1, setOperations.add(KEY, "1", "1", "1").longValue());

        assertTrue(setOperations.isMember(KEY, "1"));
        assertEquals(1, setOperations.remove(KEY, "1").longValue());
        assertEquals(0, setOperations.remove(KEY, "1").longValue());
        assertEquals(0, setOperations.size(KEY).longValue());
        setOperations.add(KEY, "1");
        assertEquals(1, setOperations.size(KEY).longValue());

        IntStream.rangeClosed(1, 10).boxed().map(String::valueOf).forEach(s -> setOperations.add(KEY, s));

        assertArrayEquals(IntStream.rangeClosed(1, 10).boxed().map(String::valueOf).toArray(), setOperations.members(KEY).toArray());
        setOperations.add("b", "1", "2");

        assertArrayEquals(new String[]{"1", "2"}, setOperations.intersect(KEY, "b").toArray());

        setOperations.intersectAndStore(KEY, "b", "tmp");
        assertArrayEquals(new String[]{"1", "2"}, setOperations.members("tmp").toArray());

        assertNotNull(setOperations.randomMember(KEY));
        assertNotNull(setOperations.pop(KEY));

        stringRedisTemplate.delete("b");
        stringRedisTemplate.delete("tmp");

    }

    @Test
    void testZSetCommand() {
        ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

        zSetOperations.add(KEY, "a", 10);
        zSetOperations.add(KEY, "b", 20);

        assertEquals(2, zSetOperations.size(KEY).longValue());
        assertEquals(20, zSetOperations.score(KEY, "b").doubleValue());
        assertEquals(1, zSetOperations.rank(KEY, "b").longValue());
        assertEquals(0, zSetOperations.reverseRank(KEY, "b").longValue());

        assertArrayEquals(new String[]{"b"}, zSetOperations.range(KEY, 1, 1).toArray());

        assertEquals(1, zSetOperations.count(KEY, 11, 20).longValue());

    }

}
