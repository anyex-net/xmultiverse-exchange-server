package com.anyex.apps.interceptor;

import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.exception.LimitAccessException;
import com.anyex.apps.utils.NetworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * <p>File：IPLimitHandlerInterceptor</p>
 * <p>Title: IPLimitHandlerInterceptor</p>
 * <p>Description: IPLimitHandlerInterceptor</p>
 * <p>Copyright: Copyright (c) 2014 2014/3/22 10:52</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class IPLimitHandlerInterceptor extends HandlerInterceptorAdapter
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /**
     * 限制一个IP在同一秒内对一个接口最大只能调用10次
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
//        if (!request.getMethod().equalsIgnoreCase(GlobalConst.POST))
//        {// 非POST请求直接放行
//            return true;
//        }
        String ip = NetworkUtils.getIpAddr(request);
        String sessionId = request.getSession().getId();
        StringBuilder key = new StringBuilder("limit").append(GlobalConst.SEPARATOR).append(ip);
        key.append(GlobalConst.SEPARATOR).append(sessionId);
        long count = stringRedisTemplate.opsForValue().increment(key.toString(), 1);
        if (count == 1)
        {// 初始化
            stringRedisTemplate.expire(key.toString(), 1, TimeUnit.SECONDS);
        }
        if (GlobalConst.DEFAULT_REQUEST_LIMIT < count)
        {// 请求过于频繁,超出限制
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Overshoot the maximum limit!");
//            return false;
            log.error(ip + " Overshoot the maximum limit " + GlobalConst.DEFAULT_REQUEST_LIMIT + "/s");
            throw new LimitAccessException("Overshoot the maximum limit!");
        }
        return true;
    }
}
