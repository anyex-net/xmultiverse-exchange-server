package com.anyex.apps.interceptor;

import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.JSONUtils;
import com.anyex.apps.utils.NetworkUtils;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * <p>File：AccessLimitHandlerInterceptor</p>
 * <p>Title: AccessLimitHandlerInterceptor</p>
 * <p>Description: AccessLimitHandlerInterceptor</p>
 * <p>Copyright: Copyright (c) 2014 2021/5/25 10:52</p>
 * <p>Company: AnyEx</p>
 *
 * @author Sun
 * @version 1.0
 */
@Slf4j
@Component
public class AccessLimitHandlerInterceptor extends HandlerInterceptorAdapter
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * 限制一个IP在同M秒内对一个接口最大只能调用N次 包括非登录状态与登录状态两种情况
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            // 拿到注解的内容
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            //
            log.debug("accessLimit:{}", accessLimit);
            if (null == accessLimit) {
                // 没有特殊限流配置就直接全局基于IP限流
                // int ip = NetworkUtils.getIpAddrs(request);
                String ip = NetworkUtils.getIpAddr(request);
                StringBuilder key = new StringBuilder("accessLimit_global_ip").append(GlobalConst.SEPARATOR).append(ip);
                long count = stringRedisTemplate.opsForValue().increment(key.toString(), 1);
                if (count == 1)
                {// 初始化
                    stringRedisTemplate.expire(key.toString(), 1, TimeUnit.SECONDS);
                }
                if (GlobalConst.DEFAULT_REQUEST_LIMIT < count)
                {// 请求过于频繁,超出限制
                    log.error(ip + " Overshoot the maximum limit " + GlobalConst.DEFAULT_REQUEST_LIMIT + "/s");
                    // 改造
                    JsonMessage<Object> jsonMessage = new JsonMessage<Object>();
                    jsonMessage.setCode(CommonEnums.ERROR_OVERSHOOT_MAXIMUM_LIMIT.getCode());
                    jsonMessage.setMessage("Overshoot the maximum limit "+GlobalConst.DEFAULT_REQUEST_LIMIT+"/s");
                    jsonMessage.setTimestamp(System.currentTimeMillis());
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().print(JSONUtils.beanToJson(jsonMessage));
                    //
                    //response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Overshoot the maximum limit "+GlobalConst.DEFAULT_REQUEST_LIMIT+"/s");
                    return false;
//                    throw new LimitAccessException(ip + " Overshoot the maximum limit " + GlobalConst.DEFAULT_REQUEST_LIMIT + "/s");
                }
                //
                return true;
            } else {
                // 基于特殊限流配置限流
                int limit = accessLimit.limit();
                int timeScope = accessLimit.timeScope();
                boolean isLogin = accessLimit.isLogin();
                if(isLogin) {
                    log.debug("accessLimit login:{}", accessLimit);
                    // 需要登录账户就直接基于特殊限流配置进行基于IP、账户的限流
                    // String token = request.getHeader("token");
                    // String token = request.getHeader("sid");
                    if(StringUtils.isEmpty(request.getHeader("token")) && StringUtils.isEmpty(request.getHeader("bid"))
                            && StringUtils.isEmpty(request.getHeader("sid")) && StringUtils.isEmpty(request.getHeader("aid")))
                    {
                        log.error("Access is prohibited when the user is not logged in");
                        // 改造
                        JsonMessage<Object> jsonMessage = new JsonMessage<Object>();
                        jsonMessage.setCode(CommonEnums.ERROR_OVERSHOOT_MAXIMUM_LIMIT.getCode());
                        jsonMessage.setMessage("Access is prohibited when the user is not logged in");
                        jsonMessage.setTimestamp(System.currentTimeMillis());
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().print(JSONUtils.beanToJson(jsonMessage));
                        //
                        //response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Access is prohibited when the user is not logged in");
                        return false;
//                        throw new LimitAccessException("Access is prohibited when the user is not logged in");
                    }
                    // int ip = NetworkUtils.getIpAddrs(request);
                    String ip = NetworkUtils.getIpAddr(request);
                    String requestURI = request.getRequestURI();
//                    StringBuilder key = new StringBuilder("accessLimit_special_ip").append(GlobalConst.SEPARATOR).append(ip).
//                            append(GlobalConst.SEPARATOR).append(token).append(GlobalConst.SEPARATOR).append(requestURI);
                    StringBuilder key = new StringBuilder("accessLimit_special_ip").append(GlobalConst.SEPARATOR).append(ip).
                            append(GlobalConst.SEPARATOR).append(GlobalConst.SEPARATOR).append(requestURI);
                    long count = stringRedisTemplate.opsForValue().increment(key.toString(), 1);
                    if (count == 1)
                    {// 初始化
                        stringRedisTemplate.expire(key.toString(), timeScope, TimeUnit.SECONDS);
                    }
                    if (limit < count)
                    {// 请求过于频繁,超出限制
                        log.error(ip + " Overshoot the maximum limit " + limit + " per " + timeScope + "s");
                        // 改造
                        JsonMessage<Object> jsonMessage = new JsonMessage<Object>();
                        jsonMessage.setCode(CommonEnums.ERROR_OVERSHOOT_MAXIMUM_LIMIT.getCode());
                        jsonMessage.setMessage("Overshoot the maximum limit "+limit+" per "+timeScope+"s");
                        jsonMessage.setTimestamp(System.currentTimeMillis());
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().print(JSONUtils.beanToJson(jsonMessage));
                        //
                        //response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Overshoot the maximum limit "+limit+" per "+timeScope+"s");
                        return false;
//                        throw new LimitAccessException(ip + " Overshoot the maximum limit " + limit + " per " + timeScope + "s");
                    }
                    //
                    return true;
                } else {
                    log.debug("accessLimit not login:{}", accessLimit);
                    // 不需要登录账户就直接基于特殊限流配置进行基于IP的限流
                    // int ip = NetworkUtils.getIpAddrs(request);
                    String ip = NetworkUtils.getIpAddr(request);
                    String requestURI = request.getRequestURI();
                    StringBuilder key = new StringBuilder("accessLimit_special_ip").append(GlobalConst.SEPARATOR).append(ip).
                            append(GlobalConst.SEPARATOR).append(requestURI);
                    long count = stringRedisTemplate.opsForValue().increment(key.toString(), 1);
                    if (count == 1)
                    {// 初始化
                        stringRedisTemplate.expire(key.toString(), timeScope, TimeUnit.SECONDS);
                    }
                    if (limit < count)
                    {// 请求过于频繁,超出限制
                        log.error(ip + " Overshoot the maximum limit " + limit + " per " + timeScope + "s");
                        // 改造
                        JsonMessage<Object> jsonMessage = new JsonMessage<Object>();
                        jsonMessage.setCode(CommonEnums.ERROR_OVERSHOOT_MAXIMUM_LIMIT.getCode());
                        jsonMessage.setMessage("Overshoot the maximum limit "+limit+" per "+timeScope+"s");
                        jsonMessage.setTimestamp(System.currentTimeMillis());
                        response.setCharacterEncoding("utf-8");
                        response.getWriter().print(JSONUtils.beanToJson(jsonMessage));
                        //
                        //response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Overshoot the maximum limit "+limit+" per "+timeScope+"s");
                        return false;
//                        throw new LimitAccessException(ip + " Overshoot the maximum limit " + limit + " per " + timeScope + "s");
                    }
                    //
                    return true;
                }
            }
        }
        //
        return true;
    }
}
