package com.anyex.apps.user.service;

import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.google.Authenticator;
import com.anyex.apps.user.consts.UserConsts;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.model.PolicyModel;
import com.anyex.apps.utils.EncryptUtils;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户策略服务
 * <p>File：UserPolicyServiceImpl.java</p>
 * <p>Title: UserPolicyServiceImpl</p>
 * <p>Description: UserPolicyServiceImpl</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class UserPolicyServiceImpl implements UserPolicyService
{
    @Autowired(required = false)
    private SysMsgRecordService sysMsgRecordService;
    
    @Override
    public boolean validPassword(String plainPassword, String password)
    {
        boolean flag = false;
        if (StringUtils.isBlank(plainPassword) || StringUtils.isBlank(password))
        { return flag; }
        if (EncryptUtils.validatePassword(plainPassword, password))
        {
            flag = true;
        }
        return flag;
    }
    
    @Override
    public boolean validSMSCode(String phone, String validCode, String type)
    {
        return sysMsgRecordService.validSMSCode(phone, validCode, type);
    }
    
    @Override
    public boolean validGaCode(String authKey, String validCode)
    {
        if (StringUtils.isBlank(authKey) || StringUtils.isBlank(validCode)) return false;
        boolean flag = false;
        Authenticator authenticator = new Authenticator();
        try
        {
            Long lCode = Long.valueOf(validCode);
            if (authenticator.checkCode(EncryptUtils.desDecrypt(authKey), lCode))
            {
                flag = true;
            }
        }
        catch (RuntimeException e)
        {
            log.warn(e.getLocalizedMessage());
        }
        return flag;
    }
    
    @Override
    public boolean validEmailCode(String email, String validCode)
    {
        return sysMsgRecordService.validEmailCode(email, validCode, MessageConst.TEMPLATE_EMAIL_LOGINCODE);
    }
    
    @Override
    public void validSecurityPolicy(User user, PolicyModel policy) throws BusinessException
    {
        if (null == policy)
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        if (UserConsts.SECURITY_POLICY_DEFAULT.equals(user.getSecurityPolicy()))
        {// 验证登录密码
            if (!validPassword(policy.getPwd(), user.getLoginPwd()))
            { throw new BusinessException(CommonEnums.ERROR_LOGIN_PASSWORD); }
        }
        if (UserConsts.SECURITY_POLICY_NEEDGA.equals(user.getSecurityPolicy()))
        {// GA验证
            if (!validGaCode(user.getGaAuthKey(), policy.getGaCode()))
            { throw new BusinessException(CommonEnums.ERROR_GA_VALID_FAILED); }
        }
        if (UserConsts.SECURITY_POLICY_NEEDSMS.equals(user.getSecurityPolicy()))
        {// 短信验证
            StringBuffer mobile = new StringBuffer(user.getCountry()).append(user.getMobileNo());
            if (!validSMSCode(mobile.toString(), policy.getSmsCode(), policy.getSmsScene()))
            { throw new BusinessException(CommonEnums.ERROR_SMSCODE_VALID_FAILED); }
        }
        if (UserConsts.SECURITY_POLICY_NEEDGAANDSMS.equals(user.getSecurityPolicy()))
        {// GA和短信验证
            StringBuffer mobile = new StringBuffer(user.getCountry()).append(user.getMobileNo());
            if (!validSMSCode(mobile.toString(), policy.getSmsCode(), policy.getSmsScene()))
            { throw new BusinessException(CommonEnums.ERROR_SMSCODE_VALID_FAILED); }
            if (!validGaCode(user.getGaAuthKey(), policy.getGaCode()))
            { throw new BusinessException(CommonEnums.ERROR_GA_VALID_FAILED); }
        }
        if (UserConsts.SECURITY_POLICY_NEEDGAORSMS.equals(user.getSecurityPolicy()))
        {// GA或短信验证
            StringBuffer mobile = new StringBuffer(user.getCountry()).append(user.getMobileNo());
            boolean flag = validSMSCode(mobile.toString(), policy.getSmsCode(), policy.getSmsScene()) || validGaCode(user.getGaAuthKey(), policy.getGaCode());
            if (!flag)
            { throw new BusinessException(CommonEnums.ERROR_SMSCODE_VALID_FAILED); }
        }
    }
    
    @Override
    public boolean validTradePolicy(User user, String tradePwd)
    {
        boolean flag = false;
        if (UserConsts.TRADE_POLICY_EVERYTIME.equals(user.getTradePolicy()))
        {// 每次都验证
            flag = validPassword(user.getTradePwd(), tradePwd);
        }
        if (UserConsts.TRADE_POLICY_TWOHOUR.equals(user.getTradePolicy()))
        {// 每两小时验证一次
            StringBuffer cacheKey = new StringBuffer(CacheConst.POLICY_PERFIX).append(user.getId());
            if (StringUtils.isNotBlank(RedisUtils.get(cacheKey.toString())))
            {// 缓存中有验证过的标识时，不用再次验证
                flag = true;
            }
            else
            {
                flag = validPassword(user.getTradePwd(), tradePwd);
                if (flag)
                {// 资金密码验证成功之后将入缓存
                    RedisUtils.putObject(cacheKey.toString(), "valid", CacheConst.TWENTYFOUR_HOUR_CACHE_TIME);
                }
            }
        }
        return flag;
    }
    
    @Override
    public int errorOperatorCounter(String key)
    {
        int count = 1;
        String value = RedisUtils.get(key);
        if (StringUtils.isNotBlank(value))
        {
            count = count + Integer.valueOf(value);
            RedisUtils.putObject(key, String.valueOf(count), CacheConst.ONE_HOUR_CACHE_TIME);
        }
        else
        {
            RedisUtils.putObject(key, String.valueOf(count), CacheConst.ONE_HOUR_CACHE_TIME);
        }
        return count;
    }
}
