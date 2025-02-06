package com.anyex.apps.system.service;

import com.anyex.apps.bean.RedisCache;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 缓存服务实现类
 */
@Slf4j
@Service
public class SysCacheServiceImpl implements SysCacheService
{
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void delete(String key) throws BusinessException
    {
        redisTemplate.delete(key);
    }

    @Override
    public void cleanAll() throws BusinessException
    {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            log.info("redis cleanAll ok");
            return "ok";
        });
    }

    @Override
    public void cleanMybatis() throws BusinessException
    {
        for (String key : RedisCache.cacheKeys)
        {
            redisTemplate.delete(key);
            log.info("mybatis cache remove key=" + key);
        }
        log.info("cleanMybatis ok");
    }

    @Override
    public void cleanSession() throws BusinessException
    {
        try
        {
            Set<String> keys = redisTemplate.keys(CacheConst.ADMIN_SHIRO_CACHE_PREFIX.concat("*"));
            for (String key : keys)
            {
                log.info("cleanSession oauth key:{}", key);
                redisTemplate.delete(key);
            }
            log.info("cleanSession oauth OK");

            keys = redisTemplate.keys(CacheConst.WEB_SHIRO_CACHE_PREFIX.concat("*"));
            for (String key : keys)
            {
                log.info("cleanSession web key:{}", key);
                redisTemplate.delete(key);
            }
            log.info("cleanSession web OK");

            keys = redisTemplate.keys(CacheConst.ADMIN_SHIRO_CACHE_PREFIX.concat("*"));
            for (String key : keys)
            {
                log.info("cleanSession admin key:{}", key);
                redisTemplate.delete(key);
            }
            log.info("cleanSession admin OK");
        }
        catch (Exception e)
        {
            log.error("cleanSession:{}", e.getMessage());
        }
    }
}

