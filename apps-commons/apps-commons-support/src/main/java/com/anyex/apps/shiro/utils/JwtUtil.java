/*
package com.anyex.apps.shiro.utils;

import com.anyex.apps.exception.BusinessException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

*/
/**
 * JwtUtil
 * <p>File：JwtUtil.java</p>
 * <p>Title: JwtUtil</p>
 * <p>Description: JwtUtil</p>
 * <p>Copyright: Copyright (c) 2019/10/23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 *//*

@Slf4j
public class JwtUtil
{
    */
/**
     * JWT验证过期时间 EXPIRE_TIME 分钟
     *//*

    // private static final long EXPIRE_TIME = 60 * 60 * 1000 * 24 * 7;
    private static final long EXPIRE_TIME = 60 * 60 * 1000 * 12;

    */
/**
     * 生成token签名EXPIRE_TIME 分钟后过期
     *
     * @param username 用户名(电话号码)
     * @param secret   用户的密码
     * @return 加密的token
     *//*

    public static String sign(String username, String secret)
    {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
    }

    */
/**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     *//*

    public static boolean verify(String token, String username, String secret)
    {
        try
        {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            // 效验TOKEN
            verifier.verify(token);
            return true;
        }
        catch (SignatureVerificationException e)
        {
            log.error("SignatureVerificationException:{}", e.getLocalizedMessage());
            throw e;
        }
        catch (TokenExpiredException e)
        {
            log.error("TokenExpiredException:{}", e.getLocalizedMessage());
            throw e;
        }
        catch (Exception e)
        {
            log.error("JwtUtil登录验证失败:{}", e.getLocalizedMessage());
            throw e;
        }
    }
    
    */
/**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     *//*

    public static String getUsername(String token) throws BusinessException
    {
        try
        {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }
        catch (JWTDecodeException e)
        {
            log.error(e.getLocalizedMessage());
            throw new BusinessException("用户不存在!");
        }
    }
    
//    public static void main(String[] args)
//    {
//        String sign = sign("18888888888", "123456");
//        System.out.println(getUsername(sign));
//        verify(sign,"18888888888","123456");
//        log.warn("测试生成一个token\n" + sign);
//    }
}
*/
