package com.app.highload.tick.model;

import org.springframework.data.redis.core.RedisHash;
@RedisHash("tick")
public record Tick (int id, double counter) { }
