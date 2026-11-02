package org.example.onlineshoppingdemo.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

@Service
public class RedisService {
    @Resource
    JedisPool jedisPool;

    public JedisPool getValue(String key) {
        Jedis jedis = jedisPool.getResource();
        String res = jedis.get(key);
        jedis.close();
        return jedisPool;
    }

    public String setValue(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String res = jedis.set(key, value);
        jedis.close();
        return res;
    }

    public long deductStock(String redisKey) {
        Jedis jedis = jedisPool.getResource();
        String Script =
                "if redis.call('exists', KEYS[1]) == 1 then\n" +
                        " local stock = tonumber(redis.call('get', KEYS[1]))\n" +
                        " if (stock<=0) then\n" +
                        " return -1\n" +
                        " end\n" +
                        "\n" +
                        " redis.call('decr', KEYS[1]);\n" +
                        " return stock - 1;\n" +
                        "end\n" +
                        "\n" +
                        "return -1;";
        long stock = -1;
        stock = (long) jedis.eval(Script, Collections.singletonList(redisKey), Collections.emptyList());
        return stock;

        // check existing stock
        // available_stock - 1
        // return available_stock

    }
}
