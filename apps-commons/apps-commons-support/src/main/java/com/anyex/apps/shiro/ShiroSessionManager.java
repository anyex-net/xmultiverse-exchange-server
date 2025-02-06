package com.anyex.apps.shiro;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * SessionManager
 * <p>File: SessionManager.java </p>
 * <p>Title: SessionManager </p>
 * <p>Description: SessionManager </p>
 * <p>Copyright: Copyright (c) 2019-02-15</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class ShiroSessionManager
{
    @Autowired(required = false)
    private RedisTemplate sessionTemplate;
    
    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public String get(String key)
    {
        if (StringUtils.isBlank(key))
        { return null; }
        String value = null;
        try
        {
            value = (String) sessionTemplate.opsForValue().get(key);
        }
        catch (Exception e)
        {
            log.warn("get {} ERROR:{}", key, e);
        }
        return value;
    }
    
    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Set<String> getKeys(String key)
    {
        if (StringUtils.isBlank(key)) return null;
        Set<String> value = null;
        try
        {
            value = sessionTemplate.keys(key);
        }
        catch (Exception e)
        {
            log.warn("get {} ERROR:{}", key, e);
        }
        return value;
    }
    
    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object getObject(String key)
    {
        if (StringUtils.isBlank(key))
        { return null; }
        Object value = null;
        try
        {
            value = sessionTemplate.opsForValue().get(key);
        }
        catch (Exception e)
        {
            log.warn("get {} ERROR:{}", key, e);
        }
        return value;
    }
    
    /**
     * 缓存对象
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public void putObject(String key, Object value)
    {
        try
        {
            sessionTemplate.opsForValue().set(key, value);
        }
        catch (Exception e)
        {
            log.warn("putObject {} = {} ERROR:{}", key, value.toString(), e);
        }
    }
    
    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key          缓存的键值
     * @param value        缓存的值
     * @param cacheSeconds 过期时间
     */
    public void putObject(String key, Object value, int cacheSeconds)
    {
        try
        {
            if (cacheSeconds < 0) sessionTemplate.opsForValue().set(key, value);
            else sessionTemplate.opsForValue().set(key, value, cacheSeconds, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            log.warn("putObject {} = {} ERROR:{}", key, value.toString(), e);
        }
    }
    
    /**
     * 从Map中删除指定的存储
     *
     * @param key
     * @param hashKey
     * @return 状态码，1成功，0失败
     */
    public Long delMap(String key, Object hashKey)
    {
        Long flag = null;
        try
        {
            flag = sessionTemplate.opsForHash().delete(key, hashKey);
        }
        catch (Exception e)
        {
            log.warn("delMap {} ERROR:{}", key, e);
        }
        return flag;
    }
    
    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public Map<String, Object> getMap(String key)
    {
        Map<String, Object> map = Maps.newHashMap();
        try
        {
            map = sessionTemplate.opsForHash().entries(key);
        }
        catch (Exception e)
        {
            log.warn("getMap {} ERROR:{}", key, e);
        }
        return map;
    }
    
    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     */
    public void putMap(String key, Map<Object, Object> dataMap)
    {
        try
        {
            if (null != dataMap)
            {
                HashOperations<String, Object, Object> operations = sessionTemplate.opsForHash();
                if (operations != null)
                {
                    operations.putAll(key, dataMap);
                }
            }
        }
        catch (Exception e)
        {
            log.warn("putMap {} = {} ERROR:{}", key, dataMap.hashCode(), e);
        }
    }
    
    /**
     * 删除缓存
     *
     * @param key 键
     * @return 值
     */
    public void del(String key)
    {
        try
        {
            if (StringUtils.isBlank(key)) return;
            sessionTemplate.delete(key);
        }
        catch (Exception e)
        {
            log.warn("del {} ERROR:{}", key, e);
        }
    }
}
