package com.ziyuan.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisOperator {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * returns the TTL (time to live) of a given key in seconds
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * Set the expiration time, in seconds
     *
     * @param key
     * @return
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * increment the value of a key by a given delta
     *
     * @param key
     * @param delta can be negative
     * @return
     */
    public long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * find all keys matching the given pattern
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * delete the given key
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * set the given value to a key
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * set the given value to a key with an expiration time in seconds
     *
     * @param key
     * @param value
     * @param timeout
     */
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * get the value of a key
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * get multiple keys
     *
     * @param keys
     * @return
     */
    public List<String> mget(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * get multiple keys using pipelining (reduce connection amount)
     *
     * @param keys
     * @return
     */
    public List<Object> batchGet(List<String> keys) {
        List<Object> result = redisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection src = (StringRedisConnection) connection;

                for (String k : keys) {
                    src.get(k);
                }
                return null;
            }
        });

        return result;
    }

    /**
     * set a hash key-field-value
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * get a hash key-field-value
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * delete multiple hash key-fields; ignore if the field doesn't exist
     *
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * get all hash key-field-values
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * add a value to the head of the key-list
     *
     * @param key
     * @param value
     * @return new length of the list
     */
    public long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * pop the head of the key-list
     *
     * @param key
     * @return head of the list
     */
    public String lpop(String key) {
        return (String) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * add a value to the tail of the key-list
     *
     * @param key
     * @param value
     * @return new length of the list
     */
    public long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * add a value to the set
     *
     * @param key
     * @param value
     * @return
     */
    public long sadd(String key, String value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    /**
     * add a value to the set with tll
     *
     * @param key
     * @param value
     * @return
     */
    public long sadd(String key, String value, long timeout) {
        redisTemplate.opsForSet().add(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * delete key-value from the set
     *
     * @param key
     * @return
     */
    public long srem(String key, String value) {
        return redisTemplate.opsForSet().remove(key, value);
    }

    /**
     * get size of the set
     *
     * @param key
     * @return
     */
    public long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * if obj contain in set key
     *
     * @param key
     * @return
     */
    public boolean sismember(String key, String obj) {
        return redisTemplate.opsForSet().isMember(key, obj);
    }

    /**
     * return all member in set key
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * get values of the zset in a range
     *
     * @param key, start, end
     * @return
     */
    public Set<String> zrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * zrevrange values of the zset in a range
     */
    public Set<String> zrevrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * add a value to the zset
     */
    public boolean zadd(String key, double score, String value) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * zrangebylex, [min, max)
     */
    public Set<String> zrangebylex(String key, String min, String max) {
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.lt(max);
        range.gte(min);
        return redisTemplate.opsForZSet().rangeByLex(key, range);
    }

    /**
     * Zrangebylex  with offset and count, [min, max)
     */
    public Set<String> zrangebylex(String key, String min, String max, int offset, int count) {
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.lt(max);
        range.gte(min);
        RedisZSetCommands.Limit limit = new RedisZSetCommands.Limit();
        limit.count(count);
        limit.offset(offset);
        return redisTemplate.opsForZSet().rangeByLex(key, range, limit);
    }

    /**
     * zrevrangebylex, [min, max)
     */
    public Set<String> zrevrangebylex(String key, String min, String max) {
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.lt(max);
        range.gte(min);
        return redisTemplate.opsForZSet().reverseRangeByLex(key, range);
    }

    /**
     * zrevrangebylex with offset and count, [min, max)
     */
    public Set<String> zrevrangebylex(String key, String min, String max, int offset, int count) {
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.lt(max);
        range.gte(min);
        RedisZSetCommands.Limit limit = new RedisZSetCommands.Limit();
        limit.count(count);
        limit.offset(offset);
        return redisTemplate.opsForZSet().reverseRangeByLex(key, range, limit);
    }

    /**
     * zrank
     */
    public Long zrank(String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * remove range of values from the zset [start, end]
     */
    public long zremrangeByRank(String key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * zset size
     */
    public long zcard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }


    /**
     * remove key-obj in zset
     *
     * @param key
     * @param obj
     * @return
     */
    public long zrem(String key, String obj) {
        return redisTemplate.opsForZSet().remove(key, obj);
    }

    // zremrangebyrank

    /**
     * remove range of values from the zset by Rank
     */
    public long zremrangebyrank(String key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

}