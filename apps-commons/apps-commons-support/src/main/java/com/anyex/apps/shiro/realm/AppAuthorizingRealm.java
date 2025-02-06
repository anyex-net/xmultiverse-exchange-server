package com.anyex.apps.shiro.realm;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.enums.LoginEnums;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.shiro.UserCredentialsMatcher;
import com.anyex.apps.shiro.WebSessionManager;
import com.anyex.apps.shiro.model.AccountToken;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.shiro.session.RedisSessionDAO;
import com.anyex.apps.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import lombok.Setter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import java.util.Collection;

/**
 * <p>File：AppAuthorizingRealm.java </p>
 * <p>Title: 系统安全认证实现类 </p>
 * <p>Description: AppAuthorizingRealm </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 15:42</p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
@Slf4j
@Setter
public class AppAuthorizingRealm extends AuthorizingRealm
{
    private AccountService accountService;

    private SysMsgRecordService msgRecordService;

//    private OauthClient oauthClient;

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
        log.info("accountToken:{}", token);
        if (LoginEnums.EMAIL.getCode().equals(token.getLoginType()))
        { // 验证码登录
            Account account = accountService.findByEmail(token.getUsername());
            if (null == account) throw new UnknownAccountException("The account does not exist!");
            if (!account.verifySignature()) throw new LockedAccountException("The account data check is abnormal!");
            if (!EncryptUtils.validatePassword(String.valueOf(token.getPassword()), account.getLoginPwd()))
            {// 密码连续错误
                throw new IncorrectCredentialsException("The account or password is incorrect!");
            }
//            StringBuffer mobileNo = new StringBuffer(account.getCountry()).append(account.getMobile());
//            if (!msgRecordService.validSMSCode(mobileNo.toString(), String.valueOf(token.getAuthCode()), MessageConst.SMS_VALID_LOGIN))
//            {// 短信验证，失败后直接将异常抛出
//                throw new BusinessException(CommonEnums.ERROR_SMSCODE_VALID_FAILED);
//            }
            cleanOtherUsers(account.getId());
            UserPrincipal userPrincipal = new UserPrincipal(account.getId(), account.getAccountName(),
                    account.getAccountName(), account.getEmail(), account.getMobile());
            log.info("userPrincipal:{}", userPrincipal);
            return new SimpleAuthenticationInfo(userPrincipal, account.getEmail(), getName());
        }
        else if (LoginEnums.SMS.getCode().equals(token.getLoginType()))
        { // 验证码登录
            Account account = accountService.findByMobile(token.getUsername());
            if (null == account) throw new UnknownAccountException("账户不存在！");
            if (!account.verifySignature()) throw new LockedAccountException("账户数据检验异常！");
//            StringBuffer mobileNo = new StringBuffer(account.getCountry()).append(account.getMobile());
//            if (!msgRecordService.validSMSCode(mobileNo.toString(), String.valueOf(token.getAuthCode()), MessageConst.SMS_VALID_LOGIN))
//            {// 短信验证，失败后直接将异常抛出
//                throw new BusinessException(CommonEnums.ERROR_SMSCODE_VALID_FAILED);
//            }
            cleanOtherUsers(account.getId());
            UserPrincipal userPrincipal = new UserPrincipal(account.getId(), account.getAccountName(),
                    account.getAccountName(), account.getEmail(), account.getMobile());
            log.info("userPrincipal:{}", userPrincipal);
            return new SimpleAuthenticationInfo(userPrincipal, account.getEmail(), getName());
        }
//        else if (LoginEnums.PASS.getCode().equals(token.getLoginType()))
//        { // 密码登录
//            Account account = accountService.findByName(token.getUsername());
//            if (null == account) throw new UnknownAccountException("用户不存在！");
//            if (!StatusEnums.NORMAL.getCode().equals(account.getStatus()) || !account.verifySignature())
//            {// 数据检验异常
//                throw new LockedAccountException("数据检验异常！");
//            }
//            if (!EncryptUtils.validatePassword(String.valueOf(token.getPassword()), account.getLoginPwd()))
//            {// 密码连续错误
//                throw new IncorrectCredentialsException("用户密码错误!");
//            }
//            cleanOtherUsers(account.getId());
//            UserPrincipal userPrincipal = new UserPrincipal(account.getId(), account.getAccountName(),
//                    account.getAccountName(), null);
//            return new SimpleAuthenticationInfo(userPrincipal, account.getLoginPwd(), getName());
//        }
//        else if (LoginEnums.WEIXIN.getCode().equals(token.getLoginType()))
//        {// 微信登录
//            WeiXinCallback callback = oauthClient.getWXAccessToken(token.getAuthCode());
//            WeiXinCallback WXUserInfo = oauthClient.getWXUserInfo(callback.getAccessToken(), callback.getOpenid());
//            Thirdparty thirdparty = thirdpartyService.findByOpenId(WXUserInfo.getUnionid());
//            if (null == thirdparty) throw new AccountPolicyException("微信未绑定，请先绑定！");
//            Account account = accountService.selectByPrimaryKey(thirdparty.getAccountId());
//            cleanOtherUsers(account.getId());
//            UserPrincipal userPrincipal = new UserPrincipal(account.getId(), account.getAccountName(),
//                    account.getAccountName(), null);
//            return new SimpleAuthenticationInfo(userPrincipal, account.getLoginPwd(), getName());
//        }
//        else if (LoginEnums.QQ.getCode().equals(token.getLoginType()))
//        { // QQ登录
//            QQCallback qqToken = oauthClient.getQQAccessToken(token.getAuthCode());
//            QQCallback qqAuth = oauthClient.getQQOpenId(qqToken.getAccessToken());
//            Thirdparty thirdparty = thirdpartyService.findByOpenId(qqAuth.getOpenid());
//            if (null == thirdparty) throw new AccountPolicyException("QQ未绑定，请先绑定！");
//            Account account = accountService.selectByPrimaryKey(thirdparty.getAccountId());
//            cleanOtherUsers(account.getId());
//            UserPrincipal userPrincipal = new UserPrincipal(account.getId(), account.getAccountName(),
//                    account.getAccountName(), null);
//            return new SimpleAuthenticationInfo(userPrincipal, account.getLoginPwd(), getName());
//        }
//        else if (LoginEnums.WEIBO.getCode().equals(token.getLoginType()))
//        {// 微博登录
//            WeiboCallback callback = oauthClient.getWBAccessToken(token.getAuthCode());
//            Thirdparty thirdparty = thirdpartyService.findByOpenId(callback.getUid());
//            if (null == thirdparty) throw new AccountPolicyException("微博未绑定，请先绑定！");
//            Account account = accountService.selectByPrimaryKey(thirdparty.getAccountId());
//            cleanOtherUsers(account.getId());
//            UserPrincipal userPrincipal = new UserPrincipal(account.getId(), account.getAccountName(),
//                    account.getAccountName(), null);
//            return new SimpleAuthenticationInfo(userPrincipal, account.getLoginPwd(), getName());
//        }
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
     * @param accountId
     */
    void cleanOtherUsers(Long accountId)
    {
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        WebSessionManager sessionManager = (WebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
        for (Session session : sessions)
        {
            if(null != session.getAttribute(RedisSessionDAO.PRINCIPAL_ID)){
                if (accountId.equals(Long.valueOf(String.valueOf(session.getAttribute(RedisSessionDAO.PRINCIPAL_ID)))))
                { // 清除该用户以前登录时保存的session
                    log.info("清除该用户以前登录时保存的session:{}", session);
                    sessionManager.getSessionDAO().delete(session);
                }
            }
        }
    }
}
