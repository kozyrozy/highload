package com.app.counter;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class TickRepository {
    private static final String KEY = "tick";
    private RedisTemplate<String,Object> redisTemplate;
    private ZSetOperations zsetOps;

    @Autowired
    public TickRepository(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @PostConstruct
    private void init() {
        zsetOps = redisTemplate.opsForZSet();
    }

    public boolean add(Tick tick) {
        return Boolean.TRUE.equals(zsetOps.add(KEY, tick.id(), tick.counter()));
    }

    public double score(Tick tick) {
        return zsetOps.score(KEY,tick.id());
    }

    public double increment(Tick tick, long delta) {
        return zsetOps.incrementScore(KEY,tick.id(),delta);
    }
}
