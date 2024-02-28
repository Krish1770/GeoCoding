package com.example.geoCoding.templateClass;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Data
public class RedisCounter {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    public Boolean hasKey(Object key) {
        return redisTemplate.hasKey(key);
    }

    public void incrementTheValue(Object key) {
        redisTemplate.opsForValue().increment(key);
    }

    public void setValue(Object key, Object value) {

        redisTemplate.opsForValue().set(key, Long.valueOf(value.toString()));
    }

    public Object getValue(Object key) {
        return redisTemplate.opsForValue().get(key);
    }
}
