package com.zjf.utils.cache;

import com.zjf.common.constant.SystemConfig;
import com.zjf.common.constant.redis.RedisConstant;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;

/**
 * @author : zhoujianfei
 * @description :
 * @date : 2017/11/21.
 */
public class JedisFactory {

    private static JedisPool jedisPool;
    private static JedisFactory instance = null;

    public JedisFactory() {
        init();
    }

    public JedisFactory(JedisPoolConfig poolConfig, String host, int port) {
        jedisPool = new JedisPool(poolConfig, host, port);
    }

    public JedisFactory(JedisPoolConfig poolConfig, String host, int port, int timeOut) {
        jedisPool = new JedisPool(poolConfig, host, port, timeOut);
    }

    private void init() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        String host = SystemConfig.getValue(RedisConstant.HOST);
        Integer port = SystemConfig.getInt(RedisConstant.PORT);
        String auth = SystemConfig.getValue(RedisConstant.AUTH);
        Integer timeOut = SystemConfig.getInt(RedisConstant.TIME_OUT);
        Integer database = SystemConfig.getInt(RedisConstant.DATABASE);
        poolConfig.setMaxIdle(SystemConfig.getInt(RedisConstant.MAX_IDLE));
        jedisPool = new JedisPool(poolConfig, host, port, timeOut, auth, database);
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public static JedisFactory getInstance() throws IOException {
        if (instance == null) {
            instance = new JedisFactory();
        }
        return instance;
    }

}
