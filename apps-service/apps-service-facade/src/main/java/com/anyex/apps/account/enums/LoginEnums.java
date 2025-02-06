package com.anyex.apps.account.enums;

/**
 * 三方登录类型
 * <p>File：ThirdLoginEnums.java</p>
 * <p>Title: ThirdLoginEnums</p>
 * <p>Description: ThirdLoginEnums</p>
 * <p>Copyright: Copyright (c) 2019/10/28</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public enum LoginEnums
{
    QQ("qq", "QQ"), //
    SMS("sms", "短信"), //
    PASS("pass", "密码"), //
    EMAIL("email", "邮箱"), //
    WEIBO("weibo", "微博"), //
    WEIXIN("weixin", "微信"),//
    WEIXINMP("weixinmp", "微信小程序"),
    ZJDINGMP("zjdingmp", "浙政钉小程序"),

    EMAILPASS("emailPass", "邮箱密码登录"), //
    MOBILEPASS("mobilePass", "手机密码登录"); //
    
    private String code;
    
    private String name;
    
    LoginEnums(String code, String name)
    {
        this.code = code;
        this.name = name;
    }
    
    /**
     * 根据code取名称
     * @param code
     * @return
     */
    public String getName(String code)
    {
        String result = null;
        for (LoginEnums c : LoginEnums.values())
        {
            if (c.code.equals(code))
            {
                result = c.name;
                break;
            }
        }
        return result;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}
