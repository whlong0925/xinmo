package com.xinmo.cache;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SuppressWarnings("resource")
public class RedisManager {

    private int expire = 0;

    private JedisPool jedisPool = null;

    public RedisManager() {

    }

    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = this.jedisPool.getResource();
        try {
            value = jedis.get(key);
        } finally {
            jedis.close();
        }
        return value;
    }

    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } finally {
            jedis.close();
        }
        return value;
    }

    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            jedis.close();
        }
        return value;
    }

    public void del(byte[] key) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public void flushDB() {
        Jedis jedis = this.jedisPool.getResource();
        try {
            jedis.flushDB();
        } finally {
            jedis.close();
        }
    }

    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = this.jedisPool.getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            jedis.close();
        }
        return dbSize;
    }

    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = this.jedisPool.getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            jedis.close();
        }
        return keys;
    }

    public int getExpire() {
        return this.expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public JedisPool getJedisPool() {
        return this.jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

}