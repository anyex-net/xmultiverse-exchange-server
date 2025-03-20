package com.anyex.apps.user.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.model.PolicyModel;

/**
 * 用户策略服务
 * <p>
 *     将所有安全验证策略和交易验证策略统一封装到一个服务中实现
 * </p>
 * <p>File：UserPolicyService.java</p>
 * <p>Title: UserPolicyService</p>
 * <p>Description: UserPolicyService</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface UserPolicyService
{
    /**
     * 验证用户密码或资金密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return {@link Boolean}
     */
    boolean validPassword(String plainPassword, String password);
    
    /**
     * 验证手机验证码
     * @param phone 手机号码
     * @param validCode 验证码
     * @param type 类型
     * @return {@link Boolean}
     */
    boolean validSMSCode(String phone, String validCode, String type);
    
    /**
     * 验证GA码
     * @param authKey 私钥
     * @param validCode 难证码
     * @return {@link Boolean}
     */
    boolean validGaCode(String authKey, String validCode);
    
    /**
     * 校验邮件验证码
     * @param email 邮件
     * @param validCode 验证码
     * @return {@link Boolean}
     */
    boolean validEmailCode(String email, String validCode);
    
    /**
     * 验证安全策略，不包含密码验证
     * @param user 用户信息
     * @param policy 策略
     * @throws BusinessException
     */
    void validSecurityPolicy(User user, PolicyModel policy) throws BusinessException;
    
    /**
     * 验证交易策略
     * @param user 用户信息
     * @param tradePwd 交易密码
     * @return {@link Boolean}
     */
    boolean validTradePolicy(User user, String tradePwd);
    
    /**
     * 错误操作记数
     * <p>
     *     用于关键业务需要错误计数
     * </p>
     * @param key
     * @return {@link Integer}
     */
    int errorOperatorCounter(String key);
}
