package com.app.counter;

import org.springframework.data.redis.core.RedisHash;
@RedisHash("tick")
public record Tick (int id, double counter) { }
