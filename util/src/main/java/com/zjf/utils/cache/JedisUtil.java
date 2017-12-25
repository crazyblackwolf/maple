package com.zjf.utils.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author: linziye
 * @description:
 * @date: 15:34 2017/12/21 .
 */
public final class JedisUtil {

    private JedisUtil() {
        throw new AssertionError();
    }

    private static JedisPool jedisPool = null;

    static {
        try {
            jedisPool = JedisFactory.getInstance().getJedisPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JedisPool getJedis() {
        return jedisPool;
    }

    public static void recycleJedis(Jedis jedis) {
        jedis.close();
    }

    /**
     * Redis存储
     */
    public static void put(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        recycleJedis(jedis);
    }

    /**
     * Redis存储
     */
    public static void put(String key, String value, Integer seconds) {
        Jedis jedis = jedisPool.getResource();
        jedis.setex(key, seconds, value);
        recycleJedis(jedis);
    }

    public static String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        recycleJedis(jedis);
        return value;
    }

    public static Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long num = jedis.expire(key, seconds);
        recycleJedis(jedis);
        return num;
    }

    public static String getlist(String key) {
        Jedis jedis = jedisPool.getResource();
        List<String> list = jedis.lrange(key, -1, -1);
        if (list.size() == 0) {
            return "";

        }
        recycleJedis(jedis);
        return list.get(0);
    }

    public static Long remove(String key) {
        Jedis jedis = jedisPool.getResource();
        Long num = jedis.del(key);
        recycleJedis(jedis);
        return num;
    }

    public static void removeAll() {
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        recycleJedis(jedis);
    }


    public static List<String> mget(String mkey) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hvals(mkey);
    }

    public static String getHash(String key, String filed) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hget(key, filed);
    }

    public static void addHash(String key, String feild, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.hset(key, feild, value);
        recycleJedis(jedis);
    }

    public static void delHash(String hashKey) {
        Jedis jedis = jedisPool.getResource();
        Set<String> set = jedis.hkeys(hashKey);
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            jedis.del(obj.toString());
            jedis.hdel(hashKey, obj.toString());
        }
        recycleJedis(jedis);
    }

}
