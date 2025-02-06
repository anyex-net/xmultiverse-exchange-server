//package com.anyex.apps.shiro.realm;
//
//import com.anyex.apps.shiro.UserCredentialsMatcher;
//import com.auth0.jwt.exceptions.SignatureVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.anyex.apps.common.service.MsgRecordService;
//import com.anyex.apps.enums.CommonEnums;
//import com.anyex.apps.exception.AccountPolicyException;
//import com.anyex.apps.shiro.model.JwtToken;
//import com.anyex.apps.shiro.model.UserPrincipal;
//import com.anyex.apps.shiro.utils.JwtUtil;
//import com.anyex.apps.system.entity.UserInfo;
//import com.anyex.apps.system.service.UserInfoService;
//import com.anyex.apps.utils.RedisUtils;
//import com.anyex.apps.utils.StringUtils;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authc.credential.CredentialsMatcher;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//
///**
// * AdminAppAuthorizingRealm
// * <p>File：AdminAppAuthorizingRealm.java</p>
// * <p>Title: AdminAppAuthorizingRealm</p>
// * <p>Description: AdminAppAuthorizingRealm</p>
// * <p>Copyright: Copyright (c) 2019/10/23</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Slf4j
//@Setter
//public class AdminAppAuthorizingRealm extends AuthorizingRealm
//{
//    private UserInfoService userInfoService;
//
//    private MsgRecordService msgRecordService;
//
//    /**
//     * 必须重写此方法，不然Shiro会报错
//     * @param token
//     * @return
//     */
//    @Override
//    public boolean supports(AuthenticationToken token)
//    {
//        return token instanceof JwtToken;
//    }
//
//    /**
//     * 添加自定义认证器
//     *
//     * @param credentialsMatcher
//     */
//    @Override
//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher)
//    {
//        super.setCredentialsMatcher(new UserCredentialsMatcher());
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException
//    {
//        String token = (String) auth.getCredentials();
//        if(StringUtils.isEmpty(token)){
//            log.error("token为空");
//            throw new AuthenticationException("账户未登录!");
//        }
//        // 解密获得username，用于和数据库进行对比
//        String username = JwtUtil.getUsername(token);
//        if (StringUtils.isBlank(username))
//        {
//            log.error("token无效(空''或者null都不行!)");
//            throw new AuthenticationException("token无效！");
//        }
//        UserInfo userInfo = userInfoService.findByUserName(username);
//        if (null == userInfo)
//        {
//            log.error("用户" + username + "不存在！");
//            throw new UnknownAccountException("用户不存在！");
//        }
//        // JWT token认证
//        try
//        {
//            JwtUtil.verify(token, username, userInfo.getPassWord());
//        }
//        catch (SignatureVerificationException e)
//        {
//            log.error("token SignatureVerificationException:{}", e.getLocalizedMessage());
//            throw new AccountPolicyException(e.getLocalizedMessage());
//        }
//        catch (TokenExpiredException e)
//        {
//            log.error("token TokenExpiredException:{}", e.getLocalizedMessage());
//            // 允许一段时间有效时间同时返回新的token 这里需要优化 防止高并发
//            // String newToken = JwtUtil.sign(username, account.getLoginPwd());
//            // log.info("Subject: [" + username + "] token expired, allow get new token [" + newToken + "]");
//            // throw new AccountPolicyException(CommonEnums.ERROR_SESSION_TIME_OUT2.getCode(), "token has expired", newToken);
//            throw new AccountPolicyException(CommonEnums.ERROR_SESSION_TIME_OUT2.getCode(), CommonEnums.ERROR_SESSION_TIME_OUT.getMessage());
//        }
//        catch (Exception e)
//        {
//            log.error("token Exception:{}", e.getLocalizedMessage());
//            throw new AccountPolicyException(e.getLocalizedMessage());
//        }
//
//        cleanOtherUsers(userInfo.getId(), token);
//
//        UserPrincipal userPrincipal = new UserPrincipal(userInfo.getId(), userInfo.getUserName(), null, null, null);
//        return new SimpleAuthenticationInfo(userPrincipal, userInfo.getPassWord(), getName());
//    }
//
//    /**
//     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
//     *
//     * @param principals
//     * @return {@link AuthenticationInfo}
//     * @throws AuthenticationException
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
//    {
//        return new SimpleAuthorizationInfo();
//    }
//
//    /**
//     * 清理其它用户，保障一个帐户同时只能登录一个
//     * @param userId
//     */
//    void cleanOtherUsers(Long userId, String jwtToken)
//    {
//        //log.info("cleanOtherUsers userId:{}, jwtToken:{}", userId, jwtToken);
//        String jwtTokenInRedis = RedisUtils.get("adiapp:access_token:userid_" + userId );
//        if(!jwtToken.equals(jwtTokenInRedis)){
//            log.error("cleanOtherUsers error:该账号已经在其他地方登录，本会话已过期！userId:{}", userId);
//            throw new AccountPolicyException(CommonEnums.ERROR_SESSION_TIME_OUT2.getCode(), CommonEnums.ERROR_SESSION_TIME_OUT.getMessage());
//        }
//    }
//}
