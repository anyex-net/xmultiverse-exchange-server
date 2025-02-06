/*
 * @(#)ObjectUtils.java 2015-4-24 上午11:14:24
 * Copyright 2015 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.utils;

import com.anyex.apps.consts.CharsetConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * <p>File：ObjectUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-24 上午11:14:24</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils
{
    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
    
    private ObjectUtils()
    {
    }
    
    /**
     * 序列化对象
     *
     * @param object 对象
     * @return byte[] 字节数组
     */
    public static byte[] serialize(Object object)
    {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            if (object != null)
            {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                return baos.toByteArray();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 反序列化对象
     *
     * @param bytes 字节数组
     * @return Object 对象
     */
    public static Object unserialize(byte[] bytes)
    {
        ByteArrayInputStream bais = null;
        try
        {
            if (bytes != null && bytes.length > 0)
            {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 对象转字节数组
     *
     * @param object 对象
     * @return byte[] 字节数组
     */
    public static byte[] getBytes(Object object)
    {
        if (object instanceof String)
        {
            String string = (String) object;
            byte[] bytes = null;
            try
            {
                bytes = string.getBytes(CharsetConst.CHARSET_UT);
            }
            catch (UnsupportedEncodingException e)
            {
                logger.error(e.getMessage());
            }
            return bytes;
        }
        else
        {
            return serialize(object);
        }
    }
    
    /**
     * Object转换byte[]类型
     *
     * @param object 对象
     * @return byte[] 字节数组
     */
    public static byte[] toBytes(Object object)
    {
        return serialize(object);
    }
    
    /**
     * byte[]型转换Object
     *
     * @param bytes 字节数组
     * @return Object 对象
     */
    public static Object toObject(byte[] bytes)
    {
        return unserialize(bytes);
    }
}
