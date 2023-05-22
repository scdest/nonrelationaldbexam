package com.exam.relationaldbexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CarShowroomRedisRepository {

    private final RedisTemplate<String, CarShowroom> redisTemplate;

    public CarShowroomRedisRepository(@Autowired RedisConnectionFactory redisConnectionFactory)
    {
        this.redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
    }

    public CarShowroom save(CarShowroom entity) {
        entity.setId(UUID.randomUUID());
        redisTemplate.opsForValue().set(entity.getId().toString(), entity);
        return entity;
    }

    public Optional<CarShowroom> findById(UUID uuid) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(uuid.toString()));
    }

    public void deleteById(UUID id) {
        redisTemplate.delete(id.toString());
    }
}
