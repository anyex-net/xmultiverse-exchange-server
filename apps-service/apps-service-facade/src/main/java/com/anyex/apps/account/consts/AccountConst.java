package com.anyex.apps.account.consts;

/**
 * 帐户模块常量 介绍
 * <p>File：AccountConst.java </p>
 * <p>Title: AccountConst </p>
 * <p>Description:AccountConst </p>
 * <p>Copyright: Copyright (c) 2017/7/10 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class AccountConst
{
    private AccountConst()
    {
    }
    
    // 正常
    public static final Integer ACCOUNT_STATUS_NORMAL           = 0;
    
    // Integer
    public static final Integer ACCOUNT_STATUS_FROZEN           = 1;
    
    // 注销
    public static final Integer ACCOUNT_STATUS_CLOSE            = 2;
    
    // 默认安全验证策略登录密码
    public static final Integer SECURITY_POLICY_DEFAULT         = 0;
    
    // 安全验证策略启用SMS
    public static final Integer SECURITY_POLICY_NEEDSMS         = 1;
    
    // 安全验证策略启用GA
    public static final Integer SECURITY_POLICY_NEEDGA          = 2;
    
    // 安全验证策略启用SMS或GA
    public static final Integer SECURITY_POLICY_NEEDGAORSMS     = 3;
    
    // 安全验证策略启用SMS和GA
    public static final Integer SECURITY_POLICY_NEEDGAANDSMS    = 4;
    
    // 默认交易验证策略
    public static final Integer TRADE_POLICY_DEFAULT            = 0;
    
    // 交易验证策略两小时验证一次
    public static final Integer TRADE_POLICY_TWOHOUR            = 1;
    
    // 交易验证策略每次都验证
    public static final Integer TRADE_POLICY_EVERYTIME          = 2;

    // 验证方式：短信
    public static final String  ACCOUNT_VALID_EMAIL             = "email";

    // 验证方式：短信
    public static final String  ACCOUNT_VALID_SMS               = "sms";
    
    // 验证方式：GA
    public static final String  ACCOUNT_VALID_GA                = "ga";
}
