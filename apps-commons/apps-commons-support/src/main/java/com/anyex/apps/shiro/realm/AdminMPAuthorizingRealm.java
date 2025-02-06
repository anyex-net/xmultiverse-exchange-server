package com.anyex.apps.shiro.realm;

import com.anyex.apps.account.enums.LoginEnums;
import com.anyex.apps.shiro.UserCredentialsMatcher;
import com.anyex.apps.shiro.WebSessionManager;
import com.anyex.apps.shiro.model.AccountToken;
import com.anyex.apps.shiro.session.RedisSessionDAO;
import com.anyex.apps.system.service.SysUserInfoService;
import com.anyex.apps.shiro.auth.OauthClient;
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

/**
 * <p>File：AdminMPAuthorizingRealm.java </p>
 * <p>Title: 系统安全认证实现类 </p>
 * <p>Description: AdminMPAuthorizingRealm </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 15:42</p>
 * <p>Company: AnyEx</p>
 *
 * @author sun
 * @version 1.0
 */
@Slf4j
@Setter
public class AdminMPAuthorizingRealm extends AuthorizingRealm
{
    private SysUserInfoService userInfoService;

    private OauthClient  oauthClient;

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
        AccountToken token = (AccountToken) authToken;
        log.info("doGetAuthenticationInfo accountToken:{}", token);
        if (LoginEnums.ZJDINGMP.getCode().equals(token.getLoginType()))
        {// 浙政钉小程序登录
//            ZjDingAppUserInfo zjDingMpUserInfo = null;
//            try {
//                zjDingMpUserInfo = oauthClient.getZJDINGMPUserInfo(token.getAuthCode());
//                log.info("浙政钉小程序接口返回zjDingMpUserInfo:{}", zjDingMpUserInfo);
//                UserInfo userInfo = userInfoService.findByZZDOpenId(zjDingMpUserInfo.getAccountId());
//                log.info("userInfo:{}", userInfo);
//                //
//                if(null == userInfo) throw new UnknownAccountException("系统关联用户不存在！请联系系统管理员");
//                //
//                // cleanOtherUsers(userInfo.getId());
//                UserPrincipal userPrincipal = new UserPrincipal(userInfo.getId(), userInfo.getUserName(),
//                        userInfo.getZzdName(), userInfo.getUserLogo(), null);
//                return new SimpleAuthenticationInfo(userPrincipal, userInfo.getZzdOpenId(), getName());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        else if (LoginEnums.WEIXINMP.getCode().equals(token.getLoginType()))
        {// 微信小程序登录
//            WeiXinMPCallback WXMPUserInfo = oauthClient.getWXMPUserInfo(token.getAuthCode());
//            log.info("微信小程序接口返回WXMPUserInfo:{}", WXMPUserInfo);
//            AccountInfo accountInfo = new AccountInfo();
//            //
//            //
//            cleanOtherUsers(accountInfo.getId());
//            UserPrincipal userPrincipal = new UserPrincipal(accountInfo.getId(), accountInfo.getAccountName(),
//                    accountInfo.getWeixinName(), accountInfo.getHeadUrl(), null);
//            return new SimpleAuthenticationInfo(userPrincipal, accountInfo.getOpenId(), getName());
        }
        else if (LoginEnums.WEIXIN.getCode().equals(token.getLoginType()))
        {// 微信公众号登录
//            WeiXinCallback callback = oauthClient.getWXAccessToken(token.getAuthCode());
//            WeiXinCallback WXUserInfo = oauthClient.getWXUserInfo(callback.getAccessToken(), callback.getOpenid());
//            log.info("微信公众号接口返回WXUserInfo:{}", WXUserInfo);
//            AccountInfo accountInfo = new AccountInfo();
//            //
//            //
//            cleanOtherUsers(accountInfo.getId());
//            UserPrincipal userPrincipal = new UserPrincipal(accountInfo.getId(), accountInfo.getAccountName(),
//                    accountInfo.getWeixinName(), accountInfo.getHeadUrl(), null);
//            return new SimpleAuthenticationInfo(userPrincipal, accountInfo.getOpenId(), getName());
        }
        return null;
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
