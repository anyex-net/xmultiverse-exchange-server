package com.anyex.apps.shiro.session;

import com.anyex.apps.utils.ServletsUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * <p>File：CacheSessionDAO.java </p>
 * <p>Title: 系统安全认证实现类 </p>
 * <p>Description: CacheSessionDAO </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 09:52</p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
public class CacheSessionDAO extends EnterpriseCacheSessionDAO
{
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    public CacheSessionDAO()
    {
        super();
    }
    
    @Override
    protected void doUpdate(Session session)
    {
        if (session == null || session.getId() == null)
        { return; }
        HttpServletRequest request = ServletsUtils.getRequest();
        super.doUpdate(session);
        logger.debug("update {} {}", session.getId(), request != null ? request.getRequestURI() : "");
    }
    
    @Override
    protected void doDelete(Session session)
    {
        if (session == null || session.getId() == null)
        { return; }
        super.doDelete(session);
        logger.debug("delete {} ", session.getId());
    }
    
    @Override
    protected Serializable doCreate(Session session)
    {
        HttpServletRequest request = ServletsUtils.getRequest();
        super.doCreate(session);
        logger.debug("doCreate {} {}", session, request != null ? request.getRequestURI() : "");
        return session.getId();
    }
    
    @Override
    protected Session doReadSession(Serializable sessionId)
    {
        return super.doReadSession(sessionId);
    }
    
    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException
    {
        try
        {
            HttpServletRequest request = ServletsUtils.getRequest();
            if (request != null)
            {
                Session session = (Session) request.getAttribute("session_" + sessionId);
                if (session != null)
                { return session; }
            }
            Session session = super.readSession(sessionId);
            logger.debug("readSession {} {}", sessionId, request != null ? request.getRequestURI() : "");
            if (request != null && session != null)
            {
                request.setAttribute("session_" + sessionId, session);
            }
            return session;
        }
        catch (UnknownSessionException e)
        {
            return null;
        }
    }
}
