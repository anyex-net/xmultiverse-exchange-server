package com.anyex.apps.shiro.filter;

import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.AccountPolicyException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.JSONUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.apps.shiro.model.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JwtFilter
 * <p>File：JwtFilter.java</p>
 * <p>Title: JwtFilter</p>
 * <p>Description: JwtFilter</p>
 * <p>Copyright: Copyright (c) 2019/10/23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter
{
    public static final String JWT_TOKEN_NAME = "Client-Token"; //"token";

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        JwtToken jwtToken = new JwtToken(getRequestToken(httpServletRequest));
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try
        {
            getSubject(request, response).login(jwtToken);
            // 如果没有抛出异常则代表登入成功，返回true
            return true;
        }
        catch (AccountPolicyException e)
        {
            JsonMessage jsonMessage = new JsonMessage(e.getCode(), e.getLocalizedMessage());
            jsonMessage.setMessage(e.getLocalizedMessage());
            jsonMessage.setData(e.getObject());
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JSONUtils.beanToJson(jsonMessage));
            return false;
        }
        catch (AuthenticationException e)
        {
            JsonMessage jsonMessage = new JsonMessage(CommonEnums.USER_NOT_LOGIN);
            if(e.getLocalizedMessage().equals("用户不存在！")){
                jsonMessage.setCode(999);
            }
            jsonMessage.setMessage(e.getLocalizedMessage());
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JSONUtils.beanToJson(jsonMessage));
            return false;
        }
    }
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
    {
        try
        {
            return executeLogin(request, response);
        }
        catch (Exception e)
        {
            log.error("JwtFilter过滤验证失败:{}", e.getLocalizedMessage());
            return false;
        }
    }
    
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name()))
        {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
    
    /**
     * 取JWT凭证
     * @param request
     * @return
     */
    protected String getRequestToken(HttpServletRequest request)
    {
        String token = request.getHeader(JWT_TOKEN_NAME);
        if (StringUtils.isBlank(token))
        {// 通过参数取
            token = request.getParameter(JWT_TOKEN_NAME);
        }
        //log.info("getRequestToken:{}", token);
        return token;
    }
}
