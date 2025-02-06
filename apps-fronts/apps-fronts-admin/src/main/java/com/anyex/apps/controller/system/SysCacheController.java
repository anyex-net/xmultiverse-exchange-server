package com.anyex.apps.controller.system;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.apps.system.service.SysCacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping(GlobalConst.SYSTEM)
@Api(tags = "缓存管理")
public class SysCacheController extends GenericController
{
    @Autowired(required = false)
    SysCacheService cacheService;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/cache/data")
    @RequiresPermissions("system:cache:data")
    @ApiOperation(value = "根据key查询缓存",httpMethod = "GET")
    public JsonMessage<HashMap<Object, Object>> data(String redisKey) throws BusinessException
    {
        HashMap<Object, Object> map = new HashMap<>();
        Set<String> keys = redisTemplate.keys("*");
        if (null == keys) { return getJsonMessage(CommonEnums.SUCCESS, map); }
        if (StringUtils.isNotEmpty(redisKey))
        {
            for (String key : keys)
            {
                if (key.indexOf(redisKey) > -1)
                {
                    Object value = RedisUtils.getObject(key);
                    map.put(key, value);
                }
            }
        }
        else
        {
            for (String key : keys)
            {
                Object value = RedisUtils.getObject(key);
                map.put(key, value);
            }
        }
        return getJsonMessage(CommonEnums.SUCCESS, map);
    }

    @PostMapping(value = "/cache/del")
    @RequiresPermissions("system:cache:operator")
    @ApiOperation(value = "根据指定key删除缓存",httpMethod = "POST" )
    public JsonMessage delete(@RequestParam("redisKey") String redisKey) throws BusinessException
    {
        if (StringUtils.isBlank(redisKey))
        {// Key不可为空
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        cacheService.delete(redisKey);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/cache/delbatch")
    @ApiOperation(value = "根据type清除所有缓存(session、mybatis、all)",httpMethod = "POST")
    public JsonMessage cleanBatch(@RequestParam("type") String type) throws BusinessException
    {
        if ("all".equals(type))
        {
            cacheService.cleanAll();
        }
        else if ("mybatis".equals(type))
        {
            cacheService.cleanMybatis();
        }
        else if ("session".equals(type))
        {
            cacheService.cleanSession();
        }
        else
        {
            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID, "缓存类型不存在！");
        }
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/cache/clean/all")
    @ApiOperation(value = "清除所有缓存",httpMethod = "POST")
    public JsonMessage cleanAll() throws BusinessException
    {
        cacheService.cleanAll();
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/cache/clean/mybatis")
    @ApiOperation(value = "清除mybatis缓存",httpMethod = "POST")
    public JsonMessage cleanMybatis() throws BusinessException
    {
        cacheService.cleanMybatis();
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/cache/clean/session")
    @ApiOperation(value = "清除session缓存",httpMethod = "POST")
    public JsonMessage cleanSession() throws BusinessException
    {
        cacheService.cleanSession();
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}

