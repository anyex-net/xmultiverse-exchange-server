package com.anyex.apps.user.enums;

import com.anyex.apps.bean.EnumDescribable;
import com.anyex.apps.enums.CommonEnums;

/**
 * UserEnums 介绍
 * <p>File：UserEnums.java </p>
 * <p>Title: UserEnums </p>
 * <p>Description:UserEnums </p>
 * <p>Copyright: Copyright (c) 2017/7/10 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public enum UserEnums implements EnumDescribable
{
    USER_PHONE_TIPS(30000, "Please enter your phone number"), // 请输入手机号
    USER_NAME_NOTEXITS(30001, "Username cannot be blank"), // 帐户名称不能为空
    USER_PASSWORD_ERROR(30002, "Incorrect password"), // 密碼錯誤
    USER_SMSCODE_ERROR(30003, "SMS code error"), // 手机验证码错误
    USER_EMAILCODE_ERROR(30004, "email code error"), // 手机验证码错误
    USER_GACODE_ERROR(30005, "Google auth code error"), // 谷歌验证码错误
    USER_PHONE_NOTBIND(30006, "Please enable Phone Verification first."), // 手机号未绑定
    USER_EMAIL_NOTBIND(30007, "Please enable Email Verification first."), // 邮箱未绑定
    USER_GACODE_NOTBIND(30008, "Please enable GACode Verification first."), // GA未绑定
    USER_PHONE_HAS_BIND(30009, "The phone number has bound"), // 手机号已绑定
    USER_EMAIL_HAS_BIND(30010, "The email has bound"), // 邮箱已绑定
    USER_WALLET_ASSET_NOTEXITS(30011, "The wallet account does not exist"), // 钱包账户不存在
    USER_WALLET_ASSET_INSUFFICIENT_BAL(30012, "The balance of the wallet account is insufficient")// 钱包账户余额不足 insufficient Balance
    ;
    public Integer code;

    public String  message;

    private UserEnums(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    /**
     * 根据状态码获取状态码描述
     * @param code 状态码
     * @return String 状态码描述
     */
    public static String getMessage(Integer code)
    {
        String result = null;
        for (CommonEnums c : CommonEnums.values())
        {
            if (c.code.equals(code))
            {
                result = c.message;
                break;
            }
        }
        return result;
    }
    
    @Override
    public Integer getCode()
    {
        return this.code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    @Override
    public String getMessage()
    {
        return this.message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
}
