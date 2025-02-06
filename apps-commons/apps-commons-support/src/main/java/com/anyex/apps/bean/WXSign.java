package com.anyex.apps.bean;

import com.anyex.apps.utils.*;
import com.google.common.collect.Maps;
import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * WXSign
 * <p>File：WXSign.java</p>
 * <p>Title: WXSign</p>
 * <p>Description: WXSign</p>
 * <p>Copyright: Copyright (c) 2019/10/24</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class WXSign
{
    @Autowired
    private GlobalProperies properies;
    
    private static final String CACHE_KEY = new StringBuffer(CacheConst.WEIXIN_TICKET_PERFIX)          // 加入缓存前缀
            .append(GlobalConst.SEPARATOR).append("|WeixinCache").toString();
    
    /**
     * 获取ticker
     *
     * @return
     */
    public String getJsapiTicket()
    {
        String cache_ticket = (String) RedisUtils.getObject(CACHE_KEY);
        if (StringUtils.isBlank(cache_ticket))
        {
            log.info("微信缓存ticker过期, 重新加载, 加载时间:" + CalendarUtils.dateFormat(new Date(), "yyyy-MM-dd hh:ss:mm"));
            String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
            Map<String, String> params = Maps.newLinkedHashMap();
            params.put("grant_type", "client_credential");
            params.put("appid", properies.getWeixin().getAppKey());
            params.put("secret", properies.getWeixin().getSecret());
            String result = HttpUtils.get(requestUrl, params);
            String access_token = JSONUtils.jsonToMap(result).get("access_token").toString();
            if (StringUtils.isEmpty(access_token))
            {
                log.info("微信请求签名失败, result: " + result);
                return null;
            }
            requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
            params = Maps.newLinkedHashMap();
            params.put("access_token", access_token);
            params.put("type", "jsapi");
            result = HttpUtils.get(requestUrl, params);
            String jsapi_ticket = JSONUtils.jsonToMap(result).get("ticket").toString();
            String expires_in = JSONUtils.jsonToMap(result).get("expires_in").toString();
            if (StringUtils.isAnyEmpty(jsapi_ticket, expires_in))
            {
                log.info("微信请求签名失败, result: " + result);
                return null;
            }
            // 提前5分钟获取ticket
            Integer expiredTime = Integer.parseInt(expires_in) - CacheConst.DEFAULT_CACHE_TIME;
            RedisUtils.putObject(CACHE_KEY, jsapi_ticket, expiredTime);
            cache_ticket = jsapi_ticket;
        }
        return cache_ticket;
    }
    
    /**
     * URL签名
     * @return
     */
    public Map<String, String> urlSign(String url)
    {
        Map<String, String> ret = Maps.newHashMap();
        try
        {
            String jsapi_ticket = getJsapiTicket();
            String nonce_str = create_nonce_str();
            String timestamp = create_timestamp();
            // 注意这里参数名必须全部小写，且必须有序
            String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            String signature = byteToHex(crypt.digest());
            ret.put("url", url);
            ret.put("jsapi_ticket", jsapi_ticket);
            ret.put("nonceStr", nonce_str);
            ret.put("timestamp", timestamp);
            ret.put("signature", signature);
            ret.put("appId", properies.getWeixin().getAppKey());
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error("NoSuchAlgorithmException: " + e.getLocalizedMessage());
        }
        catch (UnsupportedEncodingException e)
        {
            log.error("UnsupportedEncodingException: " + e.getLocalizedMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("Exception: " + e.getLocalizedMessage());
        }
        return ret;
    }
    
    /**
     * 取所有请求参数,拼接URL
     *
     * @param request
     * @return
     */
    protected String getUrlParameters(HttpServletRequest request)
    {
        StringBuffer sb = new StringBuffer();
        Enumeration<String> parameterNames = request.getParameterNames();
        String parameterName;
        while (parameterNames.hasMoreElements())
        {
            parameterName = parameterNames.nextElement();
            if (sb.length() > 0) sb.append("&");
            sb.append(parameterName + "=" + request.getParameter(parameterName));
        }
        String result = sb.toString();
        if (StringUtils.isNotEmpty(result))
        {
            result = "?" + result;
        }
        return result;
    }
    
    private String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
    private String create_nonce_str()
    {
        return UUID.randomUUID().toString();
    }
    
    private String create_timestamp()
    {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
