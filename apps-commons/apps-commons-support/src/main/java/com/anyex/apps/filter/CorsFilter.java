package com.anyex.apps.filter;

import com.anyex.apps.config.GlobalProperies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CorsFilter
 * <p>File: CorsFilter.java </p>
 * <p>Title: CorsFilter </p>
 * <p>Description: CorsFilter </p>
 * <p>Copyright: Copyright (c) 2018/10/31</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter
{
    @Autowired(required = false)
    private GlobalProperies properies;
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        //response.setHeader("Access-Control-Allow-Origin", properies.getAllowOrigin());
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        //response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", properies.getAllowMethods());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", properies.getMaxAge());
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        //https://tonyxu.io/zh/posts/2018/http-deprecate-x-prefix/
        //Authorization: 常用于传OAuth的Access Token或其他认证信息, 如Authorization: Bearer ABCDEFG
        //https://www.jianshu.com/p/cecb73b26a11
        //抓包查看http请求和响应，发现已允许跨域。说明跨域设置是成功了，只是HTTP Header缺少了一个字段，导致的报错。
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Content-Type,Token,Bid,Sid,Aid");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod()))
        {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else
        {
            chain.doFilter(req, res);
        }
    }
    
    @Override
    public void init(FilterConfig filterConfig)
    {
    }
    
    @Override
    public void destroy()
    {
    }
}
