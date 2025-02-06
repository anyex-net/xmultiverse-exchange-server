/*
 * @(#)CookieUtils.java 2015-4-16 下午2:38:52
 * Copyright 2015 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.utils;

import com.anyex.apps.consts.CharsetConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <p>File：CookieUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-16 下午2:38:52</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class CookieUtils
{
    // 一个小时
    private static final Integer iDefaultValidSecond = 3600;
    
    private CookieUtils()
    {
        // 防止实例化
    }
    
    /**
     * 将值存入cookie，浏览器关闭后失效
     * @param request HttpServletRequest
     * @param response 输出
     * @param name Cookie的名字
     * @param value Cookie的值
     */
    public static void put(HttpServletRequest request, HttpServletResponse response, String name, String value)
    {
        put(request, response, name, value, iDefaultValidSecond);
    }
    
    /**
     * Put cookie to the client
     * @param response 输出
     * @param name Cookie的名字
     * @param value Cookie的值
     */
    public static void put(HttpServletResponse response, String name, String value)
    {
        try
        {
            Cookie cookie = new Cookie(name, encode(value));
            cookie.setPath("/");
            // cookie.setDomain("");
            cookie.setMaxAge(iDefaultValidSecond);
            response.addCookie(cookie);
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
        }
    }
    
    /**
    * 设定一个Cookie,有生存时间设定,单位为秒
    * @param request 请求
    * @param response 响应
    * @param name  Cookie的名称
    * @param value  Cookie的值
    * @param iValidSecond Cookie生存秒数
    */
    public static void put(HttpServletRequest request, HttpServletResponse response, String name, String value, int iValidSecond)
    {
        try
        {
            Cookie cookie = new Cookie(name, encode(value));
            setCookieProperty(request, cookie);
            cookie.setMaxAge(iValidSecond);
            response.addCookie(cookie);
        }
        catch (Exception ex)
        {
            log.error(ExceptionUtils.getStackTrace(ex));
        }
    }
    
    /**
     * 取得服务器域名
     * @param request HttpServletRequest
     * @return String 服务器域名
     */
    public static String getDomain(HttpServletRequest request)
    {
        // String serverName = request.getServerName();
        // int index = serverName.indexOf(DOMAIN);
        // if (index > 0)
        // {
        // serverName = StringUtils.substring(serverName, index);
        // }
        // return serverName;
        return request.getServerName();
    }
    
    /**
     * Cookie路径及域名设置
     * @param request HttpServletRequest
     * @param cookie Cookie
     */
    public static void setCookieProperty(HttpServletRequest request, Cookie cookie)
    {
        if (null != cookie)
        {
            cookie.setDomain(getDomain(request));// 二级及多级子域名共享Cookie
            cookie.setPath("/");
        }
    }
    
    /**
    * get cookie from client
    * @param request HttpServletRequest
    * @param name 要取值的cookie名称
    * @return String cookie的值
    */
    public static String get(HttpServletRequest request, String name)
    {
        Cookie[] cookies = readAll(request);
        if (cookies == null)
        { return ""; }
        String result = "";
        try
        {
            for (Cookie cookie : cookies)
            {
                setCookieProperty(request, cookie);
                if (cookie.getName().equals(name))
                {
                    result = cookie.getValue();
                    break;
                }
            }
        }
        catch (Exception ex)
        {
            log.error(ExceptionUtils.getStackTrace(ex));
        }
        return decode(result);
    }
    
    /**
    * 清除Cookie
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    * @param name String
    */
    public static void remove(HttpServletRequest request, HttpServletResponse response, String name)
    {
        put(request, response, name, null, 0);
    }
    
    /**
    * 清除所有的Cookie
    * @param request HttpServletRequest
    * @param response HttpServletResponse
    */
    public static void removeAll(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = readAll(request);
        if (cookies != null)
        {
            try
            {
                for (Cookie ck : cookies)
                {
                    Cookie cookie = new Cookie(ck.getName(), null);
                    setCookieProperty(request, cookie);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            catch (Exception ex)
            {
                log.error(ExceptionUtils.getStackTrace(ex));
            }
        }
    }
    
    /**
    * 取得所有Cookie
    * @param request HttpServletRequest
    * @return Cookie[] 所有Cookie
    */
    public static Cookie[] readAll(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        return cookies;
    }
    
    /**
    * 对给定字符进行 URL 编码
    * @param value String
    * @return String
    */
    private static String encode(String value)
    {
        String result = "";
        value = StringUtils.trim(value);
        if (StringUtils.isNotEmpty(value))
        {
            try
            {
                result = URLEncoder.encode(value, "UTF-8");
            }
            catch (UnsupportedEncodingException ex)
            {
                log.error(ExceptionUtils.getStackTrace(ex));
            }
        }
        return result;
    }
    
    /**
    * 对给定字符进行 URL 解码
    * @param value String
    * @return String
    */
    private static String decode(String value)
    {
        String result = "";
        value = StringUtils.trim(value);
        if (StringUtils.isNotEmpty(value))
        {
            try
            {
                result = URLDecoder.decode(value, CharsetConst.CHARSET_UT);
            }
            catch (UnsupportedEncodingException ex)
            {
                log.error(ExceptionUtils.getStackTrace(ex));
            }
        }
        return result;
    }
    
    /**
    * 判断cookie中制定条目是否存在
    * @param request
    * @param key 要取值的cookie名称
    * @return
    */
    public static boolean isExists(HttpServletRequest request, String key)
    {
        Cookie[] cookies = readAll(request);
        if (null != cookies)
        {
            for (Cookie cookie : cookies)
            {
                setCookieProperty(request, cookie);
                if (cookie.getName().equals(key))
                { return true; }
            }
        }
        return false;
    }
}
