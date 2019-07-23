package com.ddm.vblog.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date:2019/1/31 10:28
 * @Author ddm
 **/
@SuppressWarnings("unchecked")
@Component
public class RedisUtil {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    RedisSerializer valueSerializer() {
        return this.redisTemplate.getValueSerializer();
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final Object key) {
        Object result = null;
        return redisTemplate.opsForValue().get(key).toString();
    }

//    public Object getObject(final Object key) {
//        Object result = null;
//        return  redisTemplate.opsForValue().get(key)
//        result = operations.get(key);
//        return result;
//    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean leftPushRightPop(Object key , Object l){
        boolean result = false;
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            listOperations.leftPush(key, l);
            listOperations.rightPop(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  boolean hmset(String key, Map<String, String> value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String,String> hmget(String key) {
        Map<String,String> result =null;
        try {
            result=  redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean hmput(String h, String hk,Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().put(h,hk,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean hmHasKey(Object h, Object o){
        boolean result = false;
        try {
            return redisTemplate.opsForHash().hasKey(h, o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object hmGetValue(Object k, Object k2){
        return  redisTemplate.opsForHash().get(k, k2);
    }

    public Object hmDelValue(Object k, Object k2){
        return  redisTemplate.opsForHash().delete(k, k2);
    }

    public Long hmCountKey(Object h){
        try {
            return redisTemplate.opsForHash().size(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 将一个list集合放入该key的最右边
     * @param key
     * @param value
     * @return
     */
    public <T> boolean lset(String key, List<T> value){
        boolean result = false;
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean lset(String key,long l, Object value){
        boolean result = false;
        try {
            redisTemplate.opsForList().set(key,l,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> List<T> getList(Class<T> c,Object key){
        return JSONArray.parseArray(JSON.toJSONString(redisTemplate.opsForList().range(key, 0, -1)),c);
    }

    public Long getListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean lLeftPush(Object k, Object v){
        boolean result = false;
        try {
            redisTemplate.opsForList().leftPush(k,v);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<String> keys(String k){
        Set<String> set = new HashSet<String>();
        try {
            set = redisTemplate.keys(k);
        } catch (Exception e){
            e.printStackTrace();
        }
        return set;
    }

}
