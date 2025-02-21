package com.anyex.apps.shiro.realm;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.exception.UserPolicyException;
import com.anyex.apps.shiro.UserCredentialsMatcher;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.shiro.model.UserToken;
import com.anyex.apps.shiro.session.RedisSessionDAO;
import com.anyex.apps.user.consts.UserConsts;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserPolicyService;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.EncryptUtils;
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
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import java.util.Collection;

/**
 * <p>File：WebUserAuthorizingRealm.java </p>
 * <p>Title: 系统安全认证实现类 </p>
 * <p>Description: WebUserAuthorizingRealm </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 15:42</p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
@Slf4j
@Setter
public class WebUserAuthorizingRealm extends AuthorizingRealm
{
    private UserService         userService;

    private UserPolicyService   userPolicyService;
    
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
        // 用户名密码登录模式
        if(StringUtils.isNotEmpty(token.getUsername())){
            String accountName = token.getUsername().toLowerCase();
            User user = userService.findByUserNameAndNormal(accountName, token.getCountry());
            if (null == user)
            { throw new UnknownAccountException("User not exists!"); }
            if (user.getState().intValue() != UserConsts.USER_STATUS_NORMAL.intValue() || !user.verifySignature())
            { // 加入冻结的用户筛选
                throw new LockedAccountException("User data error!");
            }
            if (!EncryptUtils.validatePassword(String.valueOf(token.getPassword()), user.getLoginPwd()))
            {// 密码连续错误
                throw new IncorrectCredentialsException("User password error!");
            }
            if (user.getSecurityPolicy() > UserConsts.SECURITY_POLICY_DEFAULT)
            {// 说明用户启用了其它安全验证策略
                try
                {
                    userPolicyService.validSecurityPolicy(user, token.getPolicy());
                }
                catch (BusinessException e)
                {
                    token.setId(user.getId());
                    token.setGa(null != user.getGaAuthKey());
                    token.setPhone(null != user.getMobileNo());
                    token.setLevel(user.getSecurityPolicy());
                    token.setMobileNo(user.getMobileNo());
                    throw new UserPolicyException(e.getError(), e.getMessage());
                }
            }
            cleanOtherUsers(String.valueOf(user.getId()));
            UserPrincipal userPrincipal = new UserPrincipal(user.getId(), user.getUid(), user.getUserName(), user.getUserName(), user.getMobileNo(),
                    user.getEmail(), user.getLang(), user.getCountry(), user.getGaAuthKey());
            return new SimpleAuthenticationInfo(userPrincipal, user.getLoginPwd(), getName());
        }
        // 唯一二维码串登录
        else {
            User user = userService.selectByPrimaryKeyNoCheck(token.getId());
            if (null == user)
            { throw new UnknownAccountException("User not exists!"); }
            if (user.getState().intValue() != UserConsts.USER_STATUS_NORMAL.intValue() || !user.verifySignature())
            { // 加入冻结的用户筛选
                throw new LockedAccountException("User data error!");
            }
            if (user.getSecurityPolicy() > UserConsts.SECURITY_POLICY_DEFAULT)
            {// 说明用户启用了其它安全验证策略
                try
                {
                    userPolicyService.validSecurityPolicy(user, token.getPolicy());
                }
                catch (BusinessException e)
                {
                    token.setId(user.getId());
                    token.setGa(null != user.getGaAuthKey());
                    token.setPhone(null != user.getMobileNo());
                    token.setLevel(user.getSecurityPolicy());
                    token.setMobileNo(user.getMobileNo());
                    throw new UserPolicyException(e.getError().getCode(), e.getMessage(), token);
                }
            }
            //
            cleanOtherUsers(String.valueOf(user.getId()));
            UserPrincipal userPrincipal = new UserPrincipal(user.getId(), user.getUid(), user.getUserName(), user.getUserName(), user.getMobileNo(),
                    user.getEmail(), user.getLang(), user.getCountry(), user.getGaAuthKey());
            return new SimpleAuthenticationInfo(userPrincipal, user.getLoginPwd(), getName());
        }
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
        return new SimpleAuthorizationInfo();
    }
    
    /**
     * 清理其它用户，保障一个帐户同时只能登陆一个
     * @param accountId
     */
    void cleanOtherUsers(String accountId)
    {
        // 处理session
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();// 获取当前已登录的用户session列表
        for (Session session : sessions)
        {// 清除该用户以前登录时保存的session
            if (null != session.getAttribute(RedisSessionDAO.PRINCIPAL_ID))
            { // 空指针处理
                String principalId = (String) session.getAttribute(RedisSessionDAO.PRINCIPAL_ID);
                if (null != principalId && accountId.equals(principalId))
                { // 清除该用户以前登录时保存的session
                    sessionManager.getSessionDAO().delete(session);
                }
            }
        }
    }
    
    /**
     * 添加自定义认证器
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher)
    {
        super.setCredentialsMatcher(new UserCredentialsMatcher());
    }
}
