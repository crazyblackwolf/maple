package com.zjf.common.constant.redis;

/**
 * @author : zhoujianfei
 * @description :
 * @date : 2017/11/21.
 */
public class RedisConstant {

    /**
     * ip
     */
    public static final String HOST = "redis.host";
    /**
     * 端口
     */
    public static final String PORT = "redis.port";
    /**
     * 密码
     */
    public static final String AUTH = "redis.auth";
    /**
     * 可用连接实例的最大数目，默认值为8;
     * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽).
     */
    public static final String MAX_ACTIVE = "redis.maxActive";
    /**
     * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
     */
    public static final String MAX_IDLE = "redis.maxIdle";
    /**
     *
     */
    public static final String TIME_OUT = "redis.timeOut";
    /**
     * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException;
     */
    public static final String MAX_WAIT = "redis.maxWait";
    /**
     * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的;
     */
    public static final String TEST_ON_BORROW = "redis.testOnBorrow";
    public static final String DATABASE = "redis.database";
    public static final String CACHE_DATABASE = "redis.cache.database";

}
