package com.app.highload.redis;

public interface RedisPubInterface {
    void publish(String message);
}