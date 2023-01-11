package com.example.demoi18n.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;


public class RedisService extends JedisCluster {
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    public final static String SET_RESULT_OK = "OK";

    public RedisService(HostAndPort node) {
        super(node);
    }

    public RedisService(HostAndPort node, int timeout) {
        super(node, timeout);
    }

    public RedisService(HostAndPort node, int timeout, int maxAttempts) {
        super(node, timeout, maxAttempts);
    }

    public RedisService(HostAndPort node, GenericObjectPoolConfig poolConfig) {
        super(node, poolConfig);
    }

    public RedisService(HostAndPort node, int timeout, GenericObjectPoolConfig poolConfig) {
        super(node, timeout, poolConfig);
    }

    public RedisService(HostAndPort node, int timeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
        super(node, timeout, maxAttempts, poolConfig);
    }

    public RedisService(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
        super(node, connectionTimeout, soTimeout, maxAttempts, poolConfig);
    }

    public RedisService(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String password, GenericObjectPoolConfig poolConfig) {
        super(node, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
    }

    public RedisService(Set<HostAndPort> nodes) {
        super(nodes);
    }

    public RedisService(Set<HostAndPort> nodes, int timeout) {
        super(nodes, timeout);
    }

    public RedisService(Set<HostAndPort> nodes, int timeout, int maxAttempts) {
        super(nodes, timeout, maxAttempts);
    }

    public RedisService(Set<HostAndPort> nodes, GenericObjectPoolConfig poolConfig) {
        super(nodes, poolConfig);
    }

    public RedisService(Set<HostAndPort> nodes, int timeout, GenericObjectPoolConfig poolConfig) {
        super(nodes, timeout, poolConfig);
    }

    public RedisService(Set<HostAndPort> jedisClusterNode, int timeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
        super(jedisClusterNode, timeout, maxAttempts, poolConfig);
    }

    public RedisService(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
        super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, poolConfig);
    }

    public RedisService(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String password, GenericObjectPoolConfig poolConfig) {
        super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
    }

    public String setNEX(String key, String value, int seconds) {
        return this.set(key, value, "NX", "EX", seconds);
    }

    public interface LockFunction<T> {
        T apply();
    }


    /**
     * @param key      加锁的key
     * @param seconds  加锁时间
     * @param function 处理逻辑
     * @param <T>      处理函数的返回值类型
     * @return 如果加锁失败, 返回null. 加锁成功则执行LockFunction并返回函数的返回值
     */
    public <T> T lock(String key, int seconds, LockFunction<T> function) {
        String identify = UUID.randomUUID().toString();
        if (!SET_RESULT_OK.equals(this.setNEX(key, identify, seconds))) {
            return null;
        }
        try {
            return function.apply();
        } finally {
            String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            this.eval(luaScript, Collections.singletonList(key), Collections.singletonList(identify));
        }
    }

    @Deprecated
    public boolean lock(String key, int seconds) {
        String threadId = Thread.currentThread().getId() + "";
        return SET_RESULT_OK.equals(this.setNEX(key, threadId, seconds));
    }

    public void unlock(String key) {
        String threadId = Thread.currentThread().getId() + "";
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        this.eval(luaScript, Collections.singletonList(key), Collections.singletonList(threadId));
    }
}
