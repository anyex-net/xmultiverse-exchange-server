package com.anyex.apps.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 自定义会话管理器
 * <p>File: CustomWebSessionManager.java </p>
 * <p>Title: CustomWebSessionManager </p>
 * <p>Description: CustomWebSessionManager </p>
 * <p>Copyright: Copyright (c) 2019-05-20</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class WebSessionManager extends DefaultWebSessionManager
{
    private String sessionId;
    
    public WebSessionManager(String sessionId)
    {
        this.sessionId = sessionId;
    }
    
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response)
    {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String sid = httpServletRequest.getHeader(sessionId);
        if (!StringUtils.isEmpty(sid))
        {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sid);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sid;
        }
        return super.getSessionId(request, response);
    }
}
