package com.anyex.apps.shiro.realm;

import com.anyex.apps.exception.AccountPolicyException;
import com.anyex.apps.google.Authenticator;
import com.anyex.apps.shiro.UserCredentialsMatcher;
import com.anyex.apps.shiro.WebSessionManager;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.shiro.model.UserToken;
import com.anyex.apps.shiro.session.RedisSessionDAO;
import com.anyex.apps.system.entity.SysResources;
import com.anyex.apps.system.entity.SysRoleInfo;
import com.anyex.apps.system.entity.SysUserInfo;
import com.anyex.apps.system.service.SysResourcesService;
import com.anyex.apps.system.service.SysRoleInfoService;
import com.anyex.apps.system.service.SysUserInfoService;
import com.anyex.apps.utils.EncryptUtils;
import com.anyex.apps.utils.ListUtils;
import com.anyex.apps.utils.StringUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import java.util.Collection;
import java.util.List;

/**
 * <p>File：AdminAuthorizingRealm.java </p>
 * <p>Title: 系统安全认证实现类 </p>
 * <p>Description: AdminAuthorizingRealm </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 15:42</p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
@Slf4j
@Setter
public class AdminAuthorizingRealm extends AuthorizingRealm
{
    private SysUserInfoService userInfoService;

    private SysRoleInfoService roleInfoService;

    private SysResourcesService resourcesService;

    /**
     * 添加自定义认证器
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher)
    {
        super.setCredentialsMatcher(new UserCredentialsMatcher());
    }

    /**
     * 认证回调函数, 登录时调用
     * <p>
     *  一次性将用户认证、操作权限等信息放到用户会话中
     * </p>
     * @param authToken
     * @return {@link AuthenticationInfo}
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException
    {
        UserToken token = (UserToken) authToken;
        log.info("token.getUsername():{}", token.getUsername());
        if (StringUtils.isBlank(token.getUsername())) throw new UnknownAccountException("用户不存在！");
        SysUserInfo userInfo = userInfoService.findByUserName(token.getUsername());
        log.info("userInfo:{}", userInfo);
        if (null == userInfo) throw new UnknownAccountException("用户不存在！");
        if (!userInfo.getActive())
        {
            throw new AuthenticationException("该用户已被禁用！");
        }
        if (!EncryptUtils.validatePassword(String.valueOf(token.getPassword()), userInfo.getPassWord()))
        {//
            throw new AuthenticationException("密码错误！");
        }
        if (StringUtils.isNotBlank(userInfo.getAuthKey()))
        {// 绑定过GA的用户强制进行GA码校验
            if (!validGaCode(userInfo.getAuthKey(), token.getGaCode()))
            {// GA验证不通过后直接执行
                token.setId(userInfo.getId());
                throw new AccountPolicyException("policy valid error!");
            }
        }
        List<SysRoleInfo> roles = roleInfoService.findByUserId(userInfo.getId());
        if (ListUtils.isNotNull(roles))
        {
            for (SysRoleInfo role : roles)
            {
                List<SysResources> resources = resourcesService.findByRoleId(role.getId());
                log.info("resources:{}", resources);
                role.setResources(resources);
            }
        }

        //cleanOtherUsers(userInfo.getId());

        UserPrincipal userPrincipal = new UserPrincipal(userInfo.getId(), userInfo.getUserName(), userInfo.getTrueName(), roles);
        return new SimpleAuthenticationInfo(userPrincipal, userInfo.getPassWord(), getName());
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     *
     * @param principals
     * @return {@link AuthenticationInfo}
     * @throws AuthenticationException
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        UserPrincipal principal = (UserPrincipal) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<SysRoleInfo> roles = principal.getRoles();
        if (ListUtils.isNotNull(roles))
        {
            for (SysRoleInfo role : roles)
            {
                authorizationInfo.addRole(role.getRoleCode());
                List<SysResources> resources = role.getResources();
                if (ListUtils.isNotNull(roles))
                {
                    for (SysResources res : resources)
                    {
                        authorizationInfo.addStringPermission(res.getResCode());
                    }
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 验证GA码
     * @param authKey
     * @param validCode
     * @return
     */
    protected boolean validGaCode(String authKey, String validCode)
    {
        boolean flag = false;
        if (StringUtils.isBlank(authKey) || StringUtils.isBlank(validCode)) return flag;
        Authenticator authenticator = new Authenticator();
        if (authenticator.checkCode(EncryptUtils.desDecrypt(authKey), Long.valueOf(validCode))) flag = true;
        return flag;
    }

    /**
     * 清理其它用户，保障一个帐户同时只能登录一个
     * @param userId
     */
    void cleanOtherUsers(Long userId)
    {
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        WebSessionManager sessionManager = (WebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
        for (Session session : sessions)
        {
            if(null != session.getAttribute(RedisSessionDAO.PRINCIPAL_ID)){
                if (userId.equals(Long.valueOf(String.valueOf(session.getAttribute(RedisSessionDAO.PRINCIPAL_ID)))))
                { // 清除该用户以前登录时保存的session
                    sessionManager.getSessionDAO().delete(session);
                }
            }
        }
    }
}
