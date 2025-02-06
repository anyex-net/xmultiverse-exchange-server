package com.anyex.apps.shiro.realm;

import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.AccountPolicyException;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.shiro.utils.JwtAkSkUtil;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.StringUtils;
/*import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;*/
import com.anyex.apps.shiro.UserCredentialsMatcher;
import com.anyex.apps.shiro.model.JwtToken;
import com.anyex.apps.shiro.model.UserPrincipal;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

/**
 * ApiAuthorizingRealm
 * <p>File：ApiAuthorizingRealm.java</p>
 * <p>Title: ApiAuthorizingRealm</p>
 * <p>Description: ApiAuthorizingRealm</p>
 * <p>Copyright: Copyright (c) 2019/10/23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Setter
public class ApiAuthorizingRealm extends AuthorizingRealm
{
//    private AccessOrganizationService accessOrganizationService;

    /**
     * 必须重写此方法，不然Shiro会报错
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 添加自定义认证器
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(new UserCredentialsMatcher());
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        log.info("token：" + token);
        if (StringUtils.isEmpty(token)) {
            log.error("token为空");
            throw new AuthenticationException("AK账户未登录!");
        }

//        // JWT token认证
//        AccessOrganizationEntity accessOrganization = null;
//        try {
//            //
//            String clientTokenDecode = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
//            String ak = clientTokenDecode.split(":")[1];
//            String time = clientTokenDecode.split(":")[2];
//            log.info("ak:{}, time:{}", ak, time);
//            // 解密获得ak，用于和数据库进行对比
//            if (StringUtils.isEmpty(ak) || StringUtils.isEmpty(time)) {
//                log.error("token ak || time 无效(空''或者null都不行!)");
//                throw new AuthenticationException("token ak || time 无效！");
//            }
//            // 提高性能使用缓存做判断 否则每次查询数据库很慢
//            accessOrganization = (AccessOrganizationEntity) RedisUtils.getObject("api:accessOrganization:ak_" + ak);
//            if (null == accessOrganization) {
//                AccessOrganizationEntity accessOrganizationEntityQuery = new AccessOrganizationEntity();
//                accessOrganizationEntityQuery.setFormalAk(ak); // "3fb17f91f041427db8326b94f2185d25"
//                log.info("accessOrganizationEntityQuery:{}", accessOrganizationEntityQuery);
//                AccessOrganizationEntity accessOrganizationEntity = accessOrganizationService.selectOne(accessOrganizationEntityQuery);
//                RedisUtils.putObject("api:accessOrganization:ak_" + ak, accessOrganizationEntity, CacheConst.ONE_HOUR_CACHE_TIME);
//                accessOrganization = accessOrganizationEntity; //
//            } else {
//                accessOrganization = (AccessOrganizationEntity) RedisUtils.getObject("api:accessOrganization:ak_" + ak);
//            }
//            // 提高性能使用缓存做判断 否则每次查询数据库很慢
//            if (null == accessOrganization) {
//                log.error("账户" + ak + "不存在！");
//                throw new UnknownAccountException("账户不存在！");
//            }
//            //
//            if (null != accessOrganization) {
//                String clientToken = JwtAkSkUtil.getClientToken(ak, accessOrganization.getFormalSk(), Long.parseLong(time));
//                if (clientToken.equals(token)) {
//                    long timeDuration =  Long.parseLong(time) - new Date().getTime();
//                    log.info("timeDuration:{}", timeDuration);
//                    if (timeDuration / 1000 > 300 || timeDuration < 0) {
//                        log.error("Token is illegal/expired");
//                        throw new BusinessException(601, "Token is illegal/expired");
//                    }
//                } else {
//                    log.error("Token is illegal/expired");
//                    throw new BusinessException(601, "Token is illegal/expired");
//                }
//            } else {
//                log.error("Token is illegal/expired");
//                throw new BusinessException(601, "Token is illegal/expired");
//            }
//        } catch (SignatureVerificationException e) {
//            log.error("token SignatureVerificationException:{}", e.getLocalizedMessage());
//            throw new AccountPolicyException(e.getLocalizedMessage());
//        } catch (TokenExpiredException e) {
//            log.error("token TokenExpiredException:{}", e.getLocalizedMessage());
//            // 允许一段时间有效时间同时返回新的token 这里需要优化 防止高并发
//            // String newToken = JwtUtil.sign(username, account.getLoginPwd());
//            // log.info("Subject: [" + username + "] token expired, allow get new token [" + newToken + "]");
//            // throw new AccountPolicyException(CommonEnums.ERROR_SESSION_TIME_OUT.getCode(), "token has expired", newToken);
//            throw new AccountPolicyException(CommonEnums.ERROR_SESSION_TIME_OUT.getCode(), "token has expired");
//        } catch (Exception e) {
//            log.error("token Exception:{}", e.getLocalizedMessage());
//            throw new AccountPolicyException(e.getLocalizedMessage());
//        }

        //
        // cleanOtherUsers(accessOrganization.getId(), clientToken);

        //
//        UserPrincipal userPrincipal = new UserPrincipal(accessOrganization.getId(), accessOrganization.getName(), accessOrganization.getJuridicalName(), null);
//        return new SimpleAuthenticationInfo(userPrincipal, accessOrganization.getFormalSk(), getName());
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
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 清理其它用户，保障一个帐户同时只能登录一个
     *
     * @param accessOrganizationId
     * @param jwtToken
     */
    void cleanOtherUsers(Long accessOrganizationId, String jwtToken) {
        //log.info("cleanOtherUsers accountId:{}, jwtToken:{}", accountId, jwtToken);
        String jwtTokenInRedis = RedisUtils.get("api:accessOrganization:ak_" + accessOrganizationId);
        if (!jwtToken.equals(jwtTokenInRedis)) {
            log.error("cleanOtherUsers error:该账号已经在其他地方登录，本会话已过期！");
            throw new AccountPolicyException(CommonEnums.ERROR_SESSION_TIME_OUT.getCode(), "token has expired");
        }
    }
}
