package com.xinmo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinmo.cache.SerializeUtils;
import com.xinmo.service.RedisService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-spring.xml"})
public class RedisTestCase {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;

    @Test
    public void testRedis() {
       /* redisService.set("testss", "test123123");
        Object objValue = redisService.get("testss");
        System.out.println(objValue);*/
        byte[] key = SerializeUtils.serialize("shiro_redis_session:admin");
        System.out.println(this.redisTemplate.opsForValue().get(key));
    }

   

}
