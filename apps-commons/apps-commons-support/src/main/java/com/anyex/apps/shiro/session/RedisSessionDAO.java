package com.anyex.apps.shiro.session;

import com.anyex.apps.consts.GlobalConst;
import com.google.common.collect.Lists;
import com.anyex.apps.shiro.ShiroSessionManager;
import com.anyex.apps.shiro.model.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>File：RedisSessionDAO.java </p>
 * <p>Title:  自定义授权会话实现类 </p>
 * <p>Description: RedisSessionDAO </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 09:52</p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
@Slf4j
public class RedisSessionDAO extends AbstractSessionDAO
{
    // 凭证ID
    public static final String  PRINCIPAL_ID = "principalId";
    
    public String               sessionPrefix;
    
    private ShiroSessionManager shiroSessionManager;
    
    public void setShiroSessionManager(ShiroSessionManager shiroSessionManager)
    {
        this.shiroSessionManager = shiroSessionManager;
    }
    
    public void setSessionPrefix(String sessionPrefix)
    {
        this.sessionPrefix = sessionPrefix;
    }
    
    @Override
    public void update(Session session) throws UnknownSessionException
    {
        log.debug("更新sessionId:{}", session.getId());
        if (session == null || session.getId() == null)
        { return; }
        try
        {
            String key = new StringBuffer(sessionPrefix).append(GlobalConst.SEPARATOR).append(session.getId()).toString();
            // 设置超期时间
            int timeoutSeconds = (int) (session.getTimeout() / 1000);
            if (null == session.getAttribute(PRINCIPAL_ID))
            {// 获取登录者编号
                UserPrincipal principal = null;
                SimplePrincipalCollection collection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (null != collection)
                {
                    principal = (UserPrincipal) collection.getPrimaryPrincipal();
                }
                if (null != principal)
                {
                    Long principalId = principal != null ? principal.getId() : null;
                    session.setAttribute(PRINCIPAL_ID, String.valueOf(principalId));
                }
            }
            shiroSessionManager.putObject(key, session, timeoutSeconds);
        }
        catch (Exception e)
        {
            log.error("update {} {}", session.getId(), e);
        }
    }
    
    @Override
    public void delete(Session session)
    {
        if (session == null || session.getId() == null)
        { return; }
        try
        {
            String key = new StringBuffer(sessionPrefix).append(GlobalConst.SEPARATOR).append(session.getId()).toString();
            shiroSessionManager.del(key);
            log.info("删除sessionId:{}", session.getId());
        }
        catch (Exception e)
        {
            log.error("delete {} ", session.getId(), e);
        }
    }
    
    @Override
    public Collection<Session> getActiveSessions()
    {
        List<Session> value = Lists.newArrayList();
        try
        {
            StringBuffer buffer = new StringBuffer(sessionPrefix).append("*");
            Set<String> keys = shiroSessionManager.getKeys(buffer.toString());
            if (null != keys && keys.size() > 0)
            {
                for (String key : keys)
                {
                    value.add((Session) shiroSessionManager.getObject(key));
                }
            }
            log.debug("getActiveSessions {} ", keys.size());
        }
        catch (Exception e)
        {
            log.error("getActiveSessions {} ", e);
        }
        return value;
    }
    
    @Override
    protected Serializable doCreate(Session session)
    {
        Serializable sessionId = this.generateSessionId(session);
        log.info("创建sessionId:{}", sessionId);
        this.assignSessionId(session, sessionId);
        return sessionId;
    }
    
    @Override
    protected Session doReadSession(Serializable sessionId)
    {
        Session session = null;
        try
        {
            String key = new StringBuffer(sessionPrefix).append(GlobalConst.SEPARATOR).append(sessionId).toString();
            session = (Session) shiroSessionManager.getObject(key);
        }
        catch (Exception e)
        {
            log.error("doReadSession {} {}", sessionId, e);
        }
        return session;
    }
}