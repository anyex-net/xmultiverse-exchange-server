package com.anyex.apps.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.anyex.apps.bean.SpringContext;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JSONUtils
{
    private static final ObjectMapper mapper = SpringContext.getBean(ObjectMapper.class);
    
    /**
     * Bean对象转JSON
     *
     * @param object
     * @return
     */
    public static String beanToJson(Object object)
    {
        if (object != null)
        {
            try
            {
                return mapper.writeValueAsString(object);
            }
            catch (JsonProcessingException e)
            {
                log.error(e.getMessage());
            }
        }
        return null;
    }
    
    /**
     * Bean对象转byte[]
     *
     * @param object
     * @return
     */
    public static byte[] beanToByte(Object object)
    {
        if (object != null)
        {
            try
            {
                return mapper.writeValueAsBytes(object);
            }
            catch (JsonProcessingException e)
            {
                log.error(e.getMessage());
            }
        }
        return null;
    }
    
    /**
     * 将json字符串转换成对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T extends Serializable> T jsonToBean(String json, Class<T> clazz)
    {
        if (StringUtils.isEmpty(json) || clazz == null)
        { return null; }
        try
        {
            return mapper.readValue(json, clazz);
        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }
        return null;
    }
    
    /**
     * json字符串转map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json)
    {
        if (StringUtils.isEmpty(json))
        { return null; }
        try
        {
            return mapper.readValue(json, HashMap.class);
        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }
        return null;
    }
}
