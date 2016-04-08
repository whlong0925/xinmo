package com.xinmo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service("redisService")
@SuppressWarnings("rawtypes")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    @Override
    public void set(String key, Object value) {
        ValueOperations valueOperations = this.redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    @Override
    public Object get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setList(String key, List<?> value) {
        ListOperations listOperations = this.redisTemplate.opsForList();
        listOperations.leftPush(key, value);
    }

    @Override
    public Object getList(String key) {
        //ListOperations可以理解为List<Object>
        return this.redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public void setSet(String key, Set<?> value) {
        SetOperations setOperations = this.redisTemplate.opsForSet();
        setOperations.add(key, value);
    }

    @Override
    public Object getSet(String key) {
        return this.redisTemplate.opsForSet().members(key);
    }

    @Override
    public void setHash(String key, Map<String, ?> value) {
        this.redisTemplate.opsForHash().putAll(key, value);
    }

    @Override
    public Object getHash(String key) {
        return this.redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void delete(String key) {
        this.redisTemplate.delete(key);
    }

    public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
        return this.redisTemplate;
    }

    public void setRedisTemplate(
            RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    

}