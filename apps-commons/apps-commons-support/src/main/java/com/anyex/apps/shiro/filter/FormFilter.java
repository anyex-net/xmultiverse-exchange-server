package com.anyex.apps.shiro.filter;

import com.anyex.apps.bean.EnumDescribable;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.JSONUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 改变SHIRO在未登录时的处理方式
 * <p>File: CustomFormAuthenticationFilter.java </p>
 * <p>Title: CustomFormAuthenticationFilter </p>
 * <p>Description: CustomFormAuthenticationFilter </p>
 * <p>Copyright: Copyright (c) 2019-05-22</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class FormFilter extends FormAuthenticationFilter
{
    /**
     * 如果isAccessAllowed返回false 则执行onAccessDenied
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
    {
        if (request instanceof HttpServletRequest)
        {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS"))
            { return true; }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
    
    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     *
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 这里是个坑，如果不设置的接受的访问源，那么前端都会报跨域错误，因为这里还没到corsConfig里面
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        JsonMessage json = getJsonMessage(CommonEnums.USER_NOT_LOGIN);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().write(JSONUtils.beanToJson(json));
        return false;
    }
    
    /**
     * 接口处理结果反馈
     *
     * @param describable 异常代码描述
     * @return {@link JsonMessage}
     * @author Playguy
     */
    protected JsonMessage getJsonMessage(EnumDescribable describable)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(describable.getCode());
        jsonMessage.setMessage(describable.getMessage());
        jsonMessage.setTimestamp(System.currentTimeMillis());
        return jsonMessage;
    }
}
