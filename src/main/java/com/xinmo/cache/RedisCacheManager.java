
package com.xinmo.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;


@SuppressWarnings("rawtypes")
public class RedisCacheManager implements CacheManager {
    
    private static Logger logger = LogManager.getLogger(RedisCacheManager.class);

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

    private RedisManager redisManager;

    
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");
        
        Cache c = this.caches.get(name);
        
        if (c == null) {

            // create a new cache instance
            c = new RedisCache<K, V>(this.redisManager);
            
            // add it to the cache collection
            this.caches.put(name, c);
        }
        return c;
    }

    public RedisManager getRedisManager() {
        return this.redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    
}
